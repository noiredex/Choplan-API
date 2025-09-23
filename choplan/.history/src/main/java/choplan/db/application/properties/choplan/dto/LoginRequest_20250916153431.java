package choplan.db.application.properties.choplan.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;


@Getter
@Setter
public class LoginRequest {

    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

}
