package com.textrip.diarymarket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.textrip.diarymarket.entity") // 여기가 중요
public class DiaryMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiaryMarketApplication.class, args);
	}
}

