package my.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.member.SessionConst;
import my.member.domain.Member;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberInfoChangeController {

    private final MemberService memberService;

    @GetMapping("/{userId}")
    public String changeForm(@PathVariable("userId") String userId,
                             @RequestParam("info") String info,
                             Model model) {
        model.addAttribute("member", memberService.selectById(userId));

        if (info.equals("password")) {
            model.addAttribute("memberChangePw", new MemberChangePwDto());
            log.info("Change PasswordForm={}", info);
            return "/member/changePwForm";
        } else if (info.equals("nickname")) {
            model.addAttribute("memberChangeNickname", new MemberChangeNicknameDto());
            log.info("Change NicknameForm={}", info);
            return "/member/changeNicknameForm";
        } else {
            return "redirect:/tw"; // 잘못된 요청이다. 나중에 스프링으로 처리해보자.
        }
    }

    @PostMapping("/{userId}/password")
    public String changePassword(@PathVariable String userId,
                                 @ModelAttribute("memberChangePw") MemberChangePwDto changePwDto,
                                 RedirectAttributes redirectAttributes) {
        if (memberService.updatePassword(userId, changePwDto) == 1) {
            redirectAttributes.addAttribute("changeStatus", true);
            return "redirect:/tw";
        } // 나중에 오류 처리를 하자

        return "/welcome";
    }

    @PostMapping("/{userId}/nickname")
    public String changeNickname(@PathVariable String userId,
                                 @ModelAttribute("memberChangeNickname") MemberChangeNicknameDto changeNicknameDto,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        log.info("닉네임변경={}", changeNicknameDto.toString());

        if (memberService.updateNickname(userId, changeNicknameDto) == 1) {
            redirectAttributes.addAttribute("changeStatus", true);
            ChangeSessionInfo(changeNicknameDto.getNickname(), "nickname", request);
            return "redirect:/tw";
        } // 나중에 오류 처리를 하자

        return "/welcome";
    }

    private void ChangeSessionInfo(String info, String infoName, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (infoName.equals("nickname")) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            member.setNickname(info);
            session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        }
    }
}
