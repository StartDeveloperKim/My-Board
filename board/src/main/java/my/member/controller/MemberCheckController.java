package my.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.member.repository.MemberRepository;
import my.member.service.MemberService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberCheckController {

    private final MemberService memberService;

    // 왜 Post로 전송을 해도 JSON을 받지 못하는 걸까? 쉽지않구만

    @PostMapping("/checking")
    public int checkId(@RequestBody HashMap<String, String> map) {
        // 존재하는 아이디 false, 존재하지않는 아이디 true
        log.info("checkId = {}", map.get("id"));
        if (memberService.checkId(map.get("id"))) {
            return 1;
        } else {
            return 0;
        }
    }

    /*@GetMapping("/checking")
    public int checkId(@RequestParam String id) {
        // 존재하는 아이디 false, 존재하지않는 아이디 true
        log.info("checkId = {}", id);
        if (memberService.checkId(id)) {
            return 1;
        } else {
            return 0;
        }
    }*/
}
