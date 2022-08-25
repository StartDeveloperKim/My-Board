package my.board.service.implement;

import my.board.domain.Board;
import my.board.repository.interfaces.BoardRepository;
import my.board.service.interfaces.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.lang.model.SourceVersion;
import java.util.List;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("글 등록 및 가져오기 테스트")
    void BoardRegisterTest() {
        Board board = new Board("세번쨰 글입니다.", "하하", "틔우");
        boardService.insertBoard(board);

        Board boardById = boardService.getBoardById(3);
        System.out.println("boardById = " + boardById.toString());
    }

    @Test
    @DisplayName("글 목록 가져오기")
    void getBoardListTest() {
        List<Board> boardList = boardService.getBoardList();
        for (Board board : boardList) {
            System.out.println("board = " + board.toString());
        }
    }

    @Test
    @DisplayName("글 10개 가져오기 테스트")
    void getBoardListTenTest(){
        List<Board> boardListTen = boardService.getBoardListTen();
        for (Board board : boardListTen) {
            System.out.println("board = " + board.toString());
        }
    }
}