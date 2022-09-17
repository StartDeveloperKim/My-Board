package my.member.service;

import lombok.RequiredArgsConstructor;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.domain.MemberLoginDto;
import my.member.domain.jpaDomain.Member;
import my.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JPAMemberService implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public void register(Member member) {
        memberRepository.insert(member);
    }

    @Override
    public Member selectByIdandPassword(MemberLoginDto loginDto) {
        return memberRepository.selectByIdandPassword(loginDto);
    }

    @Override
    public Member selectById(String id) {
        return memberRepository.selectById(id);
    }

    @Override
    public boolean checkId(String id) {
        return memberRepository.selectById(id) == null;
    }

    /*변경감지를통한 update*/
    @Override
    @Transactional
    public void updatePassword(String userId, MemberChangePwDto changePwDto) {
        Member member = memberRepository.selectById(userId);
        member.setPassword(changePwDto);
    }

    @Override
    @Transactional
    public void updateNickname(String userId, MemberChangeNicknameDto changeNicknameDto) {
        Member member = memberRepository.selectById(userId);
        member.setNickname(changeNicknameDto);
    }
}
