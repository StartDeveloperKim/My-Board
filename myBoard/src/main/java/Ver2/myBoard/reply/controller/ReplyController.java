package Ver2.myBoard.reply.controller;

import Ver2.myBoard.domain.Reply;
import Ver2.myBoard.reply.dto.ReplyRegisterDto;
import Ver2.myBoard.reply.dto.ReplyShowDto;
import Ver2.myBoard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/new")
    public List<ReplyShowDto> registerReply(@RequestBody ReplyRegisterDto replyRegisterDto) {
        // 댓글을 등록하면 등록된 댓글을 포함해서 다시 댓글리스트를 보여줘야하기에 ReplyShowDto를 전송.
        //log.info("댓글 정보 {}, {}, {}", replyRegisterDto.getComment(), replyRegisterDto.getMemberId(), replyRegisterDto.getBoardId());
        replyService.saveReply(replyRegisterDto);

        List<Reply> listReply = replyService.getListReply(replyRegisterDto.getBoardId());
        log.info("listReply {}", listReply);
        
        // 현재 댓글은 잘 넘어간다. FK도 잘 매칭된다. 하지만 댓글이 리스트 형식으로 넘어가서 좀 그렇다 영한님이 이게 별로 좋지 않다고 했다.
        // 2022-09-24 => 댓글 view를 만들고 JSON 형식으로 넘어갈 수 있도록 해보자

        return listReply.stream().map(reply -> new ReplyShowDto(reply.getComment(), reply.getMember().getNickname(),
                reply.getRegDate())).collect(Collectors.toList());
    }

    @PostMapping("/{id}/remove")
    public List<ReplyShowDto> removeReply(@PathVariable("id") Long id) {

        return null;
    }
}
