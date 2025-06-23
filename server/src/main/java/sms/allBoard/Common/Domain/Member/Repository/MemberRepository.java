package sms.allBoard.Common.Domain.Member.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sms.allBoard.Common.Domain.Member.Model.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByUsername(String username);
    boolean existsMemberByEmail(String email);
    Optional<Member> findByUsername(String username);
}
