package my.member.repository;

import lombok.extern.slf4j.Slf4j;
import my.member.domain.Member;
import my.member.domain.MemberLoginDto;
import my.member.repository.MemberRepository;
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
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private String insert_sql = "INSERT INTO MEMBER3 (ID, PASSWORD, NAME, NICKNAME) VALUES (?, ?, ?, ?)";
    private String selectById_sql = "SELECT * FROM MEMBER3 WHERE ID = ? and PASSWORD = ?";

    @Override
    public void insert(Member member) {
        jdbcTemplate.update(insert_sql,
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getNickname());
    }

    @Override
    public Member selectByIdandPassword(MemberLoginDto loginDto) {
        List<Member> member = jdbcTemplate.query(selectById_sql, rowMapper, loginDto.getId(), loginDto.getPassword());
        if(member.isEmpty()){
            /* 나중에 예외처리를 공부하고 다시 작성해보자 */
            return null;
        }
        return member.get(0);
    }

    private RowMapper<Member> rowMapper = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(rs.getString("ID"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("NICKNAME"));
            member.setRegdate(rs.getDate("REGDATE"));
            return member;
        }
    };

}
