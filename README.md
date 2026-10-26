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

32.- Uso de dtos y mapeers con mapstrut
-   
-	Añadimos dependencias de mapstrut core y procesor(también en runtime)
-	Agrupamos en model dto, mapper y entidades junto con sus cclases
-	Creamos dos interfaces mapeadoras en dto
-	Quitamos en CocinerosResponse y Request los constructores ya que ahora el mapeo se hace en los mapers
-	Ajustamos los daos y el service ya que ahora usarán los mapeadores

- 