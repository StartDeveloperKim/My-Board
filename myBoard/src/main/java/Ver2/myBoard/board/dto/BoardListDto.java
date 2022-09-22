package Ver2.myBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardListDto {

    private Long id;
    private String title;
    private String writer;
    private int hit;
    private String regDate;
    private String updateDate;
}
