package com.allboard.member.Controller;

import com.allboard.member.DTO.MemberDTO;
import com.allboard.member.Model.Member;
import com.allboard.member.Repository.MemberRepository;
import com.allboard.member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/")
    public void insert(
            @RequestBody MemberDTO memberDTO
    ) {
        memberService.save(memberDTO);
    }
}
