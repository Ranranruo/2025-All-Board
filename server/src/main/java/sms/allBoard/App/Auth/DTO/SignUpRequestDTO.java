package sms.allBoard.App.Auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String displayName;
    private String password;
    private String email;
    private String verificationCode;
}
