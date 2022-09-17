package my.member.domain.jpaDomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import my.board.domain.jpaDomain.Board;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.domain.MemberRegisterDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long member_id;

    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "NICKNAME", nullable = false, unique = true, length = 50)
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>(); // 한 멤버가 쓴 글 모음

    // 생성메서드 대신에 생성자를 통해 Member 객체를 생성하자.
    public Member(String id, String password, String name, String nickname) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }

    //==업데이트 로직==//
    public void setPassword(MemberChangePwDto changePwDto) {
        this.password = changePwDto.getPassword();
    }

    public void setNickname(MemberChangeNicknameDto changeNicknameDto) {
        this.nickname = changeNicknameDto.getNickname();
    }

}
