FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/src
COPY application/target/catalog-application.jar app.jar
ENTRYPOINT java $JAVA_OPTS -jar app.jar