package choplan.db.application.properties.choplan.service;

import choplan.db.application.properties.choplan.dto.SignupRequestOwner;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.entity.UserRole;
import choplan.db.application.properties.choplan.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;

    /**
     * OWNER 회원가입
     */
    public Users registerOwner(SignupRequestOwner request, MultipartFile businessDoc) {
        // 이메일 중복 검사
        Optional<Users> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 사업자등록증 업로드 (S3)
        String businessDocUrl = null;
        if (businessDoc != null && !businessDoc.isEmpty()) {
            businessDocUrl = s3Service.uploadFile(businessDoc);
        } else {
            throw new IllegalArgumentException("사업자등록증은 필수 업로드 항목입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Users 엔티티 생성
        Users owner = Users.builder()
                .email(request.getEmail())
                .passwordHash(encodedPassword)
                .realName(request.getRealName())
                .phone(request.getPhone())
                .storeName(request.getStoreName())
                .storePhone(request.getStorePhone())
                .storeAddress(request.getStoreAddress())
                .businessRegistrationDoc(businessDocUrl)
                .role(UserRole.OWNER)
                .approved(false) // 관리자 승인 전까지는 false
                .build();

        // DB 저장
        return userRepository.save(owner);
    }

    /**
     * 관리자 승인 메소드
     */
    public Users approveOwner(Long ownerId) {
        Users owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (owner.getRole() != UserRole.OWNER) {
            throw new IllegalArgumentException("해당 사용자는 OWNER 권한이 아닙니다.");
        }

        owner.setApproved(true);
        return userRepository.save(owner);
    }
}
