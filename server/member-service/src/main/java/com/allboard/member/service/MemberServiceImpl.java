package com.allboard.member.service;

import com.allboard.member.dto.MemberDTO;
import com.allboard.member.model.Member;
import com.allboard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        Member member = new Member();
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());
        member.setEmail(memberDTO.getEmail());
        member.setDisplayName(memberDTO.getDisplayName());

        memberRepository.save(member);
    }
}
