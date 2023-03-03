package com.hwang.staste.config.oauth;

import com.hwang.staste.config.auth.PrincipalDetails;
import com.hwang.staste.model.entity.Article;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public PrincipalOauth2UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    //    구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: "+userRequest.getClientRegistration());
        System.out.println("getAccessToken:"+userRequest.getAccessToken().getTokenValue());

        // userRequest 정보 -> loadUser 함수호출 -> 구글로부터 회원프로필을 받아준다.


        OAuth2User oAuth2User = super.loadUser(userRequest);
        // userRequest 정보
        // 구글 로그인 버튼 클릭 -> 구글로그인 창 -> 로그인 완료 -> code를 리턴(OAuth-client 라이브러리) -> AccessToken요청
        System.out.println("getAttributes:"+oAuth2User.getAttributes());

        // OAuth로 회원가입을 강제로 진행
        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String username = provider+"_"+providerId; //google_12345678910
        String password = bCryptPasswordEncoder.encode("겟인데어");

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        } else {
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
        }

        return super.loadUser(userRequest);
    }
}
