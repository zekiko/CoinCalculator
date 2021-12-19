FROM openjdk:8-jdk-alpine
VOLUME /coincalculator
ARG JAR_FILE=./target/coin-calculator-1.0.0.jar
ADD ${JAR_FILE} coin-calculator-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/coin-calculator-1.0.0.jar"]