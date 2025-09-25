package choplan.db.application.properties.choplan.controller;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequest;
import choplan.db.application.properties.choplan.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // CUSTOMER 회원가입
    @PostMapping("/signup/customer")
    public ResponseEntity<AuthResponse> signupCustomer(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.signupCustomer(request));
    }

    // OWNER 회원가입
    @PostMapping("/signup/owner")
    public ResponseEntity<AuthResponse> signupOwner(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.signupOwner(request));
    }

    // ADMIN 회원가입
    @PostMapping("/signup/admin")
    public ResponseEntity<AuthResponse> signupAdmin(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.signupAdmin(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
