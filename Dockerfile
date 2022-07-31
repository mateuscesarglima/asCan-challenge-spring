ARG BUILD_IMAGE=maven:3.8.6-jdk-11-slim
ARG RUNTIME_IMAGE=openjdk:11-jre-slim
#############################################################################################
###                Stage where Docker is pulling all maven dependencies                   ###
#############################################################################################
FROM ${BUILD_IMAGE} AS dependencies
# Copia tudo que está dentro de souce para o diretorio /home/app/src
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml install -DskipTests
#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM ${RUNTIME_IMAGE}
COPY --from=dependencies /home/app/target/*.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]