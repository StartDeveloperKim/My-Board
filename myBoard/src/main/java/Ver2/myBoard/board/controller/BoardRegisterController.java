package Ver2.myBoard.board.controller;

import Ver2.myBoard.board.dto.BoardRegisterDto;
import Ver2.myBoard.board.repository.BoardRepository;
import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
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
    public String BoardRegister(@Validated @ModelAttribute("board") BoardRegisterDto registerDto, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "board/registerForm";
        }
        try {
            boardService.insertBoard(registerDto);
            redirectAttributes.addFlashAttribute("registerStatus", true);
        } catch (Exception e) {
            log.info("오류가 발생");
        }
        return "redirect:/board";
    }
}
