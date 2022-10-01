package Ver2.myBoard.heart.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HeartRequestDto {

    private String status; // 좋아요(like), 싫어요(dislike), 좋아요 취소(removeLike), 싫어요 취소(removeDislike)
    private String memberId;
    private Long boardId;

}
