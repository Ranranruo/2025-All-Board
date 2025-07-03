package com.allboard.member.repository;

import com.allboard.member.dto.MemberDTO;
import com.allboard.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByUsername(String username);
    boolean existsMemberByEmail(String email);
}
