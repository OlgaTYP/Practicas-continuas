[![CI](https://github.com/OlgaTYP/Practicas-continuas/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/OlgaTYP/Practicas-continuas/actions/workflows/ci.yml)

# Todo API - Rama A y B (resuelto)

API REST para gestionar tareas con Spring Boot, Spring Data JPA y PostgreSQL.

## Descripción

Esta aplicación permite crear, consultar, actualizar, eliminar y marcar tareas como completadas. Los datos se persisten en PostgreSQL mediante Spring Data JPA y Hibernate.

## Requisitos

- Java 21+
- Maven 3.9+
- PostgreSQL 16+
- Sistema operativo compatible con Java

## Configurar PostgreSQL

La aplicación espera una base de datos `todo_api` en `localhost:5432`. Para levantar una instancia local de desarrollo con Docker:

```bash
docker run --name todo-postgres \
  -e POSTGRES_DB=todo_api \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:16
```

La URL, el usuario y la contraseña se pueden personalizar mediante `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`. Los valores predeterminados (`postgres`/`postgres`) son solo para desarrollo local; utiliza credenciales seguras fuera de ese entorno. Hibernate actualiza el esquema existente al iniciar (`spring.jpa.hibernate.ddl-auto=update`).

## Arranque

### Opción 1: compilar y ejecutar el JAR generado

```bash
mvn package
java -jar target/todo-api.jar
```

La API queda disponible por defecto en:

```text
http://localhost:8080
```

Si el puerto 8080 está ocupado, puedes cambiarlo al 8081 al arrancar:

```bash
java -jar target/todo-api.jar --server.port=8081
```

Y luego consultar:

```text
http://localhost:8081
```

### Opción 2: ejecutar directamente desde el jar ya construido

```bash
java -jar target/todo-api.jar
```

## Levantar el proyecto con Docker

Requisitos: Docker Engine o Docker Desktop con Docker Compose v2.

Desde la raíz del repositorio, crea el fichero de configuración a partir del ejemplo:

```bash
cp .env.example .env
```

Edita `.env` y, antes de usarlo fuera de un entorno local, cambia `POSTGRES_PASSWORD`. El ejemplo configura PostgreSQL 16 con la base de datos `tareas` y el usuario `app`.

Construye la imagen de la API y levanta la base de datos y la aplicación:

```bash
docker compose up --build -d
```

Comprueba que los servicios estén activos:

```bash
docker compose ps
```

La API queda disponible en `http://localhost:8080`. Para comprobar su estado accede a `http://localhost:8080/actuator/health`, la respuesta esperada es `{"status":"UP"}.` 

Para consultar los registros:

```bash
docker compose logs -f
```

Detén los servicios conservando los datos de PostgreSQL con:

```bash
docker compose down
```

Los datos se guardan en el volumen `db-data`. Para eliminar también ese volumen y todos los datos de la base de datos:

```bash
docker compose down -v
```
Advertencia: `docker compose down -v` elimina los datos persistidos de PostgreSQL.

La imagen de la API compila con Maven 3.9 y Eclipse Temurin Java 21, y se ejecuta con Eclipse Temurin JRE 21. PostgreSQL usa la imagen `postgres:16`.

## Levantar el proyecto con Dev Container

### Requisitos: 
- Docker Engine instalado y en ejecución.
- Visual Studio Code.
- Extensión **Dev Containers** de Microsoft.

### Abrir el entorno
Abre la raíz del repositorio en VS Code y, desde la paleta de comandos (`Ctrl+Shift+P`), selecciona **Dev Containers: Reopen in Container**. 

La configuración de `.devcontainer/devcontainer.json` usa la imagen `mcr.microsoft.com/devcontainers/java:21`, instala Java 21 y Maven, y configura Docker-in-Docker. Al crear el contenedor, se copia `.env.example` a `.env` si aún no existe y se ejecuta `mvn -B dependency:go-offline` para descargar las dependencias.

El fichero .env debe mantenerse en local. Este fichero no debe subirse al repositorio.

### Arrancar la aplicación
En la terminal integrada del contenedor, inicia PostgreSQL con Docker Compose:

```bash
docker compose up --build -d
```
Este comando construye la imagen de la API y arranca PostgreSQL y la aplicación. La API espera a que PostgreSQL esté saludable antes de iniciar.

Comprueba el estado de los servicios:
```bash
docker compose ps
```
Comprueba el estado de la API en :
```text
http://127.0.0.1:8080/actuator/health
```
La respuesta esperada es `{"status":"UP"}`.

Para consultar los registros:
```bash
docker compose logs -f
```
Para detener los servicios sin eliminar los datos de PostgreSQL:
```bash
docker compose down
```
Los datos de PostgreSQL se conservan en el volumen `db-data`. Para eliminar también ese volumen y los datos almacenados, utiliza `docker compose down -v` únicamente cuando quieras reiniciar la base de datos desde cero.
## Formateo automático con Spotless

Este proyecto usa Spotless para mantener un único estilo Java en todo el repositorio. Puedes comprobarlo con:

```bash
mvn spotless:check
```

Y aplicar el formato automáticamente con:

```bash
mvn spotless:apply
```

### Hook de pre-commit

Para que cada commit se formatee antes de guardarse, el repositorio usa un hook local en `.githooks`:

```bash
mkdir -p .githooks
cat > .githooks/pre-commit <<'EOF'
#!/bin/sh
echo "Formatting with Spotless..."
mvn -q spotless:apply || exit 1
git add -u
EOF
chmod +x .githooks/pre-commit
git config core.hooksPath .githooks
```

Esto hace que el hook viaje con el repositorio y cada persona que lo clone tenga que ejecutar ese `git config` una vez. El hook puede saltarse con `git commit --no-verify`, pero en CI debe quedarse como comprobación obligatoria para evitar commits sin formatear.

## Base URL

```text
/api/tasks
```

## Endpoints

### 1) Obtener todas las tareas

- Método: GET
- Ruta: `/api/tasks`
- Descripción: devuelve las tareas y permite filtrar opcionalmente por estado, prioridad y texto del título. Los filtros se pueden combinar.

Ejemplo:

```bash
curl http://localhost:8080/api/tasks
```

Ejemplo de búsqueda parcial del título (sin distinguir entre mayúsculas y minúsculas):

```bash
curl "http://localhost:8080/api/tasks?title=revisi%C3%B3n"
```

Los filtros pueden combinarse, por ejemplo:

```bash
curl "http://localhost:8080/api/tasks?status=TODO&priority=HIGH&title=sprint"
```

También se pueden ordenar por fecha límite con `sortBy=deadline` y `direction=asc|desc` (por defecto `asc`). Si no se indica `sortBy`, se mantiene el orden actual. La ordenación se puede combinar con los filtros:

```bash
curl "http://localhost:8080/api/tasks?sortBy=deadline"
curl "http://localhost:8080/api/tasks?sortBy=deadline&direction=desc"
curl "http://localhost:8080/api/tasks?status=TODO&sortBy=deadline&direction=asc"
```

Un valor no válido en `sortBy` o `direction` devuelve `400 Bad Request`.

Si no hay tareas coincidentes, la API devuelve `200 OK` con una lista vacía.

Respuesta esperada (ejemplo):

```json
[
  {
    "id": 1,
    "title": "Preparar la revisión del sprint",
    "description": "Revisar el sprint con el equipo y actualizar el backlog",
    "status": "TODO",
    "priority": "HIGH",
    "deadline": "2026-09-30",
    "createdAt": "2026-09-22T07:30:00",
    "updatedAt": "2026-09-22T07:30:00"
  },
  {
    "id": 2,
    "title": "Corregir bugs del login",
    "description": "Revisar el flujo de autenticación y cerrar incidencias abiertas",
    "status": "IN_PROGRESS",
    "priority": "URGENT",
    "deadline": "2026-09-25",
    "createdAt": "2026-09-22T08:00:00",
    "updatedAt": "2026-09-22T08:15:00"
  }
]
```

### 2) Obtener una tarea por su id

- Método: GET
- Ruta: `/api/tasks/{id}`
- Descripción: devuelve una tarea concreta.

Ejemplo:

```bash
curl http://localhost:8080/api/tasks/1
```

Respuesta esperada:

```json
{
  "id": 1,
  "title": "Preparar la revisión del sprint",
  "description": "Revisar el sprint con el equipo y actualizar el backlog",
  "status": "TODO",
  "priority": "HIGH",
  "deadline": "2026-09-30",
  "createdAt": "2026-09-22T07:30:00",
  "updatedAt": "2026-09-22T07:30:00"
}
```

### 3) Crear una tarea

- Método: POST
- Ruta: `/api/tasks`
- Código HTTP esperado: `201 Created`
- Descripción: crea una nueva tarea.

Ejemplo de petición:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Preparar la revisión del sprint",
    "description": "Revisar el sprint con el equipo y actualizar el backlog",
    "status": "TODO",
    "priority": "HIGH",
    "deadline": "2026-09-30"
  }'
```

Respuesta esperada:

```json
{
  "id": 1,
  "title": "Preparar la revisión del sprint",
  "description": "Revisar el sprint con el equipo y actualizar el backlog",
  "status": "TODO",
  "priority": "HIGH",
  "deadline": "2026-09-30",
  "createdAt": "2026-09-22T07:30:00",
  "updatedAt": "2026-09-22T07:30:00"
}
```

### 4) Actualizar una tarea

- Método: PUT
- Ruta: `/api/tasks/{id}`
- Descripción: reemplaza los valores de la tarea existente.

Ejemplo de petición:

```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Preparar la revisión del sprint actualizada",
    "description": "Revisar el sprint con el equipo, analizar riesgos y actualizar el backlog",
    "status": "IN_PROGRESS",
    "priority": "URGENT",
    "deadline": "2026-10-02"
  }'
