# Sistema de Inventario Nexos

Este proyecto consiste en un sistema de gestión de inventario desarrollado con Spring Boot (backend) y Angular (frontend).

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

1. **Java 24** - [Descargar JDK](https://www.oracle.com/java/technologies/downloads/)
2. **Maven** - [Descargar Maven](https://maven.apache.org/download.cgi)
3. **Node.js y npm** - [Descargar Node.js](https://nodejs.org/)
4. **Angular CLI** - Instalar con `npm install -g @angular/cli`
5. **PostgreSQL** - [Descargar PostgreSQL](https://www.postgresql.org/download/)

## Configuración de la Base de Datos

1. Instala PostgreSQL si aún no lo tienes.
2. Abre pgAdmin o la herramienta que prefieras para administrar PostgreSQL.
3. Crea una nueva base de datos llamada `mi_basedatos`.
4. Crea un usuario con los siguientes datos:
   - Usuario: `admin`
   - Contraseña: `admin123`
5. Asigna todos los privilegios de la base de datos `mi_basedatos` a este usuario.

Alternativamente, puedes modificar la configuración de la base de datos en el archivo `backend/src/main/resources/application.properties` para que coincida con tu configuración existente.

## Pasos para Ejecutar el Proyecto

### Configuración y Ejecución del Backend

1. Abre una terminal y navega a la carpeta del backend:

   ```
   cd c:\Users\bravorc\mio\nexos\inventory\backend
   ```

2. Compila el proyecto con Maven:

   ```
   ./mvnw clean install
   ```

   En Windows, usa `mvnw.cmd` en lugar de `./mvnw`:

   ```
   mvnw.cmd clean install
   ```

3. Ejecuta la aplicación Spring Boot:

   ```
   ./mvnw spring-boot:run
   ```

   En Windows:

   ```
   mvnw.cmd spring-boot:run
   ```

   El backend se ejecutará en http://localhost:8080/nexos/api

### Configuración y Ejecución del Frontend

1. Abre una nueva terminal y navega a la carpeta del frontend:

   ```
   cd c:\Users\bravorc\mio\nexos\inventory\frontend
   ```

2. Instala las dependencias:

   ```
   npm install
   ```

3. Inicia la aplicación Angular:

   ```
   npm start
   ```

   El frontend se ejecutará en http://localhost:4200

4. Abre tu navegador web y accede a http://localhost:4200 para usar la aplicación.

## Estructura del Proyecto

### Backend (Spring Boot)

- **Controladores**: Gestionan las solicitudes HTTP.
- **Servicios**: Contienen la lógica de negocio.
- **Repositorios**: Se comunican con la base de datos.
- **Entidades**: Representan las tablas de la base de datos.
- **DTOs**: Objetos de transferencia de datos entre capas.

### Frontend (Angular)

- **Componentes**: Elementos visuales reutilizables.
- **Servicios**: Gestionan la comunicación con el backend.
- **Interfaces**: Definen la estructura de los datos.
- **Módulos**: Organizan el código en secciones funcionales.

## Funcionalidades

El sistema permite:

1. Registrar mercancía nueva al inventario
2. Listar mercancía disponible
3. Buscar mercancía por diferentes criterios
4. Gestionar usuarios del sistema
5. Asignar roles y permisos

## Solución de Problemas

### Problemas con el Backend

- **Error de conexión a la base de datos**: Verifica la configuración en `application.properties` y asegúrate de que PostgreSQL esté en ejecución.
- **Error de compilación**: Asegúrate de tener instalada la versión correcta de Java (Java 24).

### Problemas con el Frontend

- **Error de dependencias**: Ejecuta `npm install` nuevamente.
- **Error de CORS**: Si hay problemas de comunicación entre frontend y backend, verifica la configuración de CORS en el backend.

## Recursos Adicionales

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Angular](https://angular.io/docs)
- [Documentación de PostgreSQL](https://www.postgresql.org/docs/)

## Contacto

Para preguntas o soporte, por favor contacta al equipo de desarrollo.
