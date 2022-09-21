package my.board.domain.jpaDomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.board.domain.BoardRegisterDTO;
import my.board.domain.BoardUpdateDto;
import my.member.domain.jpaDomain.Member;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "HIT", nullable = false)
    @ColumnDefault("0")
    private int hit;

    @Column(nullable = false)
//    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    @LastModifiedDate
    private LocalDateTime regdate;

//    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    @LastModifiedDate
    private LocalDateTime updatedate;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    //==생성메서드==//
    public static Board createBoard(Member member, BoardRegisterDTO registerDTO) {
        Board board = new Board(registerDTO.getTitle(), registerDTO.getContent());
        board.setMember(member); // 연관관계 설정, FK키로 member의 PK를 넣고 Member 객체의 글 모음에 해당 글을 넣는다.
        board.setRegdate(LocalDateTime.now()); // 등록일자 설정

        return board; // board 반환
    }

    //==업데이트 로직==//
    public void update(BoardUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.updatedate = LocalDateTime.now();
    }

    // 조회수 1 증가
    public void plusHit() {
        this.hit += 1;
    }
}
