package Ver2.myBoard.board.service;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.dto.BoardUpdateDto;
import Ver2.myBoard.domain.Board;

import java.util.List;

public interface BoardService {

    public void insertBoard(BoardRegisterDto registerDto);

    public List<Board> getBoardList();

    public Board getBoardById(Long id);

    public void updateBoard(Long id, BoardUpdateDto updateDto);

    public void removeBoard(Long id);
}
