package choplan.db.application.properties.choplan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestCustomer;
import choplan.db.application.properties.choplan.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerAuthController {

    private final CustomerService customerService;

    public CustomerAuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequestCustomer request) {
        return ResponseEntity.ok(customerService.signup(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }
}
