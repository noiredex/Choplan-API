package choplan.db.application.properties.choplan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import choplan.db.application.properties.choplan.dto.AuthResponse;

@RestController
public class TestController {
    
    @GetMapping("/customer/test")
    public ResponseEntity<AuthResponse> customerTest() {
        return ResponseEntity.ok(new AuthResponse(200, "CUSTOMER 권한 접근 성공", null));
    }

    @GetMapping("/owner/test")
    public ResponseEntity<AuthResponse> ownerTest() {
        return ResponseEntity.ok(new AuthResponse(200, "OWNER 권한 접근 성공", null));
    }

    @GetMapping("/admin/test")
    public ResponseEntity<AuthResponse> adminTest() {
        return ResponseEntity.ok(new AuthResponse(200, "ADMIN 권한 접근 성공", null));
    }
}