```

Respuesta esperada:

```json
{
  "id": 1,
  "title": "Preparar la revisión del sprint actualizada",
  "description": "Revisar el sprint con el equipo, analizar riesgos y actualizar el backlog",
  "status": "IN_PROGRESS",
  "priority": "URGENT",
  "deadline": "2026-10-02",
  "createdAt": "2026-09-22T07:30:00",
  "updatedAt": "2026-09-22T07:45:00"
}
```

### 5) Eliminar una tarea

- Método: DELETE
- Ruta: `/api/tasks/{id}`
- Código HTTP esperado: `204 No Content`
- Descripción: elimina la tarea con el id indicado.

Ejemplo:

```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

Respuesta: sin contenido, con estado HTTP `204`.

### 6) Marcar tarea como completada

- Método: PUT
- Ruta: `/api/tasks/{id}/done`
- Descripción: cambia el estado de la tarea a `DONE` si no estaba ya completada.

Ejemplo:

```bash
curl -X PUT http://localhost:8080/api/tasks/1/done
```

Respuesta esperada:

```json
{
  "id": 1,
  "title": "Preparar la revisión del sprint",
  "description": "Revisar el sprint con el equipo y actualizar el backlog",
  "status": "DONE",
  "priority": "HIGH",
  "deadline": "2026-09-30",
  "createdAt": "2026-09-22T07:30:00",
  "updatedAt": "2026-09-22T07:50:00"
}
```

