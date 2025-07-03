package com.allboard.member.service;

import com.allboard.member.dto.MemberDTO;

public interface MemberService {
    void save(MemberDTO memberDTO);
    boolean existsMemberByUsername(String username);
    boolean existsMemberByEmail(String email);
}
