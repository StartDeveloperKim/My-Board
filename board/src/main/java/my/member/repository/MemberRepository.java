package my.member.repository;

import my.member.domain.MemberLoginDto;
import my.member.domain.jpaDomain.Member;

public interface MemberRepository {

    public void insert(Member member);

    public Member selectByIdandPassword(MemberLoginDto loginDto);

    public Member selectById(String id);

//    public int updatePassword(Member member);
//    public int updateNickname(Member member);

}
