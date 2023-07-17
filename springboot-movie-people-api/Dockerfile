#`FROM openjdk:11-jdk as builder
FROM maven:3.5.4-jdk-9 as build
WORKDIR /springboot-movie-people-api
ADD pom.xml .
ADD src src
RUN mvn clean
RUN mvn package -DskipTests
RUN echo "done!"

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /springboot-movie-people-api
RUN addgroup --system spring && adduser --ingroup spring spring
USER spring:spring
COPY --from=build /springboot-movie-people-api/target/springboot-movie-people-api-1.0.jar ./
ENTRYPOINT ["java","-jar","springboot-movie-people-api-1.0.jar"]
