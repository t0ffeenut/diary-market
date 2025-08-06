package com.textrip.diarymarket.repository;

import com.textrip.diarymarket.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    // 기본 CRUD 제공됨: save(), findAll(), findById(), deleteById() 등
}
