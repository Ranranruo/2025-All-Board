package sms.allBoard.App.Auth.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sms.allBoard.App.Auth.DTO.SignUpRequestDTO;
import sms.allBoard.App.Auth.Service.AuthService;
import sms.allBoard.Common.Domain.Member.Model.Member;
import sms.allBoard.Common.Domain.Member.Repository.MemberRepository;
import sms.allBoard.Common.Domain.MemberRoleBridge.Model.MemberRoleBridge;
import sms.allBoard.Common.Domain.MemberRoleBridge.Repository.MemberRoleBridgeRepository;
import sms.allBoard.Common.Domain.Role.Model.Role;
import sms.allBoard.Common.Domain.Role.Repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MemberRoleBridgeRepository memberRoleBridgeRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean isExistsUsername(String username) {
        return memberRepository.existsMemberByUsername(username);
    }

    public boolean isExistsEmail(String email) {
        return memberRepository.existsMemberByEmail(email);
    }

    @Transactional
    public void signUp(SignUpRequestDTO signUpRequestDTO) {

        String password = passwordEncoder.encode(signUpRequestDTO.getPassword());

        Member member = Member.builder()
                .username(signUpRequestDTO.getUsername())
                .password(password)
                .email(signUpRequestDTO.getEmail())
                .displayName(signUpRequestDTO.getDisplayName())
                .build();
        Role role = roleRepository.findByName("MEMBER");
        memberRepository.save(member);

        Long memberId = member.getId();
        Long roleId = role.getId();

        MemberRoleBridge memberRoleBridge = MemberRoleBridge.builder()
                .memberId(memberId)
                .roleId(roleId)
                .build();

        memberRoleBridgeRepository.save(memberRoleBridge);
    }


}
