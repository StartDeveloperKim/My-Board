package my.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardRegisterDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String nickname;

    public BoardRegisterDTO(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
