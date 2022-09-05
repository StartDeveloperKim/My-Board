package my.board.repository.implement;

import lombok.extern.slf4j.Slf4j;
import my.board.domain.Board;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.Criteria;
import my.board.repository.interfaces.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
@Slf4j
public class JdbcTemplateBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*쿼리 문*/
    private String selectBoardWithPaging_sql = "SELECT B.* FROM (SELECT ROWNUM RN, TB.* FROM (SELECT * FROM BOARD ORDER BY REGDATE DESC) TB) B WHERE RN BETWEEN ? AND ?";
    private String selectById_sql = "SELECT * FROM BOARD WHERE ID = ?";
    private String insertBoard = "INSERT INTO BOARD (ID, TITLE, CONTENT, NICKNAME) VALUES (board_seq.nextval, ?, ?, ?)";
    private String selectTenBoard_sql = "SELECT B.* FROM (SELECT ROWNUM RN, TB.* FROM (SELECT * FROM BOARD ORDER BY REGDATE DESC) TB) B WHERE RN BETWEEN ? AND ?";
    private String getCount_sql = "SELECT COUNT(*) FROM BOARD";
    private String update_sql = "UPDATE BOARD SET TITLE=?, CONTENT=?, UPDATEDATE=? WHERE ID=?";
    private String delete_sql = "DELETE FROM BOARD WHERE ID = ?";
    private String updateHit_sql = "UPDATE BOARD SET HIT = ? WHERE ID=?";

    @Override
    public List<Board> selectBoard(Criteria cri) {
        log.info("selectBoard -> PageNum:{}, amount:{}", cri.getPageNum(), cri.getAmount());
        return jdbcTemplate.query(selectBoardWithPaging_sql, rowMapper, (((cri.getPageNum()-1) * cri.getAmount()) + 1), cri.getPageNum() * cri.getAmount());
    }
    /*
    * pageNum=1, amount=10 or pageNum=2, amount=10
    * pageNum*amount
    * */

    /*시작페이지에서 글 10개 보기*/
    @Override
    public List<Board> selectBoardTen() {
        return jdbcTemplate.query(selectTenBoard_sql, rowMapper, 1, 10);
    }

    @Override
    public Board selectById(int id) {
        List<Board> result = jdbcTemplate.query(selectById_sql, rowMapper, id);
        return result.get(0);
    }

    @Override
    public void insertBoard(Board board) {
        jdbcTemplate.update(insertBoard, board.getTitle(), board.getContent(), board.getNickname());
    }

    @Override
    public void updateBoard(Board board) {
        log.info("updateBoard");
        jdbcTemplate.update(update_sql, board.getTitle(), board.getContent(), new Date(), board.getId());
    }

    @Override
    public void deleteBoard(int id) {
        jdbcTemplate.update(delete_sql, id);
    }

    @Override
    public int getTotal() {
        Integer result = jdbcTemplate.queryForObject(getCount_sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        });
        return result;
    }

    @Override
    public void updateHit(Board board) {
        jdbcTemplate.update(updateHit_sql, board.getHit(), board.getId());
    }

    private RowMapper<Board> rowMapper = new RowMapper<Board>() {
        @Override
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            Board board = new Board(
                    rs.getString("TITLE"),
                    rs.getString("CONTENT"),
                    rs.getString("NICKNAME"),
                    rs.getInt("HIT"),
                    rs.getDate("REGDATE"),
                    rs.getDate("UPDATEDATE")
            );
            board.setId(rs.getInt("ID"));
            return board;
        }
    };
}
