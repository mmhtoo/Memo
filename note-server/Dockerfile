FROM maven:3.8.5-eclipse-temurin-17
WORKDIR /memo/app
COPY pom.xml .

COPY src ./src
RUN mvn install
CMD ["java","spring-boot:run"]
