package com.allboard.member.controller;

import com.allboard.member.dto.ExistsMemberRequestDTO;
import com.allboard.member.dto.ExistsMemberResponseDTO;
import com.allboard.member.dto.MemberDTO;
import com.allboard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/exists")
    public ExistsMemberResponseDTO exists(@RequestBody ExistsMemberRequestDTO requestBody) {
        ExistsMemberResponseDTO responseBody = new ExistsMemberResponseDTO();

        boolean existsUsername = memberService.existsMemberByUsername(requestBody.getUsername());
        boolean existsEmail = memberService.existsMemberByEmail(requestBody.getEmail());

        responseBody.setExistsUsername(existsUsername);
        responseBody.setExistsEmail(existsEmail);

        return responseBody;
    }


    @PostMapping("/")
    public void insert(
            @RequestBody MemberDTO memberDTO
    ) {
        memberService.save(memberDTO);
    }
}
