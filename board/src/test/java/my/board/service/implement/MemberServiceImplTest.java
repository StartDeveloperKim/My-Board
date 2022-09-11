package my.board.service.implement;

import my.member.domain.Member;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.repository.MemberRepository;
import my.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
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

//    @Test
//    @DisplayName("회원 ID 중복 테스트")
//    void memberSeclectByIdTest() {
//        String id = "park";
//        boolean result = memberService.selectById(id);
//        System.out.println(result);
//    }

    @Test
    @DisplayName("회원 닉네임 변경 테스트")
    void memberNicknameChangeTest() {
        String newNickname = "호날두";
        MemberChangeNicknameDto memberChangeNicknameDto = new MemberChangeNicknameDto();
        memberChangeNicknameDto.setNickname(newNickname);

        memberService.updateNickname("lee", memberChangeNicknameDto);

        Member member = memberRepository.selectById("lee");
        Assertions.assertThat(member.getNickname()).isEqualTo(newNickname);
    }

    @Test
    @DisplayName("회원 비밀번호 변경 테스트")
    void memberPasswordChangeTest() {
        MemberChangePwDto memberChangePwDto = new MemberChangePwDto();
        memberChangePwDto.setPassword("1234");
        memberChangePwDto.setConfirmPassword("1234");

        memberService.updatePassword("lee", memberChangePwDto);

        Member member = memberRepository.selectById("lee");
        Assertions.assertThat(member.getPassword()).isEqualTo("1234");
    }
}