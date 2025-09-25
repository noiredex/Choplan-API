package choplan.db.application.properties.choplan.controller;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.SignupRequestOwner;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.service.OwnerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth/owner")
@RequiredArgsConstructor
public class OwnerAuthController {

    private final OwnerService ownerService;

    /**
     * OWNER 회원가입
     * 사업자등록증 파일 업로드 필요
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupOwner(
            @ModelAttribute SignupRequestOwner request,
            @RequestParam("businessDoc") MultipartFile businessDoc) {

        try {
            Users savedOwner = ownerService.registerOwner(request, businessDoc);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "OWNER 회원가입 성공 (관리자 승인 대기중)",
                            java.util.Map.of(
                                    "userId", savedOwner.getUserId(),
                                    "email", savedOwner.getEmail(),
                                    "role", savedOwner.getRole().name(),
                                    "approved", savedOwner.isApproved()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }

    /**
     * OWNER 승인 (관리자만 호출 가능)
     */
    @PatchMapping("/approve/{ownerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> approveOwner(@PathVariable Long ownerId) {
        try {
            Users approvedOwner = ownerService.approveOwner(ownerId);

            return ResponseEntity.ok(
                    new AuthResponse(
                            200,
                            "OWNER 승인 완료",
                            java.util.Map.of(
                                    "userId", approvedOwner.getUserId(),
                                    "email", approvedOwner.getEmail(),
                                    "role", approvedOwner.getRole().name(),
                                    "approved", approvedOwner.isApproved()
                            )
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(400, e.getMessage(), null));
        }
    }
}
