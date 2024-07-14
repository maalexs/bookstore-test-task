FROM openjdk:17-oracle

VOLUME /tmp
ADD Bookstore.jar /opt/Bookstore/
EXPOSE 8080
WORKDIR /opt/Bookstore/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "Bookstore.jar"]
