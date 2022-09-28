package Ver2.myBoard.myPage.controller;

import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.myPage.dto.MyPageBoardDto;
import Ver2.myBoard.myPage.dto.MyPageDto;
import Ver2.myBoard.myPage.dto.MyPageMemberDto;
import Ver2.myBoard.myPage.service.MyPageService;
import Ver2.myBoard.paging.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class MyPageController {

    private final MyPageService myPageService;
    private final BoardService boardService;

    @GetMapping("/{id}")
    public String myPageForm(@PathVariable("id") String id,
                             Model model) {
        MyPageMemberDto myPageInfo = myPageService.getMyPageInfo(id);
        model.addAttribute("myPageInfo", myPageInfo);
        model.addAttribute("memberId", id);

        return "myPage/myPageForm";
    }

    @GetMapping("/board/{id}/{pageNum}")
    @ResponseBody
    public Map<String, Object> myPageBoard(@PathVariable("id") String id,
                                           @PathVariable(value = "pageNum", required = false) int pageNum) {
        Map<String, Object> map = new HashMap<>();
        log.info("/board/{}/{}", id, pageNum);

        Long total = boardService.getTotalById(id);
        PageDto page = new PageDto(pageNum, total);
        //log.info("/users/board {}", boardService.getTotalById(pageDto.getId()));

        List<MyPageBoardDto> board = myPageService.getMyPageBoard(id, pageNum);
        map.put("page", page);
        map.put("board", board);
        map.put("total", total);

        return map;
    }
}
