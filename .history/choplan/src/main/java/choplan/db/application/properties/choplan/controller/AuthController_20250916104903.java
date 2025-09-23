package choplan.db.application.properties.choplan.controller;

import java.util.Map;

import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.repository.UserRepository;
import choplan.db.application.properties.choplan.security.JwtTokenProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserRepository usersRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "status", 400,
                            "massage", "이미 사용중인 이메일입니다.",
                            "data", null)
            );
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        Users savedUser = userRepository.save(user);

        return ResponseEntity.ok(
                Map.of(
                        "status", 200,
                        "massage", "회원가입 성공",
                        "data", Map.of(
                                "userId", savedUser.getUserId(),
                                "email", savedUser.getEmail(),
                                "role", savedUser.getRole().name()
                        )
                )
        );
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자 입니다."));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", 400,
                    "massge", "비밀번호가 올바르지 않습니다.",
                    "data", null
            )
            );
        }

        // JWT 발급
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "로그인 성공",
                "data", Map.of(
                        "token", token,
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                )
        )
        );
    }
}
