package zlhywlf.spring.cloud.member.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zlhywlf.spring.cloud.member.model.Member;
import zlhywlf.spring.cloud.member.request.MemberRequest;
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

    @PostMapping("/register")
    public Long register(@Valid MemberRequest request) {
        return memberService.register(request);
    }
}