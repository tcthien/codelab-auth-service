FROM java:8-jre
MAINTAINER Thien Tran <thientran1986@gmail.com>


ADD ./target/codelab-auth-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/codelab-auth-service.jar"]

EXPOSE 20084
