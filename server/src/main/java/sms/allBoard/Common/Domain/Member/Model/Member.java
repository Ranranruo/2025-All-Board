package sms.allBoard.Common.Domain.Member.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "Member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Member SET deleted_at = CURRENT_TIMESTAMP() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "password")
    private String password;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
