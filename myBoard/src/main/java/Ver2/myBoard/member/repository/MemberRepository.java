package Ver2.myBoard.member.repository;

import Ver2.myBoard.domain.Member;

public interface MemberRepository {

    public void insert(Member member); // member 회원가입

    public Member findById(String id);

    public Member findByNickname(String nickname);

    public void removeMember(String id); // 회원 탈퇴
}
