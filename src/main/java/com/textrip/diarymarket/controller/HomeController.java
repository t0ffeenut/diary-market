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

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            Map<String, Object> properties = (Map<String, Object>) principal.getAttribute("properties");
            String nickname = properties != null ? (String) properties.get("nickname") : "사용자";
            model.addAttribute("nickname", nickname);
        } else {
            model.addAttribute("nickname", "비로그인 사용자");
        }
        return "home";
    }


    // 🔽 여기에 이 메서드를 **추가**하면 됨
    private String processHome(OAuth2User oauth2User, Model model) {
        if (oauth2User != null) {
            Map<String, Object> properties = (Map<String, Object>) oauth2User.getAttribute("properties");
            String nickname = properties != null ? (String) properties.get("nickname") : "사용자";
            model.addAttribute("nickname", nickname);
        } else {
            model.addAttribute("nickname", "비로그인 사용자");
        }

        return "home"; // → templates/home.html
    }



}
