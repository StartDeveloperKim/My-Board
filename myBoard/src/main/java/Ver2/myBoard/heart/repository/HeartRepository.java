package Ver2.myBoard.heart.repository;

import Ver2.myBoard.domain.Heart;
import Ver2.myBoard.domain.HeartStatus;
import Ver2.myBoard.heart.dto.HeartRequestDto;

public interface HeartRepository {

    public void save(Heart heart); // 좋아요 또는 싫어요를 저장

    // 좋아요, 싫어요 추가, 삭제 등을 status로 구분한다.
    public Heart findByMemberIdAndBoardId(String memberId,  Long boardId, HeartStatus status);
    
    // 해당 객체를 삭제
    public void remove(Heart heart);
}
