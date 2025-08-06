package com.textrip.diarymarket.controller;

import com.textrip.diarymarket.dto.DiaryRequestDto;
import com.textrip.diarymarket.entity.Diary;
import com.textrip.diarymarket.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j // ✅ [개선 팁 3] 로그 출력을 위한 어노테이션
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryWriteController {

    private final DiaryService diaryService;



    @GetMapping("/list")
    public String list(Model model) {
        List<Diary> diaries = diaryService.getAllDiaries();
        model.addAttribute("diaries", diaries);
        return "diary/list"; // ⬅️ templates/diary/list.html
    }

    @GetMapping("/chuga")
    public String chugaForm(Model model) {
        model.addAttribute("diary", new Diary());
        return "diary/chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(@ModelAttribute DiaryRequestDto dto,
                            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        diaryService.saveDiary(dto, imageFile);
        log.info("새 일기 저장 완료: {}", dto.getTitle()); // ✅ [개선 팁 3]
        return "redirect:/diary/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Diary diary = diaryService.getDiaryById(id);
        model.addAttribute("diary", diary);
        return "diary/view";
    }

    @GetMapping("/sujung/{id}")
    public String sujung(@PathVariable("id") Long id, Model model) {
        Diary diary = diaryService.getDiaryById(id);
        model.addAttribute("diary", diary);
        return "diary/sujung";
    }

    @PostMapping("/sujungProc/{id}")
    public String sujungProc(@PathVariable("id") Long id,
                             @ModelAttribute DiaryRequestDto dto,
                             @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) { // ✅ [개선 팁 1]
        diaryService.updateDiary(id, dto, imageFile);
        log.info("일기 수정 완료: {}", id); // ✅ [개선 팁 3]
        return "redirect:/diary/view/" + id;
    }

    @GetMapping("/sakje/{id}")
    public String sakjeForm(@PathVariable Long id, Model model) {
        Diary diary = diaryService.getDiaryById(id);
        model.addAttribute("diary", diary);
        return "diary/sakje";
    }

    @PostMapping("/sakjeProc/{id}")
    public String sakjeProc(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        log.info("일기 삭제 완료: {}", id); // ✅ [개선 팁 3]
        return "redirect:/diary/list";
    }
}
