package choplan.db.application.properties.choplan.controller;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestCustomer;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.entity.CustomerStatus;
import choplan.db.application.properties.choplan.service.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/customer")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final CustomerService customerService;

    /**
     * CUSTOMER 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupCustomer(@RequestBody SignupRequestCustomer request) {
        try {
            Users savedCustomer = customerService.registerCustomer(request);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "CUSTOMER 회원가입 성공",
                            java.util.Map.of(
                                    "userId", savedCustomer.getUserId(),
                                    "email", savedCustomer.getEmail(),
                                    "role", savedCustomer.getRole().name(),
                                    "customerStatus", savedCustomer.getCustomerStatus().name()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    /**
     * CUSTOMER 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(customerService.login(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    /**
     * CUSTOMER 계정 상태 변경 (관리자 전용)
     */
    @PatchMapping("/status/{customerId}")
    public ResponseEntity<AuthResponse> updateStatus(
            @PathVariable Long customerId,
            @RequestParam CustomerStatus status) {
        try {
            Users updatedCustomer = customerService.updateCustomerStatus(customerId, status);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "CUSTOMER 상태 변경 완료",
                            java.util.Map.of(
                                    "userId", updatedCustomer.getUserId(),
                                    "email", updatedCustomer.getEmail(),
                                    "role", updatedCustomer.getRole().name(),
                                    "customerStatus", updatedCustomer.getCustomerStatus().name()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }
}
