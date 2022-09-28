package Ver2.myBoard.myPage.dto;

import Ver2.myBoard.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageBoardDto {

    private Long id;
    private String title;
    private String nickname;
    private int hit;
    private String regDate;
    private String updateDate;

    public MyPageBoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.nickname = board.getWriter();
        this.hit = board.getHit();
        this.regDate = board.getRegDate();
        this.updateDate = board.getUpdateDate();
    }
}
