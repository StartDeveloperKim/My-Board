package my.member.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.member.domain.MemberLoginDto;
import my.member.domain.jpaDomain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JPAMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Override
    public void insert(Member member) {
        log.info("member insert={}", member.getId());
        em.persist(member);
    }

    @Override
    public Member selectByIdandPassword(MemberLoginDto loginDto) {
        try {
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id= :id and m.password= : password", Member.class);
            query.setParameter("id", loginDto.getId());
            query.setParameter("password", loginDto.getPassword());

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Member selectById(String id) {
        try {
            return em.createQuery("select m from Member m where m.id= :id", Member.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
