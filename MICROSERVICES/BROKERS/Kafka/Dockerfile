FROM ubuntu

WORKDIR /

RUN apt update

RUN apt install -y wget tar vim openjdk-17-jre
RUN wget https://dlcdn.apache.org/kafka/3.5.0/kafka_2.13-3.5.0.tgz
RUN tar -xzf kafka_2.13-3.5.0.tgz
RUN mv /kafka_2.13-3.5.0 /kafka

WORKDIR /kafka

COPY ./server.properties /kafka/config/server.properties

EXPOSE 9092

ENTRYPOINT [ "/kafka/bin/kafka-server-start.sh", "config/server.properties" ]