FROM openjdk:8
EXPOSE 8686
ADD target/webclientincidents.jar webclientincidents.jar
ENTRYPOINT ["java","-jar","/webclientincidents.jar"]