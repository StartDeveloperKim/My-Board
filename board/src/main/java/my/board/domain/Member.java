package my.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Member {

    private String id;
    private String password;
    private String name;
    private String nickname;
    private Date birthdate;
    private Date regdate;

    public Member(String id, String password, String name, String nickname, Date birthdate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }
}
