package Ver2.myBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardRegisterDto {

    private String title;
    private String content;
    private String nickname;
}
