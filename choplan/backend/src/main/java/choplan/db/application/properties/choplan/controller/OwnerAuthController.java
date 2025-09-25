package choplan.db.application.properties.choplan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestOwner;
import choplan.db.application.properties.choplan.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerAuthController {

    private final OwnerService ownerService;

    public OwnerAuthController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // 회원가입 (파일 업로드 포함)
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestPart("data") SignupRequestOwner request,
            @RequestPart("file") MultipartFile businessRegistrationDoc) {

        return ResponseEntity.ok(ownerService.signup(request, businessRegistrationDoc));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(ownerService.login(request));
    }
}
