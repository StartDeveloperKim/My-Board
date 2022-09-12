package my.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberChangePwDto {

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
