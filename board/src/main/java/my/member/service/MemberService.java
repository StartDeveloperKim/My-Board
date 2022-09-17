package my.member.service;

import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.domain.MemberLoginDto;
import my.member.domain.jpaDomain.Member;

public interface MemberService {

    public void register(Member member);

    public Member selectByIdandPassword(MemberLoginDto loginDto);

    public Member selectById(String id);

    public boolean checkId(String id);

    public void updatePassword(String userId, MemberChangePwDto changePwDto);

    public void updateNickname(String userId, MemberChangeNicknameDto changeNicknameDto);
}
