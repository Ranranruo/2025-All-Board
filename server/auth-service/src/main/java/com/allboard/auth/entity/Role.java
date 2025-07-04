package com.allboard.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "role_id")
    private Long roleId;
}