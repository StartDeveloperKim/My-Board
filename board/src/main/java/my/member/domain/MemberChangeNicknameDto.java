package my.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberChangeNicknameDto {

    @NotBlank
    private String nickname;
}
