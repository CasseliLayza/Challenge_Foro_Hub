![logo](src/main/resources/asset/img/logo2.webp)

# Challenge Foro Hub

Este proyecto es una aplicación backend desarrollada con Spring Boot, diseñada para el manejo de foros en línea. La estructura del proyecto está configurada con Maven como gestor de dependencias y sigue el esquema de una aplicación moderna y escalable.

## Configuración del Proyecto

### Información General

- **Group ID:** com.backend.challengeforohub
- **Artifact ID:** Challenge_Foro_Hub
- **Version:** 0.0.1-SNAPSHOT
- **Java Version:** 17

### Dependencias Principales

El archivo `pom.xml` incluye las siguientes dependencias clave:

1. **Spring Boot Starter Data JPA:** Manejo de persistencia de datos.
2. **Spring Boot Starter Web:** Creación de controladores REST.
3. **Spring Boot DevTools:** Herramientas para acelerar el desarrollo.
4. **MySQL Connector:** Conexión con base de datos MySQL.
5. **Lombok:** Simplificación de la escritura de clases Java.
6. **Spring Boot Starter Test:** Soporte para pruebas unitarias.
7. **Spring Boot Starter Logging y Logback Classic:** Manejo de logs.
8. **Spring Boot Starter Validation:** Validación de datos de entrada.
9. **Jackson Databind:** Serialización y deserialización JSON.
10. **ModelMapper:** Mapeo de objetos DTO a entidades y viceversa.
11. **Gson:** Manejo alternativo de JSON.
12. **Spring Boot Starter Security:** Manejo de seguridad.
13. **JSON Web Token (JWT):** Autenticación y generación de tokens.

### Plugins Configurados

- **Spring Boot Maven Plugin:** Compilación y empaquetado del proyecto.
- **Maven Surefire Plugin:** Ejecución de pruebas unitarias.

## Cómo Iniciar el Proyecto

### Requisitos Previos

- JDK 17 o superior.
- Maven instalado.
- MySQL configurado con una base de datos disponible.

### Pasos para la Ejecución

1. Clona este repositorio:

   ```bash
   git clone https://github.com/CasseliLayza/Challenge_Foro_Hub
   ```

2. Navega al directorio del proyecto:

   ```bash
   cd Challenge_Foro_Hub
   ```

3. Configura el archivo `application.properties` o `application.yml` con los datos de conexión a tu base de datos MySQL:

   ```properties
   spring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USER}
   spring.datasource.password=${DB_PASS}
   spring.datasource.driver-class-name=${DB_DRIVER}
   spring.jpa.properties.hibernate.dialect=${DB_PLATFORM}
   ```

4. Compila y ejecuta el proyecto con Maven:

   ```bash
   mvn spring-boot:run
   ```

5. Accede a la aplicación en:

   ```
   http://localhost:8080
   ```

## Características

- **Persistencia de Datos:** Manejo de datos mediante JPA y MySQL.
- **Validación de Entradas:** Asegura que los datos ingresados cumplan con las restricciones definidas.
- **Autenticación JWT:** Seguridad mediante tokens JSON Web Tokens.
- **Mapeo DTO-Entidades:** Simplifica la transferencia de datos.

## Rutas de la API por Roles

La seguridad de la API está basada en Spring Security y utiliza JWT para la autenticación y autorización. Las rutas están protegidas según los roles asignados a los usuarios:

### Endpoints Públicos

| Endpoint                | Descripción                  | Roles Permitidos |
| ----------------------- | ---------------------------- | ---------------- |
| `GET /users/**`         | Acceso público.              | Todos            |
| `POST /users/register`  | Registro de nuevos usuarios. | Todos            |
| `POST /users/reesetids` | Reinicio de identificadores. | Todos            |
| `GET /roles/**`         | Acceso público.              | Todos            |
| `GET /topics/**`        | Acceso público.              | Todos            |

### Endpoints Protegidos por Roles

#### Usuarios

