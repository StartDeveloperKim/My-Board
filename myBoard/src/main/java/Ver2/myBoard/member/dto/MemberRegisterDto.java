package Ver2.myBoard.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterDto {

    @NotEmpty(message = "아이디는 필수 입력 값입니다.")
    @Length(min = 3, max = 20)
    // 정규표현식으로 영문자와 숫자를 적절히 섞어 표현하라고 해야하지만 개인 프로젝트이기에 잠시 패스
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 20)
    // 비밀번호 또한 정규표현식으로 영어와 숫자의 적절한 조합과 특수기호의 포함여부 등을 검사해야하지만 잠시 패스
    private String password;

    @NotEmpty(message = "확인 비밀번호는 필수 입력 값입니다.")
    private String confirmPassword;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    @Length(min = 2, max = 15)
    private String nickname;

    private MemberIdCheck memberIdCheck = MemberIdCheck.NONE;
    private MemberNicknameCheck  nicknameCheck = MemberNicknameCheck.NONE;
    public boolean checkPassword() {
        return this.password.equals(this.confirmPassword);
    }
}
