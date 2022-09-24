package Ver2.myBoard.reply.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReplyRegisterDto {

    private String comment;
    private String memberId; // 작성자 ID
    private Long boardId; // 게시판 ID
}
