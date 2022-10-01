package Ver2.myBoard.domain;

import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.dto.MemberUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@SequenceGenerator(
        name = "MEMBER_SEQ_GEN",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
public class Member {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GEN"
    )
    @Column(name = "member_id")
    private Long member_id;

    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>(); // 한 회원이 작성한 글의 목록

    @OneToMany(mappedBy = "member")
    private List<Heart> hearts = new ArrayList<>();

    //==생성자==//
    public Member(String id, String password, String nickname, LocalDateTime createDate) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.createDate = createDate;
    }

    //==생성 메서드==//
    public static Member createMember(MemberRegisterDto registerDto) {
        return new Member(registerDto.getId(), registerDto.getPassword(),
                registerDto.getNickname(), LocalDateTime.now());
    }

    //==업데이트 메서드==//
    public void updateMember(MemberUpdateDto updateDto) {
        this.password = updateDto.getPassword();
        this.nickname = updateDto.getNickname();
    }
}
