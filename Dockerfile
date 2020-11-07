FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./mq-demo-0.0.1-SNAPSHOT.jar mqdemo.jar
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx128m", "/mqdemo.jar", "&"]
ENV TIME_ZONE=Asia/Shanghai 
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone