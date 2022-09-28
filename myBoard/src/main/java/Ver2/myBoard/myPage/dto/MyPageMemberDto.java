package Ver2.myBoard.myPage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class MyPageMemberDto {

    private String id;
    private String nickname;
    private LocalDateTime createTime;
}
