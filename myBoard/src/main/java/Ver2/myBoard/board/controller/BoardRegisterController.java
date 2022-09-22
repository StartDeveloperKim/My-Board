package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardRegisterController {

    private final BoardService boardService;

    @GetMapping("/new")
    public String BoardRegisterForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberLoginDto loginDto,
                                    Model model) {
        model.addAttribute("board", new BoardRegisterDto(loginDto.getNickname()));

        return "board/registerForm";
    }

    @PostMapping("/new")
    public String BoardRegister(@Validated @ModelAttribute("board") BoardRegisterDto registerDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "board/registerForm";
        }

        boardService.insertBoard(registerDto);
        return "redirect:/board";
    }
}
