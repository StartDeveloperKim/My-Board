package Ver2.myBoard;

import Ver2.myBoard.board.service.BoardService;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WelcomeController {

    @GetMapping("/")
    public String welcomeForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberLoginDto loginDto, Model model) {
        log.info("loginDto {}", loginDto);
        model.addAttribute("loginMember", loginDto);

        return "home";
    }
}
