package my.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {

    private int id;

    @NotBlank
    @Length(min = 1, max = 30)
    private String title;

    @NotBlank
    private String content;

    @NotBlank
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
