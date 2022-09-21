package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardGetController {

    private final BoardService boardService;

    @GetMapping
    public String boardForm(Model model) {
        // 내가 만든 게시판은 그 구성이 매우 간단하기에 Entity를 직접 넘기지만
        // 원래는 Entity를 DTO로 변환하여 넘기는 것이 안전하다.
        // 이때 N+1의 문제가 발생 --> 성능이 굉장히 떨어질 수 있다. --> fetch Join 사용
        return "board/list";
    }
}
