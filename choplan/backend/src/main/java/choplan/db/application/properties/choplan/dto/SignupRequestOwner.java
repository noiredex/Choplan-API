package choplan.db.application.properties.choplan.dto;

import choplan.db.application.properties.choplan.entity.StoreAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

/**
 * OWNER(가게 사장님) 회원가입 요청 DTO
 */
@Getter
@Setter
public class SignupRequestOwner {

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 10, message = "비밀번호는 최소 10자리 이상이어야 합니다.")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[A-Z]).{10,}$",
        message = "비밀번호에는 숫자와 대문자가 최소 1개 이상 포함되어야 합니다."
    )
    private String password;

    @NotBlank(message = "사장님 이름은 필수 입력 항목입니다.")
    @Pattern(
        regexp = "^[가-힣]{2,}$",
        message = "이름은 한글만 입력할 수 있습니다."
    )
    private String realName;

    @NotBlank(message = "사장님 휴대전화번호는 필수 입력 항목입니다.")
    @Pattern(
        regexp = "^[0-9]{10,11}$",
        message = "전화번호는 숫자만 입력 가능하며 10~11자리여야 합니다."
    )
    private String phone;

    @NotBlank(message = "가게 이름은 필수 입력 항목입니다.")
    private String storeName;

    @NotBlank(message = "가게 전화번호는 필수 입력 항목입니다.")
    @Pattern(
        regexp = "^[0-9]{9,11}$",
        message = "가게 전화번호는 숫자만 입력 가능하며 9~11자리여야 합니다."
    )
    private String storePhone;

    private StoreAddress storeAddress; // 매장 주소 (도로명, 상세주소, 우편번호)

    @NotBlank(message = "사업자등록번호는 필수 입력 항목입니다.")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "사업자등록번호는 숫자 10자리여야 합니다."
    )
    private String businessRegistrationNumber;

    @NotBlank(message = "사업자등록증 파일은 필수입니다.")
    private MultipartFile businessRegistrationDoc
