# Documentación técnica del proyecto Todo API

## 1. Descripción general

Este proyecto consiste en el desarrollo de una API REST para la gestión de tareas, implementada con Spring Boot y Java 21. La aplicación permite crear, consultar, actualizar, eliminar y marcar tareas como finalizadas, manteniendo la información en memoria durante la ejecución del servicio.

La solución incluye las siguientes capacidades principales:

- API REST con operaciones CRUD sobre tareas
- Validación de entradas mediante Jakarta Validation
- Manejo centralizado de errores y respuestas estructuradas en JSON
- Formateo automático del código con Spotless
- Configuración de hooks de Git para evitar commits con formato incorrecto
- Documentación técnica y de uso del proyecto
- Resolución y gestión de conflictos en Git

## 2. Tecnologías empleadas

### 2.1 Backend
- Java 21
- Spring Boot 3.3.4
- Maven 3.9+
- Jakarta Validation

### 2.2 Herramientas de calidad y control de versiones
- Spotless 2.43.0
- Git
- Hook local de pre-commit configurado en `.githooks`

### 2.3 Asistencia de IA
- GitHub Copilot
- Modelo: MAI-Code-1.1-Flash

## 3. Estructura del proyecto

```text
todo-api/
├── .githooks/
│   └── pre-commit
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/todoapi/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── TodoApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/todoapi/
├── README.md
├── DOCUMENTACION.md
├── pom.xml
└── target/
```

## 4. Requisitos previos

- Java 21 o superior
- Maven 3.9 o superior
- Sistema operativo compatible con Java

## 5. Procedimiento de ejecución

### 5.1 Generación del artefacto y arranque

```bash
mvn clean package
java -jar target/todo-api.jar
```

### 5.2 Ejecución con puerto alternativo

```bash
java -jar target/todo-api.jar --server.port=8081
```

### 5.3 Verificación del servicio

La API queda disponible en la siguiente dirección:

```text
http://localhost:8080
```

## 6. Endpoints expuestos

Base URL:

```text
/api/tasks
```

### GET /api/tasks

Devuelve todas las tareas almacenadas en memoria.

Ejemplo:

```bash
curl http://localhost:8080/api/tasks
```

### GET /api/tasks/{id}

Devuelve una tarea por su identificador.

Ejemplo:

```bash
curl http://localhost:8080/api/tasks/1
```

### POST /api/tasks

Crea una nueva tarea.

Ejemplo:

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

### PUT /api/tasks/{id}

Actualiza una tarea existente.

Ejemplo:

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

### DELETE /api/tasks/{id}

Elimina una tarea por id.

Ejemplo:

```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```

### PUT /api/tasks/{id}/done

Marca una tarea como completada.

Ejemplo:

```bash
curl -X PUT http://localhost:8080/api/tasks/1/done
```

## 7. Modelo de datos

La entidad principal del sistema es `Task`, que incluye los siguientes atributos:

- `id`
- `title`
- `description`
- `status`
- `priority`
- `deadline`
- `createdAt`
- `updatedAt`

Los valores de `status` y `priority` se gestionan mediante enumeraciones (enums), lo que permite controlar las opciones válidas y reforzar la consistencia del dominio de la aplicación.

## 8. Validación y manejo de errores

La aplicación incorpora validación de entrada mediante anotaciones de Jakarta Validation, así como un mecanismo centralizado de tratamiento de excepciones. Se han definido clases de excepción específicas para:

- recurso no encontrado
- errores de reglas de negocio
- errores genéricos del sistema

Esto permite devolver respuestas JSON estructuradas y comprensibles tanto en entornos de desarrollo como en pruebas funcionales.

## 9. Problemas detectados y soluciones aplicadas

### 9.1 Error de binding en operaciones PUT

Durante la validación de la API, se detectó un problema de binding en Spring relacionado con la extracción de parámetros por nombre al utilizar `@PathVariable`. La solución consistió en declarar explícitamente el nombre del parámetro:

```java
@GetMapping("/{id}")
public Task getTaskById(@PathVariable("id") Long id)
```

Además, se añadió la siguiente configuración en el `pom.xml` para preservar los metadatos de parámetros del compilador:

```xml
<parameters>true</parameters>
```

### 9.2 Problemas de formato de JSON con PowerShell

Al realizar pruebas con `curl` desde PowerShell, se observaron dificultades con el manejo de JSON inline y comillas. La solución recomendada fue emplear `curl.exe` y enviar el cuerpo como contenido binario desde un archivo o con una cadena correctamente escapada.

### 9.3 Consulta de tareas vacías

