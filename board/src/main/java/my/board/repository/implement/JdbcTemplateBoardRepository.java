package my.board.repository.implement;

import lombok.extern.slf4j.Slf4j;
import my.board.domain.Board;
import my.board.repository.interfaces.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String selectBoard_sql = "SELECT * FROM BOARD";
    private String selectById_sql = "SELECT * FROM BOARD WHERE ID = ?";
    private String insertBoard = "INSERT INTO BOARD (ID, TITLE, CONTENT, NICKNAME) VALUES (board_seq.nextval, ?, ?, ?)";
    private String selectTenBoard_sql = "SELECT B.* FROM (SELECT ROWNUM RN, TB.* FROM (SELECT * FROM BOARD ORDER BY REGDATE DESC) TB) B WHERE RN BETWEEN 1 AND 10";

    @Override
    public List<Board> selectBoard() {
        return jdbcTemplate.query(selectBoard_sql, rowMapper);
    }

    @Override
    public List<Board> selectBoardTen() {
        return jdbcTemplate.query(selectTenBoard_sql, rowMapper);
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

    }

    @Override
    public void deleteBoard(int id) {

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
