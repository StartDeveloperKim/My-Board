package my.board.repository.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.domain.Criteria;
import my.board.domain.Search;
import my.board.domain.jpaDomain.Board;
import my.board.repository.interfaces.BoardRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JpaBoardRepository implements BoardRepository {

    private final EntityManager em;

    @Override
    public void insertBoard(Board board) {
        em.persist(board);
    }

    @Override
    public Board selectById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public List<Board> selectBoard(Criteria cri) {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    @Override
    public List<Board> selectBoardTen() {
        return em.createQuery("select b from Board b", Board.class)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public void deleteBoard(Long id) {
        em.createQuery("delete from Board b where b.id = :id", Board.class)
                .setParameter("id", id);
    }

    @Override
    public Long getTotal() {
        return em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Board> searchBoard(Criteria cri, Search search) {
        return null;
    }

    @Override
    public int searchBoardGetTotal(Search search) {
        return 0;
    }
}
