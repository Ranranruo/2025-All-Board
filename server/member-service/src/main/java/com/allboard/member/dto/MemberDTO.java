package com.allboard.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private long id;
    private String username;
    private String email;
    private String displayName;
    private String password;
}
