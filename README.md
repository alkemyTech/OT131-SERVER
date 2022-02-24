# Proyecto ONG Team 131 - Java 👨‍💻👩‍💻

Desarrollo del back-end de una aplicación REST API utilizando Java 11 como lenguaje de programación principal, Spring Boot como framework, MySQL como base de datos, JPA con Hibernate para la conexión, modelado, manipulación y consultas a la base de datos y dependencias Maven para la implementación de soluciones eficientes aplicando buenas prácticas de desarrollo.

## Patrón de diseño 📐

Se ha implementado un patrón de diseño DTO para la creación de objetos planos (POJO) en respuesta a las peticiones HTTP, utilizando una arquitectura organizada por capas conectando Controller > Service > Service Implementation > Repository > Database.

También se hizo uso de Constantes para evitar el uso de Strings directos en los métodos y servicios.

Las excepciones se manejan a través de un Exception Hanlder Controller para la configuración unificada y global del manejo de excepciones.

Se implementó una Clase Data Seed utilizando la interfaz CommandLineRunner para la propagación de datos iniciales utilizando un script externo escrito en SQL nativo.

Además, varias entidades cuentan con endpoints con paginación de los resultados para optimización de las consultas a la base de datos.

## Dependencias Maven usadas 📦

### Lombok

Uso de anotaciones Lombok como @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor, entre otros.

###  ModelMapper

Para la conversión de Entidades Java en Objetos DTO y vice versa.

### JWT (JSON Web Token)

Para el registro, login y autenticación de usuarios basado en roles.

### Spring Security

Para la configuración global y unificada de la seguridad de la aplicación utilizando la Clase Web Security Config.

### SendGrid

Para el envío de emails a los usuarios cuando determinados eventos suceden (ej.: un usuario nuevo registrandose y recibiendo un mail de bienvenida).

### Amazon AWS SDK S3

Para la subida y almacenamiento de imagenes utilizando los Servicios Web de Amazon.

### Open API & Swagger UI

Para la generación de documentación detallada y automática de cada endpoint, con su ejemplo y sus posibles códigos y mensajes de respuesta

### JUnit 5 & Mockito

Para la implementación de pruebas unitarias a cada endpoint desarrollado.


## SCRUM y Metodologias ágiles 🙋‍♀️🙋‍♂️

Para la coordinación del desarrollo en equipo de la aplicación, se utilizaron fundamentos de SCRUM y Metodologias ágiles tales como uso de la plataforma JIRA, delegación de incidencias, story points, epics, dailys para actualización del estado del desarrollo y retro dailys para obtención de feedback y retrospección como equipo.

Como canal principal de comunicación del equipo se utilizó la plataforma Slack, complementandolo con videollamadas via Zoom y Google Meets.

## Reglas de permisos según rol 🚦

Para la realización de este proyecto, se desarrollaron 2 roles principales: ADMIN y USER.

Un usuario con el rol de ADMIN puede ejecutar todos los endpoints con peticiones GET, POST, PUT y DELETE para la manipulación de la base de datos.

Por otro lado, el usuario con rol USER solo podrá acceder a los endpoints con peticiones GET, pudieron realizar consultas pero no modificaciones a la base de datos.

Finalmente, un usuario sin ningún rol solo podrá acceder a los endpoints correspondientes al registro y login de usuarios para obtener su token generado con JWT.

## Registro de usuarios según DataSeed 📋

| User | Password | Rol |
| ------------- | ------------- | ------------- |
| carog@ong.com | 12345678 | Admin |
| alexisd@ong.com | 12345678 | Admin |
| pepito@ong.com | 12345678 | Admin |
| fedel@ong.com | 12345678 | Admin |
| matic@ong.com | 12345678 | Admin |
| matils@ong.com | 12345678 | Admin |
| rodri@ong.com | 12345678 | Admin |
| walterr@ong.com | 12345678 | Admin |
| marcef@ong.com | 12345678 | Admin |
| otroadmin@ong.com | 12345678 | Admin |
| mabemoujeiyeu-1534@gmail.com | 12345678 | User |
| quoyeurittefro@gmail.com | 12345678 | User |
| yazonnegrofa@gmail.com | 12345678 | User |
| keifoittillime@gmail.com | 12345678 | User |
| brautusseiceza@gmail.com | 12345678 | User |
| quazousepemei@gmail.com | 12345678 | User |
| quouruddafrette@gmail.com | 12345678 | User |
| hoivehasseke@gmail.com | 12345678 | User |
| darigonagi@gmail.com | 12345678 | User |
| jutreulaxumme@gmail.com | 12345678 | User |
