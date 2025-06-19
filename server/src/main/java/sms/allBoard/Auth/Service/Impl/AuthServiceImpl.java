package sms.allBoard.Auth.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sms.allBoard.Auth.DTO.SignUpRequestDTO;
import sms.allBoard.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Service.AuthService;
import sms.allBoard.Common.Domain.Member.Member;
import sms.allBoard.Common.Domain.Member.MemberRepository;
import sms.allBoard.Common.Domain.MemberRoleBridge.MemberRoleBridge;
import sms.allBoard.Common.Domain.MemberRoleBridge.MemberRoleBridgeRepository;
import sms.allBoard.Common.Domain.Role.Role;
import sms.allBoard.Common.Domain.Role.RoleRepository;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;

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