| Endpoint             | Descripción             | Roles Permitidos |
| -------------------- | ----------------------- | ---------------- |
| `POST /users/create` | Crear un nuevo usuario. | USER             |
| `DELETE /users/**`   | Eliminar un usuario.    | ADMIN            |

#### Temas

| Endpoint                     | Descripción                        | Roles Permitidos |
| ---------------------------- | ---------------------------------- | ---------------- |
| `POST /topics/register`      | Registrar un nuevo tema.           | USER             |
| `PUT /topics/update/{id}`    | Actualizar información de un tema. | ADMIN            |
| `DELETE /topics/delete/{id}` | Eliminar un tema.                  | ADMIN            |

## Estructura del Proyecto

```javaScript
src
└── main
    ├── java
    │   └── com
    │       └── backend
    │           └── challengeforohub
    │               ├── controller
    │               │   ├── RolController.java
    │               │   ├── TopicoController.java
    │               │   └── UsuarioController.java
    │               ├── dto
    │               │   ├── input
    │               │   │   ├── TopicoDtoInput.java
    │               │   │   └── UsuarioDtoInput.java
    │               │   └── output
    │               │       ├── TopicoDtoOut.java
    │               │       └── UsuarioDtoOut.java
    │               ├── entity
    │               │   ├── Rol.java
    │               │   ├── Topico.java
    │               │   └── Usuario.java
    │               ├── exception
    │               │   ├── DuplicateDniException.java
    │               │   ├── DuplicateRolException.java
    │               │   ├── GlobalExceptionHandler.java
    │               │   └── ResourceNotFoundException.java
    │               ├── repository
    │               │   ├── RolRepository.java
    │               │   ├── TopicoRepositoriy.java
    │               │   └── UsuarioRepositoy.java
    │               ├── security
    │               │   ├── filter
    │               │   │   ├── JwtAuthenticationFilter.java
    │               │   │   └── JwtValidationFilter.java
    │               │   ├── SecurityConfig.java
    │               │   ├── SimpleGrantedAuthorityJsonCreator.java
    │               │   └── TokenJwtConfig.java
    │               ├── service
    │               │   ├── imp
    │               │   │   ├── JpaUserDatailService.java
    │               │   │   ├── RolesService.java
    │               │   │   ├── TopicoService.java
    │               │   │   └── UsuarioService.java
    │               │   ├── IRolesService.java
    │               │   ├── ITopicoService.java
    │               │   └── IUsuarioService.java
    │               └── ChallengeforohubApplication.java
    └── resources
        ├── static
        └── templates


```

## Contribuir

- Realiza un fork de este repositorio.
- Crea tu nueva rama (git checkout -b feature/nueva-caracteristica).
- Realiza tus cambios y haz commit (git commit -am 'Añadir nueva característica').
- Haz push a tu rama (git push origin feature/nueva-caracteristica).
- Abre un pull request.

## Licencia

- Licencia MIT

  ```markdown
  Este proyecto está licenciado bajo la Licencia MIT.  
  Consulta el archivo LICENSE para más detalles.
  ```

- Licencia [LICENSE](LICENSE)

## Créditos

- Desarrollado como parte del desafío de especialización **Java Backend Developer** by [**Caseli Layza**](mailto:casseli.layza@gmail.com).
  Inspirado en la necesidad de manejar conversiones de monedas y registrar un historial de las mismas.

- 📧 **Correo:** [Caseli Layza](mailto:casseli.layza@gmail.com)

  ```go
    Este archivo `README.md` contiene toda la información necesaria sobre el proyecto, desde la descripción hasta los pasos para instalar, ejecutar y contribuir.
  ```

  _**¡Gracias por revisar este proyecto!... Powered by Casse 💖💚🔥💪😊...!!**_

### Derechos Reservados

- Sobre los derechos reservados si es aplicable.

  ```markdown
  © 2024 Casseli L CodeCloudNet. Todos los derechos reservados.  
  Este proyecto está protegido por las leyes de derechos de autor y no puede ser reproducido, distribuido ni utilizado sin autorización previa.
  ```

<div style="text-align: rigt;">
    <img src="src/main/resources/asset/img/logo.webp" width="300" height="300">
</div>
