package com.textrip.diarymarket.service;

import com.textrip.diarymarket.dto.DiaryRequestDto;
import com.textrip.diarymarket.entity.Diary;
import com.textrip.diarymarket.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    public Diary getDiaryById(Long id) {
        return diaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Diary not found"));
    }

    public void saveDiary(DiaryRequestDto dto, MultipartFile imageFile) {
        Diary diary = new Diary();
        diary.setTitle(dto.getTitle());
        diary.setContent(dto.getContent());
        diary.setCreatedAt(LocalDateTime.now());

        // ✅ 이미지 저장 처리
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String uploadDir = "C:/uploads/diary/"; // 원하는 경로로 설정
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs(); // 폴더 없으면 생성
                }

                String fileName = UUID.randomUUID() + "-" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                // 📌 DB에는 경로만 저장
                diary.setImagePath("/uploads/diary/" + fileName);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("이미지 저장 실패");
            }
        }

        diaryRepository.save(diary);
    }

    public void updateDiary(Long id, DiaryRequestDto dto, MultipartFile imageFile) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new RuntimeException("Diary not found"));

        diary.setTitle(dto.getTitle());
        diary.setContent(dto.getContent());
        diary.setUpdatedAt(LocalDateTime.now());

        // TODO: 이미지 파일이 있으면 저장하고 diary.setImagePath(...) 적용

        diaryRepository.save(diary);
    }

    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

}
