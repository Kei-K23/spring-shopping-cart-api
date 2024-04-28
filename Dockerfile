FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean install

CMD [ "./mvnw", "spring-boot:run" ]
