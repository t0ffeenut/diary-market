package com.textrip.diarymarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiaryDto {


    private Long diaryId;
    private Long userId;
    private Long groupId;

    private String title;
    private String content;

    private String imagePath;
    private String audioPath;

    private String tags;

    private int likeCount;
    private int viewCount;
    private int shareCount;

    private boolean isPrivate;

    private LocalDateTime createdAt;  // 수정됨
}
