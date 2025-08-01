package com.textrip.diarymarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    @GetMapping("/newspaper")
    public String newspaperPage() {
        return "diary/newspaper";
    }

    @GetMapping("/calendar")
    public String calendarPage() {
        return "diary/calendar";
    }

    @GetMapping("/lighting")
    public String lightingPage() {
        return "diary/lighting";
    }

    @GetMapping("/camera")
    public String cameraPage() {
        return "diary/camera";
    }

    @GetMapping("/book")
    public String bookPage() {
        return "diary/book";
    }

    @GetMapping("/recorder")
    public String recorderPage() {
        return "diary/recorder";
    }

    @GetMapping("/wallet")
    public String walletPage() {
        return "diary/wallet";
    }

    @GetMapping("/magnifier")
    public String magnifierPage() {
        return "diary/magnifier";
    }

    @GetMapping("/coffee")
    public String coffeePage() {
        return "diary/coffee";
    }

    @GetMapping("/donation")
    public String donationPage() {
        return "diary/donation";
    }

    @GetMapping("/flower")
    public String flowerPage() {
        return "diary/flower";
    }

    @GetMapping("/crayon")
    public String crayonPage() {
        return "diary/crayon";
    }

    @GetMapping("/paper")
    public String paperPage() {
        return "diary/paper";
    }
}
