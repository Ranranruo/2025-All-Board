package com.allboard.member.controller;

import com.allboard.member.dto.MemberDTO;
import com.allboard.member.service.MemberService;
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
