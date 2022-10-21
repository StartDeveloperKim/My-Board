package Ver2.myBoard.member.service;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.dto.MemberUpdateDto;
import org.springframework.validation.BindingResult;

public interface MemberService {

    public void insert(MemberRegisterDto registerDto);

    public Member getMemberById(String id);

    public void updateMember(String id, MemberUpdateDto updateDto);

    public void removeBoard(String id);

    public boolean checkId(String id);

    public boolean loginCheck(MemberLoginDto loginDto, BindingResult bindingResult);

    public boolean registerCheck(MemberRegisterDto registerDto);
}
