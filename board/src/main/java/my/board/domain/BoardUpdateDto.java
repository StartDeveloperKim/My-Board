package my.board.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class BoardUpdateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
