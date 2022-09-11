package my.member.service;

import my.member.domain.Member;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.domain.MemberLoginDto;

public interface MemberService {

    public void register(Member member);

    public Member selectByIdandPassword(MemberLoginDto loginDto);

    public boolean checkId(String id);

    public int updatePassword(String userId, MemberChangePwDto changePwDto);

    public int updateNickname(String userId, MemberChangeNicknameDto changeNicknameDto);
}
