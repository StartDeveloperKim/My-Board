package my.board.controller;

import lombok.RequiredArgsConstructor;
import my.board.domain.Member;
import my.board.repository.interfaces.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String registerMember_form() {
        return "member/regist";
    }

    @PostMapping
    public String registerMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        memberRepository.insert(member);
        redirectAttributes.addFlashAttribute("member",member);
        /*addFlashAttribute는 아직 지식이 부족하다 다음에 좀 더 공부를 해보자*/

        return "redirect:/member/success";
    }

    @GetMapping("/success")
    public String successRegister() {
        /*addFlashAttribute를 사용하면 redirect된 view에서 사용이 가능한 것인가?*/
        return "member/success";
    }
}
