package my.board.service.implement;

import my.board.domain.BoardRegisterDTO;
import my.board.domain.Criteria;
import my.board.domain.jpaDomain.Board;
import my.member.domain.jpaDomain.Member;
import my.member.service.JPAMemberService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaBoardServiceTest {

    @Autowired
    JpaBoardService boardService;
    @Autowired
    JPAMemberService memberService;
    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
        Member member = new Member("kim", "1234", "김진수", "킹진수");
        memberService.register(member);
        em.flush();
    }

    @Test
    @DisplayName("글 등록 테스트")
    @Rollback(false)
    void registerBoardTest() {
        Member member = memberService.selectById("kim");

        BoardRegisterDTO boardRegisterDTO = new BoardRegisterDTO();
        boardRegisterDTO.setTitle("안녕하세용");
        boardRegisterDTO.setContent("접니다~");

        boardService.insertBoard(member.getId(), boardRegisterDTO);

        List<Board> boardList = boardService.getBoardList(new Criteria());
        for (Board board : boardList) {
            System.out.println("board = " + board);
        }
    }
}