Requisitos
############
Java 21 con Spring Boot
Docker Desktop -  Contenedor MySQL

API para gestionar los cocineros de un restaurante
Endpoints:
    http://localhost:8090/api/cocineros/5
    http://localhost:8090/api/cocineros/
    
30.- Como usar el patrón DAO con JDBC

31.- Spring validation, como hacer validaciones en nuestros dtos

-   Añadimos starter de validación al pom.xml
-	Modificamos el dto poniendo las anotaciones de validation – max min, mail, pattern , date…
-	En el controller añadimos la vaidacion de requestDTO
-	Creamos un paquete utils para personalizar los mensajes
-	En el dto añadimos los mensajes y hacemos en el controller la referencia a la utilidad

