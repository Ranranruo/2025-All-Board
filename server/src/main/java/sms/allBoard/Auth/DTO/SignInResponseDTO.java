package sms.allBoard.Auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDTO {
    private FieldStatus username;
    private FieldStatus password;
}