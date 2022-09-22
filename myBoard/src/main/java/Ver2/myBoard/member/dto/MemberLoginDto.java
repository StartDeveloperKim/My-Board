package Ver2.myBoard.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {

    @NotEmpty(message = "아이디를 입력해주세요!")
    private String id;

    @NotEmpty(message = "비밀번호를 입력해주세요!")
    private String password;

    private String nickname;
}
