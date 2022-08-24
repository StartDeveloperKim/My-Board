package my.board.service.implement;

import my.board.domain.Member;
import my.board.repository.interfaces.MemberRepository;
import my.board.service.interfaces.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원등록 서비스 테스트")
    void memberRegisterTest() {
        /*String --> java.sql.date 변환*/
        String d = "1999-2-12";
        java.sql.Date date = java.sql.Date.valueOf(d);

        Member member = new Member("park", "0000", "박진수", "park진수", date);
        memberService.register(member);
    }

    @Test
    @DisplayName("회원조회 서비스 테스트")
    void memberSelectTest(){
        String id = "lee";
        Member member = memberService.selectById(id);
        System.out.println(member.toString());
    }
}