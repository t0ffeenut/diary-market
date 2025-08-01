# 1단계: 빌드
FROM gradle:7.6-jdk17 AS build
COPY . /home/gradle/project
WORKDIR /home/gradle/project
RUN ./gradlew clean build -x test

# 2단계: 실행
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","app.jar"]
