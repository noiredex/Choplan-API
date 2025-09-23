package choplan.db.application.properties.choplan.service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import choplan.db.application.properties.choplan.dto.AuthResponse;
import choplan.db.application.properties.choplan.dto.LoginRequest;
import choplan.db.application.properties.choplan.dto.SignupRequest;
import choplan.db.application.properties.choplan.entity.UserRole;
import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.repository.UserRepository;
import choplan.db.application.properties.choplan.security.JwtTokenProvider;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원가입
    public AuthResponse signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthResponse(400, "이미 사용 중인 이메일입니다.", null);
        }

        Users user = Users.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .realName(request.getRealName())
                .phone(request.getPhone())
                .nickname(request.getNickname())
                .role(UserRole.valueOf(request.getRole()))
                .build();

        Users savedUser = userRepository.save(user);

        return new AuthResponse(200, "회원가입 성공",
                Map.of(
                        "userId", savedUser.getUserId(),
                        "email", savedUser.getEmail(),
                        "role", savedUser.getRole().name()
                )
        );
    }

    // 로그인
    public AuthResponse login(LoginRequest request) {
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return new AuthResponse(400, "비밀번호가 올바르지 않습니다.", null);
        }

        // JWT 발급
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(200, "로그인 성공",
                Map.of(
                        "token", token,
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                )
        );
    }
}
