package com.hwang.staste.controller;

import com.hwang.staste.config.auth.PrincipalDetails;
import com.hwang.staste.config.jwt.JwtTokenProvider;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
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
        String rawPassword = user.get("password");
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userRepository.save(User.builder()
                .username(user.get("username"))
                .password(encPassword)
                .email(user.get("email"))
                .build()).getId();
        return "회원가입완료";
    }

}
