package com.allboard.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "member_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@SQLDelete(sql = "UPDATE member_role SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "role_id")
    private Long roleId;
}