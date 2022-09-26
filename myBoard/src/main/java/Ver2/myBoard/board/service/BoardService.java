package Ver2.myBoard.board.service;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.dto.BoardUpdateDto;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.paging.PageDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

    public void insertBoard(BoardRegisterDto registerDto);

    public List<Board> getBoardList();

    public List<Board> getBoardListWithPaging(int pageNum);

    public Long getTotal();

    public Board getBoardById(Long id);

    public void updateBoard(Long id, BoardUpdateDto updateDto);

    public void removeBoard(Long id);

    public void updateHit(Board board);
}
