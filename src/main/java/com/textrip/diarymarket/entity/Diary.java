package com.textrip.diarymarket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "diary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;  // ✅ 일기 고유 ID

    private Long userId;  // ✅ 작성자 ID

    private Long groupId;  // ✅ 그룹 다이어리 ID

    private String title;  // ✅ 제목

    @Column(columnDefinition = "TEXT")
    private String content;  // ✅ 본문

    private String imagePath;  // ✅ 이미지 경로

    private String audioPath;  // ✅ 음성 경로

    private String tags;  // ✅ 해시태그

    private int likeCount;  // ✅ 좋아요 수

    private int viewCount;  // ✅ 조회수

    private int shareCount;  // ✅ 공유 수

    private boolean isPrivate;  // ✅ 비공개 여부

    @CreationTimestamp
    private LocalDateTime createdAt;  // ✅ 작성일시

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // ✅ 수정일시
}
