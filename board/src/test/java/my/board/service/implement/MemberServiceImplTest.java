package my.board.service.implement;

import my.member.domain.Member;
import my.member.repository.MemberRepository;
import my.member.service.MemberService;
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

    /*@Test
    @DisplayName("회원등록 서비스 테스트")
    void memberRegisterTest() {
        *//*String --> java.sql.date 변환*//*
        String d = "1999-2-12";
        java.sql.Date date = java.sql.Date.valueOf(d);

        Member member = new Member("park", "0000", "박진수", "park진수");
        memberService.register(member);
    }

    @Test
    @DisplayName("회원조회 서비스 테스트")
    void memberSelectTest(){
        String id = "lee";
        Member member = memberService.selectByIdandPassword(id);
        System.out.println(member.toString());
    }*/

    @Test
    @DisplayName("회원 ID 중복 테스트")
    void memberSeclectByIdTest() {
        String id = "park";
        boolean result = memberService.selectById(id);
        System.out.println(result);
    }
}