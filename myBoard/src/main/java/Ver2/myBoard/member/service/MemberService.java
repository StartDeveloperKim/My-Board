package Ver2.myBoard.member.service;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.dto.MemberUpdateDto;

public interface MemberService {

    public void insert(MemberRegisterDto registerDto);

    public Member getMemberById(String id);

    public void updateMember(String id, MemberUpdateDto updateDto);

    public void removeBoard(String id);
}
