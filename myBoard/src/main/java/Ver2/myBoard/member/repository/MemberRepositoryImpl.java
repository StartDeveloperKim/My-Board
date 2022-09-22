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
        try {
            return em.createQuery("select m from Member m where m.id = :id", Member.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            // getSingleResult 는 반환값이 정확히 하나가 아니라면 예외가 발생한다.
            // 값이 없다면 null 이기에 예외가 발생 -> try-catch로 잡아야함
            return null;
        }

    }

    @Override
    public Member findByNickname(String nickname) {
        return em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    @Override
    public void removeMember(String id) {
        em.createQuery("delete from Member m where m.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
