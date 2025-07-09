package com.allboard.auth.repository;

import com.allboard.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member ,Long> {
    boolean existsMemberByUsername(String username);
    boolean existsMemberByEmail(String email);
    Optional<Member> findByUsername(String username);
}
