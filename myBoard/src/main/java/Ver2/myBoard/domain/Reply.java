package Ver2.myBoard.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "REPLY_SEQ_GEN",
        sequenceName = "REPLY_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
public class Reply {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPLY_SEQ_GEN"
    )
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "content")
    private String comment;

    @Column(name = "register_date")
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board; // FK, 연관관계 주인

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member; // FK, 연관관계 주인
    
    //==연관관계 편의 메서드==//

    public void setBoard(Board board) {
        this.board = board; // board를 지정 
        board.getComments().add(this); // 댓글추가
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
