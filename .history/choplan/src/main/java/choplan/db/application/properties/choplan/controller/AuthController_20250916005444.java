package choplan.db.application.properties.choplan.controller;

import choplan.db.application.properties.choplan.entity.Users;
import choplan.db.application.properties.choplan.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/singup")
    public String singup(@RequestBody Users user) {

user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
userRepository.save(user);
return "회원가입 성공!";
    }
    
}
