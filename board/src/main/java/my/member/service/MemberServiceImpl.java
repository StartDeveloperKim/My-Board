package my.member.service;

import lombok.RequiredArgsConstructor;
import my.member.domain.Member;
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
}
