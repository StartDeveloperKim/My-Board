package Ver2.myBoard.board.dto;

import Ver2.myBoard.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDetailDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long like;
    private Long dislike;
    private List<Reply> comments = new ArrayList<>();
}
