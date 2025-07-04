package com.allboard.auth.repository;

import com.allboard.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
