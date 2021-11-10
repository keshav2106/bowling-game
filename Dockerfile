FROM java:8-jdk-alpine
COPY ./target/bowling-game-0.0.1.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "bowling-game-0.0.1.jar"]