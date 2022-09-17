package my.member.service;

import my.member.domain.MemberLoginDto;
import my.member.domain.jpaDomain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JPAMemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("멤버 등록 테스트")
    @Rollback(value = false)
    void registerMemberTest() {
        Member member = new Member("kim", "1234", "김진수", "킹진수");

        memberService.register(member);

        em.flush();
    }

    @Test
    @DisplayName("아이디, 비번으로 가져오기")
    void getMember() {
        Member member = new Member("kim", "1234", "김진수", "킹진수");
        memberService.register(member);

        em.flush();


        MemberLoginDto memberLoginDto = new MemberLoginDto();
        memberLoginDto.setId("kim");
        memberLoginDto.setPassword("1234");

        Member findMember = memberService.selectByIdandPassword(memberLoginDto);

        assertThat(memberLoginDto.getId()).isEqualTo(findMember.getId());
        assertThat(memberLoginDto.getPassword()).isEqualTo(findMember.getPassword());
    }
}