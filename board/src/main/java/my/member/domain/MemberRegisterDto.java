package my.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberRegisterDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;

    private boolean checkId, checkNickname;
    
    // Id중복여부 체크, 닉네임 중복여부 체크
    public MemberRegisterDto() {
        this.checkId=false;
        this.checkNickname=false;
    }
}
