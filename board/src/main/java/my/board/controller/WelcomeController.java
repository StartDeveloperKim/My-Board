package my.board.controller;

import lombok.RequiredArgsConstructor;
import my.board.service.interfaces.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private final BoardService boardService;

    @GetMapping("/tw")
    public String welcome(Model model) {
        /*글 10개 정도 보여주고 밑에 더보기 링크 버튼*/
        model.addAttribute("list", boardService.getBoardListTen());
        return "welcome";
    }
}
