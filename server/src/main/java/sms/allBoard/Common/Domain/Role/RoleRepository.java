package sms.allBoard.Common.Domain.Role;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
    @Query(value = """
    SELECT
        r.*
    FROM
        Role r
        INNER JOIN
        Member_Role_Bridge mrb
        ON
        r.id = mrb.role_id
    WHERE
        mrb.member_id = :id
    """, nativeQuery = true)
    Set<Role> findAllByMemberId(@Param("id") Long id);
}
