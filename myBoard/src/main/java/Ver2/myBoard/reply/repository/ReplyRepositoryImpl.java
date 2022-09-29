package Ver2.myBoard.reply.repository;

import Ver2.myBoard.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepository{

    private final EntityManager em;

//    @Override
//    public List<Reply> getReplyList(Long id) {
//        return em.createQuery("select r from Reply r left join fetch r.member where r.board.id = :id order by r.regDate desc", Reply.class)
//                .setParameter("id", id)
//                .getResultList();
//    }

    @Override
    public List<Reply> getReplyList(Long id) {
        return em.createQuery("select r from Reply r where r.board.id = :id order by r.regDate desc", Reply.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void insertReply(Reply reply) {
        em.persist(reply);
    }

    @Override
    public void removeReply(Long id) {
        em.createQuery("delete from Reply r where r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void removeReplyByBoardId(Long id) {
        em.createQuery("delete from Reply r where r.board.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
