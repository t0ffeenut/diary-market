FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 소스 복사
COPY . .

# gradlew 실행 권한 부여
RUN chmod +x gradlew

# 빌드 실행
RUN ./gradlew clean build -x test

# 포트 개방 (Render 환경에서는 10000 사용)
EXPOSE 10000

# JAR 실행
ENTRYPOINT ["java", "-jar", "build/libs/diary-market-0.0.1-SNAPSHOT.jar"]
