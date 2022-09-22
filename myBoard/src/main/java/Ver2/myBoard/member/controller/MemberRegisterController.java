package Ver2.myBoard.member.controller;

import Ver2.myBoard.member.dto.MemberRegisterDto;
import Ver2.myBoard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberRegisterController {

    private final MemberService memberService;

    @GetMapping
    public String memberRegisterForm(Model model) {
        model.addAttribute("memberReg", new MemberRegisterDto());

        return "member/registerForm";
    }

    @PostMapping
    public String memberRegister(@Validated @ModelAttribute("memberReg") MemberRegisterDto registerDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/registerForm";
        }
        if (!registerDto.checkPassword()) {
            bindingResult.addError(new FieldError("memberReg", "confirmPassword", "비밀번호와 확인 비밀번호가 일치하지 않습니다.!"));
            return "member/registerForm";
        }

        memberService.insert(registerDto);
        return "redirect:/";
    }
}
