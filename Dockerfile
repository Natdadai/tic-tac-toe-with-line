FROM maven:3.8.8-eclipse-temurin-17

# Install FFmpeg
RUN apt-get update && \
    apt-get install -y ffmpeg

WORKDIR /app/line-webhook

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "sleep 8 && mvn spring-boot:run"]