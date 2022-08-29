package my.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Board {

    private int id;
    private String title;
    private String content;
    private String nickname;
    private Integer hit;
    private Date regdate;
    private Date updatedate;

    public Board(String title, String content, String nickname, Integer hit, Date regdate, Date updatedate) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.hit = hit;
        this.regdate = regdate;
        this.updatedate = updatedate;
    }

    /*public Board(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }*/
}
