package com.allboard.auth.service.impl;

import com.allboard.auth.dto.SignUpRequestDTO;
import com.allboard.auth.entity.Member;
import com.allboard.auth.entity.MemberRole;
import com.allboard.auth.entity.Role;
import com.allboard.auth.repository.MemberRepository;
import com.allboard.auth.repository.MemberRoleRepository;
import com.allboard.auth.repository.RoleRepository;
import com.allboard.auth.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final MemberRoleRepository memberRoleRepository;

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

        MemberRole memberRoleBridge = MemberRole.builder()
                .memberId(memberId)
                .roleId(roleId)
                .build();

        memberRoleRepository.save(memberRoleBridge);
    }


}