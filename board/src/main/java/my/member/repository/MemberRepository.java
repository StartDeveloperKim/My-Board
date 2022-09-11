package my.member.repository;

import my.member.domain.Member;
import my.member.domain.MemberLoginDto;

public interface MemberRepository {

    public void insert(Member member);

    public Member selectByIdandPassword(MemberLoginDto loginDto);

    public Member selectById(String id);

    public int updatePassword(Member member);

    public int updateNickname(Member member);

}
