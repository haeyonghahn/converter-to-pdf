FROM eclipse-temurin:17-jdk

EXPOSE 8080

COPY ./build/libs/*.jar converter-to-pdf.jar

ENTRYPOINT ["java", "-jar","/converter-to-pdf"]
