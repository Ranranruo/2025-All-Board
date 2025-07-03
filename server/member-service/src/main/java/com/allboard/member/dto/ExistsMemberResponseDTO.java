package com.allboard.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExistsMemberResponseDTO {
    private boolean existsUsername;
    private boolean existsEmail;
}
