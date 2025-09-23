package choplan.db.application.properties.choplan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private int status;
    private String message;
    private Object data;
}
