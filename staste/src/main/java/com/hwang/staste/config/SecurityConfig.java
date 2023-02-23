package com.hwang.staste.config;

import com.hwang.staste.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화 , preAuthorize 어노테이션 활성화
public class SecurityConfig {

//    @Autowired
//    private PrincipalOauth2UserService principalOauth2UserService;

    // Bean을 쓰면 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줍니다.
                // 내가 controller에 /login가 필요없음.
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint();
//                .userService(principalOauth2UserService); // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드x (엑세스토큰 + 사용자프로필정보 O)

        // 1. 코드받기(인증), 2. 엑세스토큰(권한), 3.사용자프로필 정보를 가져옴
        // 4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함 (이메일, 전화번호, 이름, 아이디) -> 구글이 주는 정보로만 회원가입 가능
        // 4-2. 추가적인 정보가 필요하기도 함 ->  쇼핑몰 -> (집주소), 쇼핑몰 -> (vip등급, 일반등급)
        return http.build();

    }
}
