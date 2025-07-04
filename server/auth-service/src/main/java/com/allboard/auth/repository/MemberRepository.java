package com.allboard.auth.repository;

import com.allboard.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member ,Long> {
}
