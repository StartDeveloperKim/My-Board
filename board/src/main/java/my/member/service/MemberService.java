package my.member.service;

import my.member.domain.Member;
import my.member.domain.MemberLoginDto;

public interface MemberService {

    public void register(Member member);

    public Member selectByIdandPassword(MemberLoginDto loginDto);
}
