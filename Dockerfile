FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "build/libs/diary-market-0.0.1-SNAPSHOT.jar"]
