package com.textrip.diarymarket.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    // 루트 경로 "/" 요청 처리
    @GetMapping("/")
    public String root(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        return processHome(oauth2User, model);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login/login"; // ⬅️ templates/login/login.html 로 연결됨
    }

    // "/home" 요청 처리
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        return processHome(oauth2User, model);
    }

    // 공통 처리 메서드
    private String processHome(OAuth2User oauth2User, Model model) {
        String emailId = "게스트";

        if (oauth2User != null) {
            Object emailIdAttr = oauth2User.getAttributes().get("emailId");
            if (emailIdAttr != null) {
                emailId = emailIdAttr.toString();
            }
        }
        model.addAttribute("userName", emailId);
        return "home/home"; // templates/home.html 렌더링
    }

}
