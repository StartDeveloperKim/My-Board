package Ver2.myBoard.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;

    @Test
    @DisplayName("게시판 글 개수 가져오기 테스트")
    void getTotalTest() {
        Long total = boardService.getTotal();
        System.out.println("total = " + total);
    }
}