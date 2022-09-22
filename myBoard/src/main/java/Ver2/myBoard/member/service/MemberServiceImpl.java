package Ver2.myBoard.member.service;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.dto.MemberUpdateDto;
import Ver2.myBoard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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

    @Override
    public boolean checkId(String id) {
        return memberRepository.findById(id) == null;
        // 아이디가 없다면 true, 있다면 false 반환
    }

    @Override
    public boolean loginCheck(MemberLoginDto loginDto, BindingResult bindingResult) {
        Member findMember = memberRepository.findById(loginDto.getId());
        if (findMember == null) {
            bindingResult.reject("notFoundId", "일치하는 아이디가 없습니다.");
            return false;
        } else {
            if (findMember.getPassword().equals(loginDto.getPassword())) {
                return true;
            } else {
                bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
                return false;
            }
        }
    }
}
