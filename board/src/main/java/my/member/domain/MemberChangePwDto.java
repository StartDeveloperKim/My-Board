package my.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberChangePwDto {

    private String password;
    private String confirmPassword;
}
