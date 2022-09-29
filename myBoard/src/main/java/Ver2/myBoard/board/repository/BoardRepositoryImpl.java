package Ver2.myBoard.board.repository;

import Ver2.myBoard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public void insert(Board board) {
        em.persist(board);
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select b from Board b order by b.regDate desc ", Board.class)
                .getResultList();
    }

    @Override
    public List<Board> findPaging(int start) {
        return em.createQuery("select  b from Board b order by b.regDate desc", Board.class)
                .setFirstResult(start)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public List<Board> findByMemberId(String id, int start) {
        return em.createQuery("select b from Board b join fetch b.member where b.member.id = :id order by b.regDate desc", Board.class)
                .setParameter("id", id)
                .setFirstResult(start)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public Long getCountById(String id) {
        return em.createQuery("select count(b) from Board b join b.member where b.member.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void remove(Long id) {
        // EntityManager 에 remove 함수가 있지만 이는 Board 객체를 직접 전달해야한다.
        // 그러기 위해선 Board 객체를 받아와야하는 쿼리 한번, 그 다음 remove 를 위한 쿼리 한번이 나가야 하므로
        // createQuery 를 통해 바로 삭제가 괜찮다고 판단하였다.
        em.createQuery("delete from Board b where b.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Long getCount() {
        return em.createQuery("select count(b) from Board b", Long.class).getSingleResult();
    }
}
