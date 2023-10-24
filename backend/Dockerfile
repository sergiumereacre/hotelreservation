FROM maven:3.9.5-eclipse-temurin-17

WORKDIR /backend
COPY . .
RUN mvn clean install

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]