package Ver2.myBoard.board.repository;

import Ver2.myBoard.domain.Board;

import java.util.List;

public interface BoardRepository {

    //CRUD -> Create, Read, Update, Delete

    public void insert(Board board); // 글 삽입

    public List<Board> findAll(); // 모든 글 조회

    public List<Board> findPaging(int start);

    public Board findById(Long id); // ID로 글 조회

    public List<Board> findByMemberId(String id, int start);

    public Long getCountById(String id);

    public void remove(Long id); // 글 삭제

    // 업데이트는 변경감지(dirty checking)기능으로 가능하기에 별도의 repository 함수가 필요없다.
    // ...필요할 수도? 아직 내가 잘 모르기때문..

    public Long getCount();
}
