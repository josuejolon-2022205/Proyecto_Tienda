README - ProyectoTienda
# ProyectoTienda

Este proyecto es una aplicación web desarrollada con Spring Boot, enfocada en la gestión de una tienda. Permite manejar entidades como ventas, productos y usuarios, aplicando una arquitectura por capas y buenas prácticas de desarrollo backend.

## Tecnologías utilizadas

Java 21
Spring Boot
Spring Web
Spring Data JPA
Thymeleaf
MySQL

## Estructura del proyecto

### El proyecto está organizado en capas:

Controller → Manejo de rutas y vistas
Service → Lógica de negocio
Repository → Acceso a datos (JPA)
Entity → Modelos de la base de datos
Templates → Vistas con Thymeleaf
Static → Archivos CSS

## Funcionalidades principales

Gestión de ventas
Listado de registros
Creación y edición de datos
Validaciones básicas
Integración con base de datos MySQL
Uso de Thymeleaf para renderizar vistas

## Notas importantes

El proyecto sigue una arquitectura por capas para mantener el código organizado.
Se utilizan anotaciones como @Controller, @Service, @Repository y @Entity.
Thymeleaf se usa para conectar el backend con las vistas HTML.
Hibernate se encarga de la persistencia de datos.

## Cómo ejecutar el proyecto

Clonar el repositorio
Configurar la base de datos en application.properties
Ejecutar el proyecto desde el IDE o con:
mvn spring-boot:run
Abrir en el navegador:
http://localhost:8080