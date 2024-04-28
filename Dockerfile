FROM openjdk:17

WORKDIR /app

COPY ./target/shopping-cart-api-0.0.1-SNAPSHOT.jar /app

COPY ./.env /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "shopping-cart-api-0.0.1-SNAPSHOT.jar"]