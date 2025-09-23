package choplan.db.application.properties.choplan.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.repository.UserRepository;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String singup(@RequestBody Users user) {

user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
userRepository.save(user);
return "회원가입 성공!";
    }
    
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Users loginRequest) {
    Users user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일"));

    if (!passwordEncoder.matches(loginRequest.getPasswordHash(), user.getPasswordHash())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 불일치");
    }

    String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole().name());
    return ResponseEntity.ok(Map.of("token", token));
}
