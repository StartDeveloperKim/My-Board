package my.board.service.implement;

import lombok.RequiredArgsConstructor;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.BoardUpdateDto;
import my.board.domain.Criteria;
import my.board.domain.Search;
import my.board.domain.jpaDomain.Board;
import my.board.repository.interfaces.BoardRepository;
import my.board.service.interfaces.BoardService;
import my.member.domain.jpaDomain.Member;
import my.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JpaBoardService implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void insertBoard(String memberId, BoardRegisterDTO registerDTO) {
        Member member = memberRepository.selectById(memberId);

        Board board = Board.createBoard(member, registerDTO);// member 참조, 제목, 글, 등록 일 등 설정이 끝남.

        boardRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Long boardId, BoardUpdateDto updateDto) {
        Board board = boardRepository.selectById(boardId);
        board.update(updateDto);
    }

    @Override
    public void updateHit(Long boardId) {
        Board board = boardRepository.selectById(boardId);
        board.plusHit(); // 조회수 1증가, 변경 감지를 통한 update
    }

    @Override
    public List<Board> getBoardList(Criteria cri) {
        //JPA를 통해 페이지구현을 해보자, 페이징 인터페이스가 있었다.
        return boardRepository.selectBoard(cri);
    }

    @Override
    public Long getTotal() {
        return boardRepository.getTotal();
    }

    @Override
    public List<Board> getBoardListTen() {
        return boardRepository.selectBoardTen();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.selectById(id);
    }


    @Override
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }

    /*검색기능은 JPA를 좀 더 공부하고 구현해보자*/
    @Override
    public List<Board> searchBoard(Criteria cri, Search search) {
        return null;
    }

    @Override
    public int getTotalAtSearchBoard(Search search) {
        return 0;
    }
}
