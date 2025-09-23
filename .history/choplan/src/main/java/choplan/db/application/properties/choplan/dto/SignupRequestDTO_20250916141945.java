package choplan.db.application.properties.choplan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String email;
    private String password;
    private String realName;
    private String phone;
    private String nickname;
    private String role;  // CUSTOMER / OWNER / ADMIN
}
