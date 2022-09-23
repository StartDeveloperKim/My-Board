package Ver2.myBoard.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyShowDto {

    private String comment; // 댓글
    private String nickname; // 닉네임
    private String regDate; // 등록일
}
