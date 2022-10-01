package Ver2.myBoard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "HEART_SEQ_GEN",
        sequenceName = "HEART_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
public class Heart {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "HEART_SEQ_GEN"
    )
    @Column(name = "heart_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private HeartStatus heartStatus; // 좋아요 또는 싫어요를 나타낸다.

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board;

    //생성자를 private으로 지정함으로써 생성함수를 통해서만 객체를 생성할 수 있도록 하였다.
    private Heart(HeartStatus heartStatus) {
        this.heartStatus = heartStatus;
    }

    //==연관관계 메서드==//
    private void setMember(Member member) {
        this.member = member;
        member.getHearts().add(this);
    }

    private void setBoard(Board board) {
        this.board = board;
        board.getHearts().add(this);
    }

    //==생성 메서드==//
    public static Heart createHeart(HeartStatus heartStatus, Member member, Board board) {
        Heart heart = new Heart(heartStatus);
        heart.setMember(member);
        heart.setBoard(board);

        return heart;
    }



}
