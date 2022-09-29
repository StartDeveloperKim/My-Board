package Ver2.myBoard.board.service;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.dto.BoardUpdateDto;
import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.repository.MemberRepository;
import Ver2.myBoard.paging.PageDto;
import Ver2.myBoard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
// readOnly 설정은 select 쿼리에 있어서는 최적화가 일어난다.
// 하지만 읽기 전용이기에 update, delete, insert 쿼리에는 따로 @Transactional 어노테이션을 붙여줘야한다.
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyService replyService;

    @Override
    @Transactional
    public void insertBoard(BoardRegisterDto registerDto) {
        Member member = memberRepository.findByNickname(registerDto.getNickname());
        Board board = Board.createBoard(member, registerDto);

        boardRepository.insert(board);
    }

    @Override
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> getBoardListWithPaging(int pageNum) {
        return boardRepository.findPaging((pageNum - 1) * 10);
    }

    @Override
    public Long getTotal() {
        return boardRepository.getCount();
    }

    @Override
    public Long getTotalById(String id) {
        return boardRepository.getCountById(id);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateBoard(Long id, BoardUpdateDto updateDto) {
        Board board = boardRepository.findById(id); // -> 영속상태의 Entity
        log.info("Before update {}, {}", board.getTitle(), board.getContent());
        board.updateBoard(updateDto); // Entity의 내용을 변경 -> @Transactional 어노테이션으로인해 커밋이 발생
        log.info("After update {}, {}", board.getTitle(), board.getContent());
        // Dirty Checking 은 제대로 공부하자...
    }

    @Override
    @Transactional
    public void removeBoard(Long id) {
        replyService.removeReplyByBoardId(id); // 댓글 삭제
        boardRepository.remove(id);
    }

    @Override
    @Transactional
    public void updateHit(Board board) {
        board.updateHit();
    }
}
