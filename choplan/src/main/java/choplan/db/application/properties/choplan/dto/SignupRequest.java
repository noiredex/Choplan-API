package choplan.db.application.properties.choplan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = " 올바른 이메일 형식이여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String realName;

    @NotBlank(message = "휴대폰 번호는 필수 입력값입니다.")
    private String phone;

    private String nickname; //CUSTOMER 전용

    private String businessRegistrationDoc; //OWNER 전용
    private String storeName; //OWNWER 전용
    private String storePhone; //OWNER 전용

    private String role;  // CUSTOMER / OWNER / ADMIN

    // Getters n Setters
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }

public String getRealName() { return realName; }
public void setRealName(String realName) { this.realName = realName; }

public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }

public String getNickname() { return nickname; }
public void setNickname(String nickname) { this.nickname = nickname; }

public String getBusinessRegistrationDoc() { return businessRegistrationDoc; }
public void setBusinessRegistrationDoc(String businessRegistrationDoc) { this.businessRegistrationDoc = businessRegistrationDoc; }

public String getStoreName() {return storeName;}
public void setStoreName(String storeName) { this.storeName = storeName; }

public String getStorePhone() { return storePhone; }
public void setStorePhone(String storePhone) { this.storePhone = storePhone;}

public String getRole() { return role; }
public void setRole(String role) { this.role = role; }

}
