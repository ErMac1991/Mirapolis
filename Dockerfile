FROM eclipse-temurin
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring
ARG JAR_FILE=target/MirapolisJava-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]