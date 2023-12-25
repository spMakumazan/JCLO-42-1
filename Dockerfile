FROM adoptopenjdk/openjdk11
EXPOSE 8081
ADD target/JCLO-42-1-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]