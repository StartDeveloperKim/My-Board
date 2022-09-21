package Ver2.myBoard.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private String nickname;
    private String password;
    private String confirmPassword;
}
