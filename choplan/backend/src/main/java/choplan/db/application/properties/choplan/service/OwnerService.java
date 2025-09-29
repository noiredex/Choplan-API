package choplan.db.application.properties.choplan.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequestOwner;
import choplan.db.application.properties.choplan.entity.OwnerStatus;
import choplan.db.application.properties.choplan.entity.StoreAddress;
import choplan.db.application.properties.choplan.entity.UserRole;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.repository.UserRepository;
import choplan.db.application.properties.choplan.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // OWNER 회원가입
    public Users registerOwner(SignupRequestOwner request, String businessDocUrl) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        StoreAddress storeAddress = new StoreAddress();
        storeAddress.setRoadAddress(request.getRoadAddress());

        Users user = Users.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .realName(request.getRealName())
                .phone(request.getPhone())
                .storeName(request.getStoreName())
                .storePhone(request.getStorePhone())
                .storeAddress(storeAddress)
                .businessRegistrationDoc(businessDocUrl)
                .role(UserRole.OWNER)
                .ownerStatus(OwnerStatus.PENDING)
                .build();

        return userRepository.save(user);
    }

    // OWNER 로그인
    public AuthResponse login(LoginRequest request) {
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        if (user.getOwnerStatus() != OwnerStatus.APPROVED) {
            throw new IllegalArgumentException("관리자 승인 후 로그인 가능합니다.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(
                200,
                "로그인 성공",
                java.util.Map.of(
                        "token", token,
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                )
        );
    }
}
