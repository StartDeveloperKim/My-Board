package Ver2.myBoard.heart.repository;

import Ver2.myBoard.domain.Heart;
import Ver2.myBoard.domain.HeartStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HeartRepositoryImpl implements HeartRepository{

    private final EntityManager em;

    @Override
    public void save(Heart heart) {
        em.persist(heart);
    }

    @Override
    public Heart findByMemberIdAndBoardId(String memberId,  Long boardId, HeartStatus status) {
        try {
            return em.createQuery("select h from Heart h where h.member.id=:memberId and " +
                            "h.board.id=:boardId and h.heartStatus=:status", Heart.class)
                    .setParameter("memberId", memberId)
                    .setParameter("boardId", boardId)
                    .setParameter("status", status)
                    .getSingleResult();
        } catch (Exception e) {
            log.info("추천 Repository에서 에러 발생");
            return null;
        }

    }

    @Override
    public void remove(Long id) {
        em.createQuery("delete from Heart h where h.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
