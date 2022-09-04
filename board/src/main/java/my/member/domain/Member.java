package my.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String nickname;
    private Date regdate;

    public Member(String id, String password, String name, String nickname) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }
}
