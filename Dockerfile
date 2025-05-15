FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 10000

CMD ["java", "-Dserver.port=10000", "-jar", "target/ServicioRest-0.0.1-SNAPSHOT.jar"]
