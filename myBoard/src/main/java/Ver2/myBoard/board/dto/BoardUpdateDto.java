package Ver2.myBoard.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDto {

    private Long id;
    @NotEmpty(message = "제목을 적어주세요!!")
    private String title;

    @NotEmpty(message = "내용을 적어주세요!!")
    private String content;
}
