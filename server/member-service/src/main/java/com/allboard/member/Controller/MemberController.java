package com.allboard.member.Controller;

import com.allboard.member.Model.Member;
import com.allboard.member.Repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/test")
    public void test() {
        Member member = new Member();
        member.setEmail("email");
        member.setUsername("username");
        member.setPassword("password");
        member.setDisplayName("displayName");
        memberRepository.save(member);
    }
}
