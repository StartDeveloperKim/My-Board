package my;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.board.service.interfaces.BoardService;
import my.member.SessionConst;
import my.member.domain.jpaDomain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WelcomeController {

    private final BoardService boardService;

    @GetMapping("/tw")
    public String welcome(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        //글 10개 정도 보여주고 밑에 더보기 링크 버튼
        model.addAttribute("list", boardService.getBoardListTen());

        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            model.addAttribute("member", null);
            return "welcome";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        return "welcome";
    }
}
