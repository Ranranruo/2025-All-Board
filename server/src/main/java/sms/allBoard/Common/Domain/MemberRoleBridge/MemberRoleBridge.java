package sms.allBoard.Common.Domain.MemberRoleBridge;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "Member_Role_bridge")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@SQLDelete(sql = "UPDATE Member SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
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