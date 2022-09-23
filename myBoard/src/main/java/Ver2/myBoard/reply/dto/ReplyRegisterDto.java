package Ver2.myBoard.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRegisterDto {

    private String comment;
    private String memberId; // 작성자 ID
    private Long boardId; // 게시판 ID
}
