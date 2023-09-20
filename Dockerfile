FROM maven:3.8.6-openjdk-8-slim

ADD . /usr/local/src

WORKDIR /usr/local/src

RUN mvn clean package

ENTRYPOINT java -jar target/aurora-demo.jar