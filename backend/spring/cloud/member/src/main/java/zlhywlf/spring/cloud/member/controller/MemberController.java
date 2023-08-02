package zlhywlf.spring.cloud.member.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zlhywlf.spring.cloud.member.service.MemberService;

@AllArgsConstructor
@RestController
public class MemberController {

    private MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "hello world: " + memberService.count();
    }

}