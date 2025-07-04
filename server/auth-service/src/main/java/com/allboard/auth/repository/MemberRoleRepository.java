package com.allboard.auth.repository;

import com.allboard.auth.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}
