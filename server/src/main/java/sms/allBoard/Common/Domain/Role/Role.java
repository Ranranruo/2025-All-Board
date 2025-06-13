package sms.allBoard.Common.Domain.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description")
    private String description;
}
