package choplan.db.application.properties.choplan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestAdmin;
import choplan.db.application.properties.choplan.entity.CustomerStatus;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.service.AdminService;
import choplan.db.application.properties.choplan.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminService adminService;
    private final CustomerService customerService;

    // ADMIN 회원가입
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupAdmin(@RequestBody SignupRequestAdmin request) {
        try {
            Users savedAdmin = adminService.registerAdmin(request);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "ADMIN 회원가입 성공",
                            java.util.Map.of(
                                    "userId", savedAdmin.getUserId(),
                                    "email", savedAdmin.getEmail(),
                                    "role", savedAdmin.getRole().name()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    // ADMIN 로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(adminService.login(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    // OWNER 승인
    @PatchMapping("/approve-owner/{ownerId}")
    public ResponseEntity<AuthResponse> approveOwner(@PathVariable Long ownerId) {
        try {
            Users approvedOwner = adminService.approveOwner(ownerId);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "OWNER 승인 완료",
                            java.util.Map.of(
                                    "userId", approvedOwner.getUserId(),
                                    "email", approvedOwner.getEmail(),
                                    "role", approvedOwner.getRole().name(),
                                    "ownerStatus", approvedOwner.getOwnerStatus().name()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    // CUSTOMER 상태 변경
    @PatchMapping("/customer-status/{customerId}")
    public ResponseEntity<AuthResponse> updateCustomerStatus(
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
