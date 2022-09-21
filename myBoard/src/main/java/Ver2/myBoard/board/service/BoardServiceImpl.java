package Ver2.myBoard.board.service;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.dto.BoardUpdateDto;
import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
// readOnly 설정은 select 쿼리에 있어서는 최적화가 일어난다.
// 하지만 읽기 전용이기에 update, delete, insert 쿼리에는 따로 @Transactional 어노테이션을 붙여줘야한다.
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

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
    public Board getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateBoard(Long id, BoardUpdateDto updateDto) {
        Board board = boardRepository.findById(id);
        board.updateBoard(updateDto); // 변경감지로 영속성 컨텍스트에서 영속 데이터의 변경이 감지되면 업데이트 된다.
        // Dirty Checking 은 제대로 공부하자...
    }

    @Override
    @Transactional
    public void removeBoard(Long id) {
        boardRepository.remove(id);
    }
}
