package my.board.service.interfaces;

import my.board.domain.Board;

import java.util.List;

/*서비스 기능
* 1. 글 보여주기 --> 글을 가져와야 함
* 2. 글 상세 보여주기 --> 글 하나의 아이디를 이용해서 모든 속성을 가져와야 함
* 3, 글 등록 --> 글 insert
* 4. 글 수정 --> 글 하나 get 그리고 다시 작성 update
* 5. 글 삭제 --> id가지고 삭제
* */
public interface BoardService {

    public List<Board> getBoardList();

    public Board getBoardById(int id);

    public void insertBoard(Board board);

    public void updateBoard(Board board);

    public void deleteBoard(int id);
}
