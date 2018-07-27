 ## Example for a jOOQ error on INSERT of an UDT array
 
 To create the db instance, run `docker run --name postgres-udt-array -e POSTGRES_PASSWORD=test -e POSTGRES_USER=test -d postgres`
 Then run the generation with `mvn clean flyway:clean flyway:migrate jooq-codegen:generate`
 
 You can run the project with `mvn spring-boot:run`