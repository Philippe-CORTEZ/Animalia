# Dockerfile pour la partie serveur
# Philippe CORTEZ


# Partie compilation du projet avec maven
FROM maven:3.8.4-eclipse-temurin-17 AS builder
COPY pom.xml App/pom.xml
COPY src App/src/
WORKDIR App
RUN --mount=type=cache,target=/root/.m2/repository,rw mvn clean package


# Image finale, copie le jar et execute avec un JRE 17
FROM eclipse-temurin:17.0.2_8-jre-alpine
COPY --from=builder App/target/Animalia-1.0-SNAPSHOT-withdependencies.jar App/app.jar
CMD java -cp App/app.jar fr.animalia.serveurs.DemarrerGrizzly

