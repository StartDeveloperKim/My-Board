package my.member.service;

import lombok.RequiredArgsConstructor;
import my.member.domain.Member;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.domain.MemberLoginDto;
import my.member.repository.MemberRepository;
import my.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void register(Member member) {
        memberRepository.insert(member);
    }

    @Override
    public Member selectByIdandPassword(MemberLoginDto loginDto) {
        return memberRepository.selectByIdandPassword(loginDto);
    }

    @Override
    public boolean checkId(String id) {
        return memberRepository.selectById(id) == null;
    }

    @Override
    public int updatePassword(String userId, MemberChangePwDto changePwDto) {
        Member member = memberRepository.selectById(userId);
        member.setPassword(changePwDto.getPassword());
        //업데이트 로직
        return memberRepository.updatePassword(member);
    }

    @Override
    public int updateNickname(String userId, MemberChangeNicknameDto changeNicknameDto) {
        Member member = memberRepository.selectById(userId);
        member.setNickname(changeNicknameDto.getNickname());
        // 업데이트 로직
        return memberRepository.updateNickname(member);
    }

}
