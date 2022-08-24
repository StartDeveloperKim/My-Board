package my.board.repository.interfaces;

import my.board.domain.Member;
import org.springframework.stereotype.Repository;

public interface MemberRepository {

    public void insert(Member member);

    public Member selectById(String id);

}
