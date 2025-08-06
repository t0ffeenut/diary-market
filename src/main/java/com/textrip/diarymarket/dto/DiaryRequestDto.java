package com.textrip.diarymarket.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
    public class DiaryRequestDto {
        private Long id;
        private String title;
        private String content;
        // getter/setter
    }


