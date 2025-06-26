package sms.allBoard.Common.Security.Details;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sms.allBoard.App.Auth.DTO.SignInResponseDTO;
import sms.allBoard.App.Auth.Exception.SignInException;
import sms.allBoard.Common.Domain.Member.Model.Member;
import sms.allBoard.Common.Domain.Member.Repository.MemberRepository;
import sms.allBoard.Common.Domain.Role.Model.Role;
import sms.allBoard.Common.Domain.Role.Repository.RoleRepository;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if (optionalMember.isEmpty()) {
            SignInResponseDTO responseBody = new SignInResponseDTO();
            responseBody.setUsername(FieldStatus.NOT_FOUND);
            responseBody.setPassword(FieldStatus.SUCCESS);
            throw new SignInException(ResponseStatus.NOT_FOUND, responseBody);
        }
        Member member = optionalMember.get();

        Set<Role> roles = roleRepository.findAllByMemberId(member.getId());
        return new MemberDetails(member, roles);
    }
}