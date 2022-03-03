FROM arm32v7/openjdk

ARG APP_NAME="department"
ARG APP_VERSION="0.0.1-SNAPSHOT"
ARG JAR_FILE="/target/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} app.jar
EXPOSE 1003

ENTRYPOINT ["java","-jar", "app.jar"]