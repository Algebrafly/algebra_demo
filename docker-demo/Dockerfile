FROM openjdk:8-jdk-alpine
ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar
ENV TZ Asia/Shanghai
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]


FROM openjdk:8-jdk-alpine
ADD ./docker/app.jar app.jar
ENV TZ Asia/Shanghai
EXPOSE 8081
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar -Dspring.config.location=/config/application.yml app.jar
