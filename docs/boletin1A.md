# Boletín 1: Git y proyecto Maven

> Memoria de la práctica 1. Estudiante: Olga Yanes ([@OlgaTYP](https://github.com/OlgaTYP))
> Repositorio: <https://github.com/OlgaTYP/Practicas-continuas>

## 1. Resumen de commits

| #   | Mensaje del commit                                        | Qué se hizo                                                                                                                              | Rama                    | Autor   | Enlace                                                                   |
| --- | --------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- | ----------------------- | ------- | ------------------------------------------------------------------------ |
| 1   | `chore: proyecto Maven inicial de la API de tareas`       | Proyecto Maven + Spring Boot completo de la API REST de tareas, con `.gitignore`, `.gitattributes`, README y tests unitarios.            | `main`                  | OlgaTYP | [1bb3b87](https://github.com/OlgaTYP/Practicas-continuas/commit/1bb3b87) |
| 2   | `chore(editor): añade .editorconfig`                      | Archivo `.editorconfig` para unificar codificación, finales de línea e indentación.                                                      | `main`                  | OlgaTYP | [943de0d](https://github.com/OlgaTYP/Practicas-continuas/commit/943de0d) |
| 3   | `docs(api): añade endpoints y ejemplos JSON`              | Cambio en la rama de trabajo: se aclara en el README cómo arrancar la API en otro puerto (8081). Fusionado en `main` por _fast-forward_. | `feat/descripcion-feat` | OlgaTYP | [665dc1a](https://github.com/OlgaTYP/Practicas-continuas/commit/665dc1a) |
| 4   | `chore(build): añade Spotless para formateo automático`   | Plugin Spotless en el `pom.xml` y primer `spotless:apply` sobre todo el código Java.                                                     | `main`                  | OlgaTYP | [feae1a1](https://github.com/OlgaTYP/Practicas-continuas/commit/feae1a1) |
| 5   | `chore(hooks): añade hook de pre-commit con Spotless`     | Hook versionado en `.githooks/pre-commit` que ejecuta `mvn -q spotless:apply` y `git add -u`.                                            | `main`                  | OlgaTYP | [4b2d9fb](https://github.com/OlgaTYP/Practicas-continuas/commit/4b2d9fb) |
| 6   | `test(hook): autoformatea con Spotless`                   | Prueba del hook: se desordenó la indentación de una clase y se hizo commit; el hook la reformateó antes de guardarla.                    | `main`                  | OlgaTYP | [d96108b](https://github.com/OlgaTYP/Practicas-continuas/commit/d96108b) |
| 7   | `feat(readme): cambia titulo en rama A`                   | Cambio del título del README a `# Todo API - Rama A`.                                                                                    | `feature/conflict-a`    | OlgaTYP | [34e4555](https://github.com/OlgaTYP/Practicas-continuas/commit/34e4555) |
| 8   | `feat(readme): cambia titulo en rama B`                   | Cambio de la **misma línea** del README a `# Todo API - Rama B`.                                                                         | `feature/conflict-b`    | OlgaTYP | [a635cf6](https://github.com/OlgaTYP/Practicas-continuas/commit/a635cf6) |
| 9   | `merge: resuelve conflicto en README`                     | Commit de merge de `feature/conflict-b` en `main` con el conflicto resuelto a mano.                                                      | `main`                  | OlgaTYP | [e00759c](https://github.com/OlgaTYP/Practicas-continuas/commit/e00759c) |
| 10  | `docs(project): añade documentación final de la práctica` | Documentación final del proyecto (`DOCUMENTACION.md`).                                                                                   | `main`                  | OlgaTYP | [9226c71](https://github.com/OlgaTYP/Practicas-continuas/commit/9226c71) |

## 2. Detalle por commit

### 1. `chore: proyecto Maven inicial de la API de tareas`

- **Qué se hizo:** se inicializó el repositorio y se añadió el proyecto generado: entidad `Task` con validaciones Bean Validation (título, descripción, estado, prioridad, fecha límite), capas controlador / servicio / repositorio, repositorio en memoria (`InMemoryTaskRepository` sobre `ConcurrentHashMap`), manejo centralizado de errores con `@RestControllerAdvice` (404 para recursos inexistentes) y tests con JUnit 5. También se incluyeron `.gitignore` y `.gitattributes`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Partes A, B y el inicio de la Parte D.
- **Uso de IA:** GitHub Copilot, para generar la estructura del proyecto y el dominio de la API. El resultado se revisó manualmente.

### 2. `chore(editor): añade .editorconfig`

- **Qué se hizo:** se añadió `.editorconfig` (UTF-8, finales `lf`, salto de línea final, indentación con espacios; 2 espacios en YAML/JSON). En este commit la indentación general quedó en 4 espacios; más tarde se corrigió a 2, como pide el enunciado (ver apartado 5).
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte D. Se hizo en un commit propio de tipo `chore`, porque es configuración de herramientas.
- **Uso de IA:** no se utilizó IA.

### 3. `docs(api): añade endpoints y ejemplos JSON`

- **Qué se hizo:** en la rama `feat/descripcion-feat` se modificó el README para indicar cómo arrancar la aplicación en el puerto 8081 si el 8080 está ocupado (`java -jar target/todo-api.jar --server.port=8081`). La rama se fusionó después en `main`; como `main` no tenía commits nuevos, Git hizo un _fast-forward_ y el historial quedó lineal.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte D (crear una rama, hacer commit y fusionarla en `main`). El enunciado pedía un cambio de código con un commit `feat(...)`; aquí el cambio fue de documentación (ver apartado 5).
- **Uso de IA:** GitHub Copilot, para redactar el texto.

### 4. `chore(build): añade Spotless para formateo automático`

- **Qué se hizo:** se añadió `spotless-maven-plugin` 2.43.0 al `pom.xml` con `googleJavaFormat`, `removeUnusedImports`, `trimTrailingWhitespace` y `endWithNewline`. Además, se ejecutó `mvn spotless:apply`, que reformateó todos los ficheros Java al estilo de Google.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte E.
- **Uso de IA:** GitHub Copilot, para integrar la configuración del plugin.

### 5. `chore(hooks): añade hook de pre-commit con Spotless`

- **Qué se hizo:** se creó `.githooks/pre-commit` y se configuró `git config core.hooksPath .githooks` para que Git use esa carpeta. Este paso de configuración está documentado en el README y en `CONTRIBUTING.md`, porque cada persona que clone el repositorio debe ejecutarlo una vez.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte E (automatizar el formateo en cada commit).
- **Uso de IA:** GitHub Copilot, para verificar la configuración del hook.

### 6. `test(hook): autoformatea con Spotless`

- **Qué se hizo:** se desordenó a propósito la indentación de una clase Java y se lanzó `git commit`. El hook ejecutó Spotless, devolvió el archivo a su formato correcto y lo volvió a añadir con `git add -u`. Por eso el commit no contiene ningún cambio en el código: el código desordenado nunca llegó al repositorio, y eso demuestra que el hook funciona.
- **Autor:** OlgaTYP
- **Relación con el boletín:** comprobación final de la Parte E.
- **Uso de IA:** no se utilizó IA.

### 7. `feat(readme): cambia titulo en rama A`

- **Qué se hizo:** en la rama `feature/conflict-a`, creada desde `main`, se cambió la primera línea del README a `# Todo API - Rama A`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte F. Esta rama se fusionó primero en `main` sin conflictos (_fast-forward_).
- **Uso de IA:** no se utilizó IA.

### 8. `feat(readme): cambia titulo en rama B`

- **Qué se hizo:** en la rama `feature/conflict-b`, creada desde el mismo punto de `main`, se cambió la misma línea a `# Todo API - Rama B`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte F. Al fusionar esta segunda rama se produjo el conflicto.
- **Uso de IA:** no se utilizó IA.

### 9. `merge: resuelve conflicto en README`

- **Qué se hizo:** Git marcó el conflicto en `README.md`. Se editó a mano, se eliminaron los marcadores `<<<<<<<`, `=======` y `>>>>>>>` y se dejó como versión final `# Todo API - Rama A y B (resuelto)`. Después se cerró con `git add` y `git commit`. Es un commit de merge con dos padres (`34e4555` y `a635cf6`).
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte F.
- **Uso de IA:** no se utilizó IA.

### 10. `docs(project): añade documentación final de la práctica`

- **Qué se hizo:** se redactó `DOCUMENTACION.md`, con la documentación formal del proyecto y del proceso seguido.
- **Autor:** OlgaTYP
- **Relación con el boletín:** cierre y entrega del boletín.
- **Uso de IA:** GitHub Copilot, para estructurar y redactar el documento.

## 3. Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Instalación y verificación del entorno

Se comprobó que Git, el JDK 21 y Maven estaban instalados (`git --version`, `java -version`, `mvn -version`). Después se configuró la identidad de Git (`user.name` y `user.email`) para que los commits tuviesen un autor claro (OlgaTYP). También se activó `pull.ff only`, para evitar merges automáticos inesperados, y `init.defaultBranch main`.

### Parte B — Generación de la aplicación con ayuda de IA

Con ayuda de GitHub Copilot se generó una API REST de gestión de tareas (To-Do) con Spring Boot 3, Java 21 y Maven. La persistencia se hace en memoria, sin base de datos, mediante `InMemoryTaskRepository`, que guarda los datos en un `ConcurrentHashMap`. La aplicación cumple los requisitos de "no trivial":

- **Entidad con validaciones:** `Task`, con `id`, `title` (`@NotBlank`, `@Size`), `description`, `status`, `priority` y `deadline` (`@FutureOrPresent`), además de las fechas de creación y modificación.
- **CRUD completo:** `POST`, `GET`, `PUT` y `DELETE` sobre `/api/tasks`, más `PUT /api/tasks/{id}/done` para marcar una tarea como completada.
- **Capas:** `TaskController` → `TaskService` → `TaskRepository`.
- **Errores centralizados:** `GlobalExceptionHandler` (`@RestControllerAdvice`) devuelve un `ApiError` en JSON: 404 para `ResourceNotFoundException` y 400 para las violaciones de validación y de reglas de negocio (`BusinessRuleException`).
- **Tests de reglas de negocio** en `TaskServiceTest` (unitarios, con Mockito y sin contexto de Spring): rechazar una fecha límite pasada, rechazar un título vacío, crear una tarea válida, actualizar una tarea inexistente, no completar dos veces la misma tarea y borrar una tarea inexistente. Además, el proyecto incluye `TaskControllerTest`, que usa `@WebMvcTest` (ver apartado 5).

El código generado se revisó manualmente para comprobar que cumplía los requisitos del boletín.

### Parte C — Construcción y pruebas en local

Se empaquetó la aplicación con `mvn clean package`. Gracias a `spring-boot-maven-plugin` y a `<finalName>todo-api</finalName>`, se genera el JAR ejecutable con todas las dependencias (_fat JAR_) `target/todo-api.jar`, que se arranca con:

```bash
java -jar target/todo-api.jar
```

Con la aplicación en marcha se probaron los endpoints con `curl`, usando los ficheros de ejemplo `task.json` y `task-update.json`:

```bash
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d @task.json
curl http://localhost:8080/api/tasks/1
curl -X PUT http://localhost:8080/api/tasks/1 -H "Content-Type: application/json" -d @task-update.json
curl -X DELETE http://localhost:8080/api/tasks/1
curl http://localhost:8080/api/tasks/999   # -> 404 con cuerpo JSON, sin traza de excepción
```

### Parte D — Control de versiones con Git

Se inicializó el repositorio y se añadieron `.gitignore` (`target/`, `*.class`, `.idea/`, `.env`) y `.gitattributes` (`* text=auto eol=lf` y `*.jar binary`). Este último normaliza los finales de línea entre Windows y Linux, y evita problemas con los scripts `.sh` dentro de contenedores. Todo entró en el commit inicial ([1bb3b87](https://github.com/OlgaTYP/Practicas-continuas/commit/1bb3b87)). El `.editorconfig` se subió en un commit separado ([943de0d](https://github.com/OlgaTYP/Practicas-continuas/commit/943de0d)).

Después se creó la rama `feat/descripcion-feat` con `git switch -c`, se hizo commit en ella ([665dc1a](https://github.com/OlgaTYP/Practicas-continuas/commit/665dc1a)) y se fusionó en `main` con `git merge`. Como `main` no había avanzado, la fusión fue un _fast-forward_. El resultado se revisó con `git log --oneline --graph --all`.

### Parte E — Formateo automático en cada commit

Se añadió Spotless al `pom.xml` ([feae1a1](https://github.com/OlgaTYP/Practicas-continuas/commit/feae1a1)) y se probaron sus dos modos: `mvn spotless:check`, que falla si hay código sin formatear, y `mvn spotless:apply`, que lo corrige. Con este último se reformateó todo el código Java al estilo de Google Java Format.

Después se automatizó con un hook de pre-commit versionado en `.githooks/` ([4b2d9fb](https://github.com/OlgaTYP/Practicas-continuas/commit/4b2d9fb)):

```sh
#!/bin/sh
echo "Formatting with Spotless..."
mvn -q spotless:apply || exit 1
git add -u
```

Para activarlo se ejecutó `git config core.hooksPath .githooks`. Los hooks de `.git/hooks/` no se versionan; guardándolo en `.githooks/`, el hook viaja con el repositorio, aunque cada persona que clone debe ejecutar ese comando una vez (está documentado en el README y en `CONTRIBUTING.md`). El hook se probó desordenando la indentación de una clase ([d96108b](https://github.com/OlgaTYP/Practicas-continuas/commit/d96108b)): el código quedó formateado en el commit. Hay que recordar que un hook local no sustituye a la comprobación en CI, porque se puede saltar con `--no-verify`. Por eso, en el Boletín 4, `spotless:check` pasa a ser una barrera del pipeline.

### Parte F — Provocar y resolver un conflicto

Desde el mismo commit de `main` (`d96108b`) se crearon dos ramas, `feature/conflict-a` y `feature/conflict-b`, que cambiaban de forma distinta la misma línea del README (el título). La primera se fusionó en `main` sin problemas. Al fusionar la segunda, Git detectó el conflicto. Se resolvió a mano: se eliminaron los marcadores, se dejó `# Todo API - Rama A y B (resuelto)` como versión final y se cerró el merge con `git add` y `git commit` ([e00759c](https://github.com/OlgaTYP/Practicas-continuas/commit/e00759c)).

## 4. Capturas de pantalla y evidencias visuales

### 4.1 Historial del repositorio y ramas

Salida de `git log --oneline --graph` al terminar el boletín:

```text
* 9226c71 docs(project): añade documentación final de la práctica
*   e00759c merge: resuelve conflicto en README
|\
| * a635cf6 (feature/conflict-b) feat(readme): cambia titulo en rama B
* | 34e4555 (feature/conflict-a) feat(readme): cambia titulo en rama A
|/
* d96108b test(hook): autoformatea con Spotless
* 4b2d9fb chore(hooks): añade hook de pre-commit con Spotless
* feae1a1 chore(build): añade Spotless para formateo automático
* 665dc1a (feat/descripcion-feat) docs(api): añade endpoints y ejemplos JSON
* 943de0d chore(editor): añade .editorconfig
* 1bb3b87 chore: proyecto Maven inicial de la API de tareas
```

En el grafo se ve la bifurcación de las dos ramas del conflicto y el commit de merge `e00759c` que las une. La rama `feat/descripcion-feat` aparece integrada en la línea principal porque se fusionó por _fast-forward_.

### 4.2 Conflicto de merge

```text
$ git merge feature/conflict-b
Auto-merging README.md
CONFLICT (content): Merge conflict in README.md
Automatic merge failed; fix conflicts and then commit the result.
```

Contenido del README durante el conflicto y versión final:

```text
<<<<<<< HEAD
# Todo API - Rama A
=======
# Todo API - Rama B
>>>>>>> feature/conflict-b
```

```text
# Todo API - Rama A y B (resuelto)
```

### 4.3 Validación del hook de pre-commit

```text
$ git commit -m "test(hook): autoformatea con Spotless"
Formatting with Spotless...
[main d96108b] test(hook): autoformatea con Spotless
```

### 4.4 Formateo con Spotless

```text
[INFO] --- spotless:2.43.0:check (default-cli) @ todo-api ---
[INFO] Spotless.Java is keeping 15 files clean - 0 needs changes to be clean
[INFO] BUILD SUCCESS
```

## 5. Revisión de coherencia con el enunciado

Al revisar el repositorio frente al enunciado se encontraron cuatro diferencias. Las dos que no afectan al resto del trabajo se han corregido; las otras dos se documentan aquí con la solución que les correspondería.

### 5.1 Corregido

| Punto                       | Problema                                                                                                                                                                                 | Corrección                                                                                                                                      |
| --------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| `.editorconfig`             | Tenía `indent_size = 4` en la sección general. El enunciado pide 2, y además Google Java Format (Spotless) indenta con 2 espacios, así que el editor y Spotless no coincidían.           | Se cambió a `indent_size = 2`. No reformatea ningún fichero existente; solo afecta a cómo indenta el editor a partir de ahora.                  |
| Hook `.githooks/pre-commit` | Estaba guardado en Git como no ejecutable (`100644`) porque en Windows `chmod +x` no se registra. Al clonar en Linux o macOS (o en el devcontainer del Boletín 3), Git no lo ejecutaría. | Se marcó como ejecutable en el índice con `git update-index --chmod=+x .githooks/pre-commit` (ahora `100755`). El contenido del hook no cambia. |

### 5.2 Pendiente (no se corrige para no afectar al trabajo posterior)

| Punto                | Problema                                                                                                                                                                                      | Lo que debería ser                                                                                                                                                                                                                                                                                                      | Por qué no se cambia                                                                                                                                                                                         |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `TaskControllerTest` | Usa `@WebMvcTest`, que levanta un contexto (parcial) de Spring. El enunciado pide **solo tests unitarios**, sin levantar el contexto.                                                         | Un test unitario del controlador sin contexto: instanciar `TaskController` con un `TaskService` simulado (`@ExtendWith(MockitoExtension.class)` + `@Mock`) y usar `MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler())`, o llamar directamente a los métodos del controlador. | Este test se amplió en boletines posteriores (filtros por estado y prioridad) y lo ejecuta el pipeline de CI del Boletín 4. Reescribirlo cambiaría tests de los que dependen esos boletines.                 |
| Rama de la Parte D   | El commit de `feat/descripcion-feat` es `docs(api): ...` y solo cambia una línea del README. El enunciado pide un cambio pequeño pero **no trivial en el código**, con un commit `feat(...)`. | Un commit del tipo `feat(task): añade campo prioridad` (o similar) en la rama, con un cambio real en el código, y después `git merge` en `main`.                                                                                                                                                                        | El historial ya está publicado en `origin/main` y sobre él se han construido los demás boletines. Reescribirlo obligaría a hacer _force push_ y rompería las referencias a commits de toda la documentación. |

## 6. Conclusión

En el Boletín 1 se han practicado los fundamentos del trabajo con un proyecto Java y Git: preparar el entorno, generar con IA una API REST no trivial, empaquetarla como JAR ejecutable y probarla en local, versionarla con ramas y merges, automatizar el formato con Spotless y un hook de pre-commit, y resolver un conflicto de merge a mano.

La IA (GitHub Copilot) aceleró la generación del proyecto y de la documentación. Todo el código se revisó para comprobar que cumplía los requisitos de la práctica.
