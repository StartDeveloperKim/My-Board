package Ver2.myBoard.heart.controller;

import Ver2.myBoard.heart.dto.HeartRequestDto;
import Ver2.myBoard.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/heart")
    public String postHeart(@RequestBody HeartRequestDto requestDto) {
        try {
            // 좋아요(like), 싫어요(dislike), 좋아요 취소(removeLike), 싫어요 취소(removeDislike)
            log.info("HeartRequestDto {}", requestDto.toString());
            if (requestDto.getStatus().equals("like") || requestDto.getStatus().equals("dislike")) {
                heartService.addHeart(requestDto);
            } else {
                heartService.removeHeart(requestDto);
            }

            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

}
