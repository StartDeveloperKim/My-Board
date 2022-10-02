package Ver2.myBoard.heart.controller;

import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.domain.Board;
import Ver2.myBoard.domain.Heart;
import Ver2.myBoard.domain.HeartStatus;
import Ver2.myBoard.heart.dto.HeartRequestDto;
import Ver2.myBoard.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HeartController {

    private final HeartService heartService;
    private final BoardService boardService;

    @PostMapping("/heart")
    public Map<String, Object> postHeart(@RequestBody HeartRequestDto requestDto) {
        Map<String, Object> map = new HashMap<>();
        String result;
        Board board = boardService.getBoardById(requestDto.getBoardId());
        try {
            // 좋아요(like), 싫어요(dislike), 좋아요 취소(removeLike), 싫어요 취소(removeDislike)
            log.info("HeartRequestDto {}", requestDto.toString());
            if (requestDto.getStatus().equals("like") || requestDto.getStatus().equals("dislike")) {
                Heart heart = heartService.findHeart(requestDto);
                if (heart != null) {
                    result="duplicate"; // 중복
                } else {
                    heartService.addHeart(requestDto);
                    result = "success";
                }
            } else {
                heartService.removeHeart(requestDto);
                result = "success";
            }
            map.put("result", result);
            map.put("likeCnt", board.getGood());
            map.put("dislikeCnt", board.getBad());

            return map;
        } catch (Exception e) {
            result = "fail";
            map.put("result", result);
            return map;
        }
    }

    @GetMapping("/heart/check/{memberId}/{boardId}")
    public Map<String, Object> checkHeart(@PathVariable("memberId") String memberId,
                                          @PathVariable("boardId") Long boardId) {
        log.info("/heart/check/{}/{}", memberId, boardId);
        Map<String, Object> map = new HashMap<>();

        String status="void";
        List<Heart> hearts = heartService.findHeartById(memberId, boardId);
        Board board = boardService.getBoardById(boardId);

        log.info("hearts {}", hearts.size());

        if (hearts.size() == 0) {
            status = "double void";
        } else if (hearts.size() == 1) {
            Heart heart = hearts.get(0);
            if (heart.getHeartStatus() == HeartStatus.GOOD) {
                status = "like existence";
            } else {
                status = "dislike existence";
            }
        } else {
            status = "double existence";
        }

        map.put("status", status);
        map.put("likeCnt", board.getGood());
        map.put("dislikeCnt", board.getBad());

        return map;
    }
}
