package com.hwang.staste.controller;

import com.hwang.staste.config.jwt.JwtTokenProvider;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.model.entity.UserAbility;
import com.hwang.staste.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SignController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signIn")
    public String signIn(@RequestBody Map<String, String> signInForm) { //의존성 주입
        User user = userRepository.findByUsername(signInForm.get("username"));
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않은 아이디입니다.");
        }
        if (!bCryptPasswordEncoder.matches(signInForm.get("password"), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername());
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody Map<String, String> user) {

        if(userRepository.findByUsername(user.get("username")) != null){
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
        String rawPassword = user.get("password");
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        Double score = Double.valueOf(5);
        UserAbility userAbility = UserAbility.builder()
                .maraLevel(score)
                .hackLevel(score)
                .tokLevel(score)
                .sweetLevel(score)
                .build();

        userRepository.save(User.builder()
                .username(user.get("username"))
                .password(encPassword)
                .email(user.get("email"))
                .userAbility(userAbility)
                .build()).getId();
        return "회원가입완료";
    }

}