Cuando la base de datos en memoria no contenía tareas, la respuesta esperada era una lista vacía, representada como `[]`. Este comportamiento no era un error funcional, sino el resultado correcto del estado inicial de la aplicación.

## 10. Formateo automático con Spotless

Se añadió el plugin de Spotless a `pom.xml`:

```xml
<plugin>
  <groupId>com.diffplug.spotless</groupId>
  <artifactId>spotless-maven-plugin</artifactId>
  <version>2.43.0</version>
  <configuration>
    <java>
      <googleJavaFormat/>
      <removeUnusedImports/>
      <trimTrailingWhitespace/>
      <endWithNewline/>
    </java>
  </configuration>
</plugin>
```

### Comandos de comprobación

```bash
mvn spotless:check
```

Comprueba si el código cumple el formato esperado. Si hay diferencias, falla el comando.

```bash
mvn spotless:apply
```

Aplica automáticamente el formato estandarizado.

## 11. Hook de pre-commit

Se configuró un hook local para que durante cada commit se ejecute automáticamente el formateo antes de guardar cambios.

Archivo:

```text
.githooks/pre-commit
```

Contenido:

```sh
#!/bin/sh
echo "Formatting with Spotless..."
mvn -q spotless:apply || exit 1
git add -u
```

Configuración Git:

```bash
git config core.hooksPath .githooks
```

Importante:

- Los hooks de Git viven en `.git/hooks` y no se versionan.
- Por eso se usa `.githooks` y `core.hooksPath` para que el hook viaje con el repositorio.
- Cada clon necesita ejecutar esa configuración una vez.
- CI debe seguir validando con `mvn spotless:check` para no depender únicamente del hook local.

## 12. Gestión de Git y resolución de conflictos

Se empleó un flujo de trabajo basado en ramas y commits para mantener una trazabilidad clara del desarrollo. En este contexto, se realizaron varias ramas con modificaciones parciales sobre el mismo archivo y se gestionaron los conflictos resultantes según la práctica estipulada.

### 12.1 Commits relevantes

```bash
git add README.md
git commit -m "docs(api): añade endpoints y ejemplos JSON"

git add README.md pom.xml src/main/java src/test/java
git commit -m "chore(build): añade Spotless para formateo automático"

git add .githooks/pre-commit
git commit -m "chore(hooks): añade hook de pre-commit con Spotless"
```

### 12.2 Creación de conflicto intencionado

Se generó un conflicto de merge mediante dos ramas que modificaban la misma línea de `README.md` de forma distinta:

```bash
git switch main
git checkout -b feature/conflict-a
git checkout -b feature/conflict-b
```

Tras fusionar la primera rama y luego la segunda, Git marcó el conflicto de contenido en el archivo. La resolución se realizó revisando manualmente el fichero conflictivo, eliminando los marcadores `<<<<<<<`, `=======` y `>>>>>>>`, y dejando la versión final correcta. Posteriormente se ejecutó:

```bash
git add README.md
git commit -m "merge: resuelve conflicto en README"
```

## 13. Comandos ejecutados durante la validación

A continuación, se recogen los comandos más importantes utilizados durante la ejecución del proyecto y la validación del trabajo:

```bash
mvn clean package
java -jar target/todo-api.jar
mvn spotless:check
mvn spotless:apply
curl http://localhost:8080/api/tasks
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{...}'
curl -X PUT http://localhost:8080/api/tasks/1 -H "Content-Type: application/json" -d '{...}'
curl -X DELETE http://localhost:8080/api/tasks/1
git status --short --branch
git log --oneline --graph --all
git config core.hooksPath .githooks
```

## 14. Estado final del proyecto

La aplicación se encuentra en un estado funcional y documentado, con capacidad para:

- ejecutarse localmente
- probar los endpoints REST
- validar el formato del código mediante Spotless
- prevenir commits con estilo incorrecto mediante hooks
- mantener un flujo Git ordenado y reproducible

## 15. Conclusión

El presente proyecto ha permitido desarrollar una API REST funcional para la gestión de tareas, reforzando aspectos clave como la validación de datos, el control de errores, la calidad del código y la utilización de trabajo con Git. La incorporación de Spotless y de un hook de pre-commit ha mejorado la automatización del repositorio y ha permitido que el proceso de desarrollo siga estándares mínimos de calidad. Asimismo, la resolución del conflicto de merge demuestra la capacidad del proyecto para gestionar cambios concurrentes y mantener la integridad del código en entornos colaborativos.

En conjunto, la práctica ha permitido obtener conocimientos de Spring Boot, control de versiones, automatización de calidad y documentación técnica, dejando el repositorio listo para su entrega final.
