package zlhywlf.spring.cloud.member.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zlhywlf.spring.cloud.member.model.Member;
import zlhywlf.spring.cloud.member.service.MemberService;

import java.util.List;

@AllArgsConstructor
@RestController
public class MemberController {
    private MemberService memberService;

    @GetMapping("/")
    public List<Member> home() {
        return memberService.selectAll();
    }
}