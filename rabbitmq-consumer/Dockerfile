FROM  java:8

COPY  *.jar /rabbit-consumer.jar

CMD   ["--server.port=8080"]

EXPOSE  8080

ENTRYPOINT  ["java","-jar","/rabbit-consumer.jar"]
