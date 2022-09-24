package Ver2.myBoard.reply.controller;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.domain.Reply;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.reply.dto.ReplyRegisterDto;
import Ver2.myBoard.reply.dto.ReplyShowDto;
import Ver2.myBoard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/show/{id}")
    public Map<String, Object> showReply(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberLoginDto loginDto,
                                         @PathVariable("id") Long id) {
        List<Reply> listReply = replyService.getListReply(id);
        Map<String, Object> map = new HashMap<>();

        map.put("list", listReply.stream()
                .map(ReplyShowDto::new)
                .collect(Collectors.toList()));
        map.put("total", listReply.size());

        return map;
    }

    @PostMapping("/new")
    public String registerReply(@RequestBody ReplyRegisterDto replyRegisterDto) {
        // 댓글을 등록하면 등록된 댓글을 포함해서 다시 댓글리스트를 보여줘야하기에 ReplyShowDto를 전송.
        //log.info("댓글 정보 {}, {}, {}", replyRegisterDto.getComment(), replyRegisterDto.getMemberId(), replyRegisterDto.getBoardId());
        //log.info("전달된 댓글 {}", replyRegisterDto.toString());
        try {
            replyService.saveReply(replyRegisterDto);
            return "success";
        } catch (Exception e) {
            return "fail";
        }


        // 현재 댓글은 잘 넘어간다. FK도 잘 매칭된다. 하지만 댓글이 리스트 형식으로 넘어가서 좀 그렇다 영한님이 이게 별로 좋지 않다고 했다.
        // 2022-09-24 => 댓글 view를 만들고 JSON 형식으로 넘어갈 수 있도록 해보자
        // fetch join을 생각해보자 getMeber.getNickname을 할 때 아마 쿼리가 더 넘어갈 것이다. -> 성능 악화
    }

    @PostMapping("/{id}/remove")
    public String removeReply(@PathVariable("id") Long id) {
        try {
            replyService.removeReply(id);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }
}
