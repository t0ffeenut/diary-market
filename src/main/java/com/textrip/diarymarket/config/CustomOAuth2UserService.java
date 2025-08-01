package com.textrip.diarymarket.config;

import com.textrip.diarymarket.entity.User;
import com.textrip.diarymarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            OAuth2User oauth2User = super.loadUser(userRequest);
            Map<String, Object> attributes = oauth2User.getAttributes();

            System.out.println("📦 카카오 attributes 전체 데이터: " + attributes); // ✅ 추가한 부분

            // ✅ ID null 체크
            if (!attributes.containsKey("id") || attributes.get("id") == null) {
                System.out.println("❗ [ERROR] 카카오 ID가 누락됨");
                throw new OAuth2AuthenticationException("카카오 ID가 없습니다.");
            }

            // ✅ 카카오 고유 ID
            Long kakaoId = ((Number) attributes.get("id")).longValue();
            System.out.println("✅ 추출된 kakaoId: " + kakaoId);

            // ✅ 계정 정보 추출
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            String email = (String) kakaoAccount.get("email"); // null 허용

            String profileImage = (String) profile.get("profile_image_url");



            // 이메일 앞부분 꺼내기: icandoit718@naver.com → icandoit718
            String emailId = (email != null && email.contains("@"))
                    ? email.split("@")[0]
                    : "user" + kakaoId;  // email 없으면 kakaoId로 대체

            // attributes에 따로 저장해줌 (nickname처럼 꺼낼 수 있도록)
            Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
            modifiedAttributes.put("emailId", emailId);


            // ✅ 이메일 없이도 저장 가능
            User user = userRepository.findByKakaoId(kakaoId).orElse(null);
            System.out.println("🔍 DB에서 찾은 사용자: " + user);

            if (user == null) {
                user = new User();
                user.setKakaoId(kakaoId);
                user.setEmail(email); // null 허용
                user.setProfileImage(profileImage);
                user.setJoinDate(LocalDateTime.now());

                userRepository.save(user); // 꼭 저장해야 반영됨!
            }

            return new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    modifiedAttributes,
                    "id"
            );



        } catch (Exception e) {
            System.out.println("❗ 전체 OAuth 처리 중 예외 발생");
            e.printStackTrace();
            throw new OAuth2AuthenticationException("OAuth 처리 실패: " + e.getMessage());
        }
    }
}
