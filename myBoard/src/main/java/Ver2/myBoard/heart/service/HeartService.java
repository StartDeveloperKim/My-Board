package Ver2.myBoard.heart.service;

import Ver2.myBoard.domain.Heart;
import Ver2.myBoard.heart.dto.HeartRequestDto;

public interface HeartService {

    public void addHeart(HeartRequestDto requestDto);

    public void removeHeart(HeartRequestDto requestDto);

    public Heart findHeart(HeartRequestDto requestDto);
}
