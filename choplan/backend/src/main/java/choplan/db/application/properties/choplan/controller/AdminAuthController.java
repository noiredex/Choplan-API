package choplan.db.application.properties.choplan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestAdmin;
import choplan.db.application.properties.choplan.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    private final AdminService adminService;

    public AdminAuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequestAdmin request) {
        return ResponseEntity.ok(adminService.signup(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(adminService.login(request));
    }
}
