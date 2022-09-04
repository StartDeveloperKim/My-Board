package my.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.member.SessionConst;
import my.member.domain.Member;
import my.member.domain.MemberLoginDto;
import my.member.domain.MemberRegisterDto;
import my.member.repository.MemberRepository;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String registerMember_form(Model model) {
        model.addAttribute("member", new MemberRegisterDto());
        return "/member/regist";
    }

    @PostMapping
    public String registerMember(@Validated @ModelAttribute("member") MemberRegisterDto registerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("/member error = {}", bindingResult.getAllErrors());
            return "/member/regist";
        }

        // MemberRegisterDto --> Member 변환
        Member member = new Member(registerDto.getId(), registerDto.getPassword(), registerDto.getName(), registerDto.getNickname());

        memberRepository.insert(member);
        redirectAttributes.addFlashAttribute("member", member);
        /*addFlashAttribute는 아직 지식이 부족하다 다음에 좀 더 공부를 해보자*/

        return "redirect:/tw";
    }

    @GetMapping("/login")
    public String loginFrom(Model model) {
        model.addAttribute("loginForm", new MemberLoginDto());
        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute MemberLoginDto loginDto, BindingResult bindingResult,
                        @RequestParam(required = false, defaultValue = "/") String redirectURL,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("/login error = {}", bindingResult.getAllErrors());
            return "/member/loginForm";
        }

        Member loginMember = memberRepository.selectByIdandPassword(loginDto);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/loginForm";
        }

        // request.getSession은 기본이 true이다. 이 경우 session이 없다면 생성하고 있으면 반환한다.
        // false로 설정한다면 없어도 세션이 생성되지는 않는다.
        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/tw";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션을 제거
        }

        return "redirect:/tw";
    }
}
