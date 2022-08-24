package my.board.service.interfaces;

import my.board.domain.Member;

public interface MemberService {

    public void register(Member member);

    public Member selectById(String id);
}
