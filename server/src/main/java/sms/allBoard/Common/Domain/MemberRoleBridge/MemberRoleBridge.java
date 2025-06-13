package sms.allBoard.Common.Domain.MemberRoleBridge;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Member_Role_bridge")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MemberRoleBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "role_id")
    private Long roleId;
}