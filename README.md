# Todo API

API REST para gestionar tareas con Spring Boot y almacenamiento en memoria.

## Descripción

Esta aplicación permite crear, consultar, actualizar, eliminar y marcar tareas como completadas. Los datos se guardan en memoria durante la ejecución del proceso; al reiniciar la aplicación, la colección de tareas se reinicia.

## Requisitos

- Java 21+
- Maven 3.9+
- Sistema operativo compatible con Java

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

Si el puerto 8080 está ocupado, puedes cambiarlo al arrancar:

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

## Base URL

```text
/api/tasks
```

## Endpoints

### 1) Obtener todas las tareas

- Método: GET
- Ruta: `/api/tasks`
- Descripción: devuelve la lista completa de tareas.

Ejemplo:

```bash
curl http://localhost:8080/api/tasks
```

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

## Nota importante

La API usa un repositorio en memoria, por lo que los datos desaparecen al reiniciar la aplicación.

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
- Persistencia: memoria (ConcurrentHashMap)
- Puerto por defecto: 8080
