package Ver2.myBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRegisterDto {

    private String title;
    private String content;
    private String nickname;

    public BoardRegisterDto(String nickname) {
        this.nickname = nickname;
    }
}
