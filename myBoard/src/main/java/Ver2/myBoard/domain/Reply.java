package Ver2.myBoard.domain;

import Ver2.myBoard.reply.dto.ReplyRegisterDto;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@ToString
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
    private String regDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private Board board; // FK, 연관관계 주인

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member; // FK, 연관관계 주인


    public Reply(String comment, String regDate) {
        this.comment = comment;
        this.regDate = regDate;
    }

    public Reply() {

    }

    //==생성메서드==//
    public static Reply createReply(Member member, Board board, ReplyRegisterDto replyRegisterDto) {
        Reply reply = new Reply(replyRegisterDto.getComment(), Reply.makeRecentTime());
        reply.setBoard(board);
        reply.setMember(member);
        return reply;
    }
    
    //==연관관계 편의 메서드==//

    public void setBoard(Board board) {
        this.board = board; // board를 지정 
        board.getComments().add(this); // 댓글추가
    }

    public void setMember(Member member) {
        this.member = member;
        //member.getReplies().add(this);
    }

    public static String makeRecentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
