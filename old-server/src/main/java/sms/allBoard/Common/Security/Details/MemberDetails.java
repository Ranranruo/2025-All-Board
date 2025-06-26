package sms.allBoard.Common.Security.Details;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import sms.allBoard.Common.Domain.Member.Model.Member;
import sms.allBoard.Common.Domain.Role.Model.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class MemberDetails extends User {
    private final String displayName;
    private final String email;
    public MemberDetails(Member member, Set<Role> roles) {
        super(
                member.getUsername(),
                member.getPassword(),
                roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet())
        );
        this.displayName = member.getDisplayName();
        this.email = member.getEmail();
    }
}