## Validaciones

Los campos de una tarea se validan según estas reglas:

- `title`: obligatorio, entre 3 y 150 caracteres.
- `description`: obligatorio, entre 5 y 1000 caracteres.
- `status`: obligatorio y debe ser uno de estos valores:
  - `TODO`
  - `IN_PROGRESS`
  - `DONE`
  - `CANCELLED`
- `priority`: obligatorio y debe ser uno de estos valores:
  - `LOW`
  - `MEDIUM`
  - `HIGH`
  - `URGENT`
- `deadline`: obligatorio y no puede ser una fecha anterior a hoy.

## Errores

La API devuelve un formato de error estándar con la estructura:

```json
{
  "timestamp": "2026-09-22T07:50:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task with id 99 was not found",
  "path": "/api/tasks/99"
}
```

### Códigos HTTP de error comunes

- `400 Bad Request`: validación fallida o regla de negocio no cumplida.
- `404 Not Found`: recurso solicitado no existe.

Ejemplo de error de validación:

```json
{
  "timestamp": "2026-09-22T07:52:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Title is required",
  "path": "/api/tasks"
}
```

## Ejemplo rápido de flujo completo

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Comprar materiales",
    "description": "Adquirir todo el material para el proyecto de infraestructura",
    "status": "TODO",
    "priority": "MEDIUM",
    "deadline": "2026-09-25"
  }'

curl http://localhost:8080/api/tasks

curl -X PUT http://localhost:8080/api/tasks/1/done
```

## Información técnica

- Framework: Spring Boot 3.3.4
- Java: 21
- Persistencia: PostgreSQL mediante Spring Data JPA y Hibernate
- Puerto por defecto: 8080
