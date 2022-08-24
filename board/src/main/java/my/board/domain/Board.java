package my.board.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Board {

    private int id;
    private String title;
    private String content;
    private int nickname;
    private int hit;
    private Date regdate;
    private Date updatedate;

    public Board(String title, String content, int nickname, int hit) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.hit = hit;
    }
}
