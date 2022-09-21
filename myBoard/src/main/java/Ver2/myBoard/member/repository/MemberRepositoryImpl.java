package Ver2.myBoard.member.repository;

import Ver2.myBoard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final EntityManager em;

    @Override
    public void insert(Member member) {
        em.persist(member);
    }

    @Override
    public Member findById(String id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findByNickname(String nickname) {
        return em.find(Member.class, nickname);
    }

    @Override
    public void removeMember(String id) {
        em.createQuery("delete from Member m where m.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
