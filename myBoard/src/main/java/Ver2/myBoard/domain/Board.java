package Ver2.myBoard.domain;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.dto.BoardUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "BOARD_SEQ_GEN",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GEN"
    )
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "writer")
    private String writer; // 작성자 닉네임

    @Column(name = "hit", nullable = false)
    private int hit;

    @Column(nullable = false)
    private String regDate;

    private String updateDate = "-";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Reply> comments = new ArrayList<>();


    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member=member;
        member.getBoards().add(this);
    }

    //==생성자==//
    public Board(String title, String content, String writer, int hit, String regDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hit = hit;
        this.regDate = regDate;
    }

    //==생성 메서드==//
    public static Board createBoard(Member member, BoardRegisterDto registerDto) {
        Board board = new Board(registerDto.getTitle(), registerDto.getContent(),
                registerDto.getNickname(), 0, Board.makeRecentTime());
        board.setMember(member);
        return board;
    }

    //==업데이트 메서드==//
    public void updateBoard(BoardUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.updateDate = Board.makeRecentTime(); // 수정 날짜 업데이트
    }

    public static String makeRecentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }

    public boolean checkMemberEquals(Member member) {
        return this.member == member;
    }

    public void updateHit() {
        this.hit += 1;
    }
}
