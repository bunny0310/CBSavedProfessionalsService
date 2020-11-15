FROM openjdk

EXPOSE 8080
EXPOSE 8081

COPY SavedProfessionalsService.jar .
COPY config_prod.yml .
CMD ["java","-jar","SavedProfessionalsService.jar","server","config_prod.yml"]


