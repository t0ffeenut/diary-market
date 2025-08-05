# 1. Java 17 slim 이미지 기반
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 전체 프로젝트 복사
COPY . .

# ✅ 이 줄 추가: 이전 캐시 무시용 (빌드 안정화용)
RUN rm -rf ~/.gradle

# 4. Gradle 실행 권한 부여 + 빌드
RUN chmod +x gradlew
RUN ./gradlew build -x test --no-daemon --stacktrace --refresh-dependencies


# 5. 포트 설정
EXPOSE 10000

# 6. JAR 실행
ENTRYPOINT ["java", "-jar", "build/libs/diary-market-0.0.1-SNAPSHOT.jar"]
