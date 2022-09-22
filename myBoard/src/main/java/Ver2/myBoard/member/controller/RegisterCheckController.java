package Ver2.myBoard.member.controller;

import Ver2.myBoard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterCheckController {

    private final MemberService memberService;

    @PostMapping("/checking")
    public int checkId(@RequestBody HashMap<String, String> map) {
        if (memberService.checkId(map.get("id"))) {
            return 1;
        } else {
            return 0;
        }
    }
}
