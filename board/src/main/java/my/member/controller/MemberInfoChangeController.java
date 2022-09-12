package my.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.member.SessionConst;
import my.member.domain.Member;
import my.member.domain.MemberChangeNicknameDto;
import my.member.domain.MemberChangePwDto;
import my.member.service.MemberService;
import my.member.validator.PasswordValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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

    /*@InitBinder
    public void init(WebDataBinder dataBinder) {
        log.info("init binder {}", dataBinder);

        dataBinder.addValidators(PasswordValidator);
    }*/

    @GetMapping("/{userId}")
    public String changeForm(@PathVariable("userId") String userId,
                             @RequestParam("info") String info,
                             Model model) {
        model.addAttribute("userId", memberService.selectById(userId).getId());

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
                                 @Validated @ModelAttribute("memberChangePw") MemberChangePwDto changePwDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        if (!changePwDto.getPassword().equals(changePwDto.getConfirmPassword())) {
            bindingResult.addError(new FieldError("memberChangePw", "confirmPassword", "비밀번호와 확인 비밀번호가 일치하지 않습니다."));
        }

        if (bindingResult.hasErrors()) {
            log.info("Change Password Error : {}", bindingResult.getAllErrors());
            return "/member/changePwForm";
        }

        if (memberService.updatePassword(userId, changePwDto) == 1) {
            redirectAttributes.addAttribute("changeStatus", true);
            ChangeSessionInfo(changePwDto.getPassword(), "password", request);
            return "redirect:/tw";
        } // 나중에 오류 처리를 하자

        return "/welcome";
    }

    @PostMapping("/{userId}/nickname")
    public String changeNickname(@PathVariable String userId,
                                 @Validated @ModelAttribute("memberChangeNickname") MemberChangeNicknameDto changeNicknameDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("Change Nickname Error : {}", bindingResult.getAllErrors());
            return "/member/changeNicknameForm";
        }

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
        if (session != null) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            if (infoName.equals("nickname")) {
                member.setNickname(info);
            } else if (infoName.equals("password")) {
                member.setPassword(info);
            }
            session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        }

    }
}
