package com.allboard.role.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MemberRole {
    @Id
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "role_id")
    private Long roleId;
}
