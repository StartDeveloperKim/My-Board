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
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public void remove(Long id) {
        // EntityManager 에 remove 함수가 있지만 이는 Board 객체를 직접 전달해야한다.
        // 그러기 위해선 Board 객체를 받아와야하는 쿼리 한번, 그 다음 remove 를 위한 쿼리 한번이 나가야 하므로
        // createQuery 를 통해 바로 삭제가 괜찮다고 판단하였다.
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
