package Ver2.myBoard.board.repository;

import Ver2.myBoard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJPABoardRepository extends JpaRepository<Board, Long> {
    @Override
    List<Board> findAll(Sort sort); // 모든 엔티티를 반환한다.

    @Override
    Page<Board> findAll(Pageable pageable); // 페이징?

    @Override
    Optional<Board> findById(Long aLong); // 주어진 PK로 식별되는 엔티티를 반환

    @Override
    long count(); // 엔티티 수를 반환

    @Override
    boolean existsById(Long aLong); // 해당 아이디를 갖는 엔티티가 있는지 True or False

    Page<Board> findByWriter(String writer, Pageable pageable);
}
