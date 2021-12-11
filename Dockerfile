FROM openjdk:11-buster
WORKDIR /app
COPY app/build/libs/*.jar /app/app.jar
CMD java -jar app.jar