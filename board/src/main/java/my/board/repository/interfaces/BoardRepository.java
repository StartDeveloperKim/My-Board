package my.board.repository.interfaces;

import my.board.domain.Board;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.Criteria;
import my.board.domain.Search;

import java.util.List;

/*게시판 기능
 * 1. 등록 --> insert
 * 2. 수정 --> update
 * 3. 삭제 --> delete
 * 4. 보여주기 --> select (리스트로 보여주기, 상세 보여주기)
 * */
public interface BoardRepository {

    /*리스트로 보여주기*/
    public List<Board> selectBoard(Criteria cri);

    /*글 리스트 10개 가져오기 최신순으로*/
    public List<Board> selectBoardTen();

    /*상세 보여주기*/
    public Board selectById(int id);

    /*글 등록*/
    public void insertBoard(Board board);

    /*글 수정*/
    public void updateBoard(Board board);

    /*글 삭제*/
    public void deleteBoard(int id);

    public int getTotal();

    public void updateHit(Board board);

    /*검색후 반환*/
    public List<Board> searchBoard(Criteria cri, Search search);

    /*검색 결과에 대한 반환값 개수*/
    public int searchBoardGetTotal(Search search);
}
