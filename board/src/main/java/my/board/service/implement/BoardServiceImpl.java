package my.board.service.implement;

import lombok.RequiredArgsConstructor;
import my.board.domain.Board;
import my.board.repository.interfaces.BoardRepository;
import my.board.service.interfaces.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor /* Lombok 의존 자동주입 어노테이션 */
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> getBoardList() {
        return boardRepository.selectBoard();
    }

    @Override
    public List<Board> getBoardListTen() {
        return boardRepository.selectBoardTen();
    }

    @Override
    public Board getBoardById(int id) {
        return boardRepository.selectById(id);
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.insertBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.updateBoard(board);
    }

    @Override
    public void deleteBoard(int id) {
        boardRepository.deleteBoard(id);
    }
}
