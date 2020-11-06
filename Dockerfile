FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./target/mq-demo-0.0.1-SNAPSHOT.jar mqdemo.jar
ENTRYPOINT ["java","-Xms128m   -Xmx128m  -Xmn256m   -Xss256k", "-jar", "/mqdemo.jar", "&"]