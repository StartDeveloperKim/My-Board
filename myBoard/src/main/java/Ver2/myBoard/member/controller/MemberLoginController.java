package Ver2.myBoard.member.controller;

import Ver2.myBoard.domain.Member;
import Ver2.myBoard.member.SessionConst;
import Ver2.myBoard.member.dto.MemberLoginDto;
import Ver2.myBoard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberLoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberLogin", new MemberLoginDto());
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("memberLogin") MemberLoginDto loginDto,
                        BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "member/loginForm";
        }

        if (!memberService.loginCheck(loginDto, bindingResult)) {
            return "member/loginForm";
        } else {
            Member findMember = memberService.getMemberById(loginDto.getId());
            loginDto.setNickname(findMember.getNickname());
        }
        log.info("로그인 오류 x");
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("로그아웃");
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
