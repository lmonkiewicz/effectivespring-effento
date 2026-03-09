FROM registry.access.redhat.com/ubi9/openjdk-25

COPY target/*.jar /app/app.jar
COPY ./docker-application.yml /app/config/application.yml

WORKDIR /app

EXPOSE 8080 5005
ENTRYPOINT java -jar -server -XshowSettings:vm -XX:MaxRAMPercentage=80.0  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 app.jar
