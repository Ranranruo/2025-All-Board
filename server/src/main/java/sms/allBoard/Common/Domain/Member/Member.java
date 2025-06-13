package sms.allBoard.Common.Domain.Member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "Member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
