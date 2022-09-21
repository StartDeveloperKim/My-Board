package Ver2.myBoard.member.service;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.dto.MemberUpdateDto;
import Ver2.myBoard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void insert(MemberRegisterDto registerDto) {
        Member member = Member.createMember(registerDto);
        memberRepository.insert(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public void updateMember(String id, MemberUpdateDto updateDto) {
        Member member = memberRepository.findById(id);
        member.updateMember(updateDto);
    }

    @Override
    public void removeBoard(String id) {
        memberRepository.removeMember(id);
    }
}
