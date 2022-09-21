package Ver2.myBoard.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterDto {

    private String id;
    private String password;
    private String confirmPassword;
    private String nickname;
}
