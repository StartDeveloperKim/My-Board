package Ver2.myBoard.reply.dto;

import Ver2.myBoard.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyShowDto {

    private Long id;
    private String comment; // 댓글
    private String nickname; // 닉네임
    private String regDate; // 등록일

    public ReplyShowDto(Reply reply) {
        this.id = reply.getId();
        this.comment = reply.getComment();
        this.nickname = reply.getMember().getNickname();
        this.regDate = reply.getRegDate();
    }
}
