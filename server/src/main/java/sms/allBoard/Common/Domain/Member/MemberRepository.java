package sms.allBoard.Common.Domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByUsername(String username);
    boolean existsMemberByEmail(String email);
    Optional<Member> findByUsername(String username);
}
