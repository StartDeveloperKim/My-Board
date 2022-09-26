package Ver2.myBoard.board.repository;

import Ver2.myBoard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPABoardRepository extends JpaRepository<Board, Long> {


}
