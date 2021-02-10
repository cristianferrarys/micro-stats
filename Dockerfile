FROM openjdk:8
VOLUMEN /tmp
ADD ./target/micro-stats-0.0.1-SNAPSHOT.jar micro-stats.jar
ENTRYPOINT ["java","-jar","/micro-stats.jar"]