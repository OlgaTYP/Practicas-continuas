# Boletín 1: Git y proyecto Maven

> Estudiante: Roxana Bareiro ([@rmbgeuskadi](https://github.com/rmbgeuskadi)).

## Resumen de commits

| # | Mensaje del commit | Qué se hizo | Autor | Enlace |
|---:|---|---|---|---|
| 1 | `chore: proyecto Maven inicial de la API de tareas` | Creación del proyecto Maven/Spring Boot para la API de tareas y configuración inicial del repositorio. | Roxana Bareiro | `e5653af` |
| 2 | `chore: añade configuración de editor` | Incorporación de `.editorconfig` para unificar reglas de edición. | Roxana Bareiro | `e834a11` |
| 3 | `feat(task): impide reabrir tareas completadas` | Implementación de la regla que impide reabrir una tarea completada y adición de un test unitario. | Roxana Bareiro | `edd1add` |
| 4 | `chore(build): añade Spotless para formateo automático` | Configuración de Spotless y del hook `pre-commit`. | Roxana Bareiro | `5eb0fb5` |
| 5 | `test(hooks): comprueba formateo automático` | Comprobación del hook mediante una modificación deliberada del formato. | Roxana Bareiro | `b0f04db` |
| 6 | `test(conflict): modifica mensaje en rama A` | Primera modificación de una línea de `ApiError.java` para preparar el conflicto. | Roxana Bareiro | `ec683b5` |
| 7 | `test(conflict): modifica mensaje en rama B` | Segunda modificación diferente de la misma línea desde otra rama. | Roxana Bareiro | `60597c1` |
| 8 | `merge: resuelve conflicto entre ramas message-a y message-b` | Resolución manual del conflicto y finalización de la fusión en `main`. | Roxana Bareiro | `3a565b3` |

Los enlaces se indican mediante los hashes cortos de los commits locales.

## Detalle por commit

### 1. `chore: proyecto Maven inicial de la API de tareas`

- **Qué se hizo:** Se creó la estructura inicial del proyecto Maven para la API REST de tareas y se preparó el repositorio Git local, incluyendo archivos de configuración como `.gitignore` y `.gitattributes`.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Creación de la aplicación solicitada en la Parte B y preparación del proyecto que después se construye y prueba en la Parte C, además de la configuración inicial de Git.
- **Uso de IA:** Se utilizó **GitHub Copilot Agent en VS Code**, mediante `/create-agent`. El prompt solicitaba crear desde cero una API REST de gestión de tareas con Java, Spring Boot y Maven, persistencia exclusivamente en memoria, arquitectura por capas, operaciones CRUD, validaciones, reglas de negocio, manejo centralizado de errores, tests unitarios y un JAR ejecutable. También pedía comprobar el proyecto y corregir los errores detectados. El prompt completo se conserva como referencia.

### 2. `chore: añade configuración de editor`

- **Qué se hizo:** Se añadió `.editorconfig` para establecer reglas comunes de formato y edición, como la codificación UTF-8, los saltos de línea, la indentación y el salto de línea final.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Configuración del repositorio dentro de la Parte D.
- **Uso de IA:** No se utilizó GitHub Copilot para realizar este cambio.

### 3. `feat(task): impide reabrir tareas completadas`

- **Qué se hizo:** Se implementó en la capa de servicio una regla para impedir que una tarea completada (`completed=true`) vuelva a marcarse como pendiente (`completed=false`). Se añadió un test unitario específico para comprobar el comportamiento.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Cambio funcional realizado sobre la API, relacionado con las reglas de negocio y las pruebas unitarias de la Parte B.
- **Uso de IA:** Se utilizó **GitHub Copilot Agent en VS Code** con un prompt específico que solicitaba analizar primero el proyecto existente, implementar la regla con cambios mínimos, utilizar el mecanismo de excepciones existente, añadir un test unitario y ejecutar las pruebas, sin introducir pruebas de integración ni modificar la configuración del proyecto.

### 4. `chore(build): añade Spotless para formateo automático`

- **Qué se hizo:** Se añadió la configuración del plugin `spotless-maven-plugin` en `pom.xml` y se creó el hook `.githooks/pre-commit` para ejecutar Spotless antes de cada commit. También se configuró Git para utilizar `.githooks` como directorio de hooks.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Parte E, dedicada al formateo automático del código y a los hooks de Git.
- **Uso de IA:** No se utilizó GitHub Copilot para realizar estos cambios.

**Nota:** El historial contiene un único commit de configuración, `5eb0fb5`, para Spotless y el hook. No existe un commit separado con el mensaje sugerido en el enunciado `chore(hooks): añade hook de pre-commit con Spotless`; por tanto, no se presenta como un commit realizado.

### 5. `test(hooks): comprueba formateo automático`

- **Qué se hizo:** Se modificó deliberadamente el formato de un archivo Java para comprobar que el hook ejecutaba Spotless y corregía el formato antes de completar el commit.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Comprobación práctica del hook descrita en la Parte E.
- **Uso de IA:** No se utilizaron herramientas de IA para realizar esta comprobación.

### 6. `test(conflict): modifica mensaje en rama A`

- **Qué se hizo:** Se creó la rama `conflict/message-a` y se modificó una línea de `ApiError.java`, añadiendo el comentario `// Rama A`.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Primera parte del ejercicio de creación de un conflicto de Git, descrito en la Parte F.
- **Uso de IA:** No se utilizaron herramientas de IA para realizar este cambio.

### 7. `test(conflict): modifica mensaje en rama B`

- **Qué se hizo:** Se creó la rama `conflict/message-b` desde `main`, antes de incorporar el cambio de la rama A, y se modificó la misma línea de `ApiError.java` de forma diferente, con el comentario `//Rama B`.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Preparación de la segunda modificación necesaria para provocar el conflicto de la Parte F.
- **Uso de IA:** No se utilizaron herramientas de IA para realizar este cambio.

### 8. `merge: resuelve conflicto entre ramas message-a y message-b`

- **Qué se hizo:** Se incorporó primero la rama A a `main`. Al fusionar después la rama B, Git detectó el conflicto esperado en `ApiError.java`. Se resolvió manualmente eliminando los marcadores de conflicto y conservando la modificación de la rama B. Finalmente, se añadió el archivo resuelto y se completó la fusión.
- **Autor:** Roxana Bareiro.
- **Relación con el boletín:** Resolución del conflicto de Git descrita en la Parte F.
- **Uso de IA:** No se utilizaron herramientas de IA para resolver el conflicto.

## Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Instalación y verificación del entorno

Se preparó el entorno de trabajo para desarrollar la aplicación utilizando Git, Java y Maven. Durante el desarrollo se utilizó Java 21 y Maven para compilar y empaquetar el proyecto.

Los comandos previstos en el enunciado para comprobar las herramientas instaladas son:

```bash
git --version
java -version
mvn -version
```

La identidad de la autora aparece reflejada en los commits del repositorio. Sin embargo, el historial de Git no conserva las salidas originales de los comandos de verificación ni permite confirmar por sí solo todos los valores de la configuración global de Git.

### Parte B — Generar la aplicación con ayuda de IA

Se creó una API REST de gestión de tareas utilizando Java, Spring Boot y Maven, con persistencia exclusivamente en memoria. La aplicación se organizó por capas, separando las responsabilidades entre `controller`, `service` y `repository`.

Se implementaron el dominio `Task`, las operaciones CRUD, las validaciones, las reglas de negocio, el manejo centralizado de errores y los tests unitarios.

La creación inicial del proyecto quedó recogida en el commit `e5653af` (`chore: proyecto Maven inicial de la API de tareas`). Posteriormente, el commit `edd1add` (`feat(task): impide reabrir tareas completadas`) incorporó una regla de negocio adicional para impedir que una tarea completada vuelva a marcarse como pendiente.

Para la generación inicial se utilizó **GitHub Copilot Agent en VS Code**, mediante `/create-agent`. El prompt solicitaba crear la aplicación desde cero, implementar los requisitos indicados y ejecutar comprobaciones para detectar y corregir posibles errores.

También se solicitó a Copilot la creación de un `README.md` con instrucciones de uso y ejemplos de peticiones HTTP para Postman. No se ha identificado en el historial aportado un commit específico asociado a esa petición.

### Parte C — Construir y probar en local

Se ejecutó `mvn clean package` y la construcción finalizó correctamente, generándose el JAR ejecutable `target/todo-api-1.0.0.jar`. También se inició la aplicación mediante el comando:

```bash
java -jar target/todo-api-1.0.0.jar
```

Durante las pruebas manuales de la API se comprobó el comportamiento de varios endpoints:

- `GET /tasks`: respondió con HTTP 200 y una lista vacía al inicio.
- `POST /tasks`: respondió con HTTP 201 al crear una tarea.
- `GET /tasks/1`: respondió con HTTP 200 al recuperar la tarea creada.
- `PUT /tasks/1`: devolvió HTTP 500. Esta incidencia quedó pendiente de investigación y no se considera resuelta en este boletín.

Además, se ejecutaron las pruebas unitarias mediante `mvn test`. El resultado fue satisfactorio:

- **Tests ejecutados:** 10.
- **Fallos:** 0.
- **Errores:** 0.
- **Pruebas omitidas:** 0.
- **Resultado de Maven:** `BUILD SUCCESS`.

Los tests comprueban comportamientos como la creación de tareas válidas, el rechazo de títulos vacíos o demasiado cortos, las fechas límite pasadas, las prioridades no válidas, las operaciones sobre tareas inexistentes y la regla que impide reabrir tareas completadas.

Estos resultados corresponden a la ejecución realizada el 25 de octubre de 2026.

### Parte D — Control de versiones con Git

Se inicializó el repositorio Git y se añadieron los archivos de configuración `.gitignore` y `.gitattributes`, incluidos en el commit `e5653af`.

Posteriormente, se incorporó el archivo `.editorconfig` para establecer unas reglas comunes de edición y formato. Este cambio quedó registrado por separado en el commit `e834a11` (`chore: añade configuración de editor`).

También se creó la rama `feat/prevent-reopening-completed-tasks` para implementar la regla de negocio que impide reabrir tareas completadas. El cambio quedó registrado en el commit `edd1add`.

Para consultar el historial y las ramas del repositorio se utilizaron los siguientes comandos:

```bash
git log --all --graph --decorate --date=short --pretty=format:"%h | %ad | %an <%ae> | %d | %s"
git branch -avv
git status --short --branch
git log --all --stat --oneline
```

El historial consultado contiene ocho commits y cuatro ramas locales: `main`, `conflict/message-a`, `conflict/message-b` y `feat/prevent-reopening-completed-tasks`.

### Parte E — Formateo automático en cada commit

Se incorporó **Spotless** al proyecto mediante el plugin de Maven `spotless-maven-plugin`, configurándolo para aplicar unas reglas comunes de formato al código Java.

Primero se ejecutó `mvn spotless:check` para comprobar el estado del formato. Este comando detectó archivos que necesitaban correcciones. A continuación, se ejecutó `mvn spotless:apply` para aplicar el formato automáticamente y se volvió a ejecutar `mvn spotless:check` para comprobar el resultado.

Además, se creó un hook de `pre-commit` en `.githooks/pre-commit` para ejecutar Spotless automáticamente antes de cada commit. Se configuró Git mediante `core.hooksPath` para utilizar este directorio de hooks.

El funcionamiento del hook se comprobó introduciendo deliberadamente una modificación incorrecta de formato en un archivo Java y realizando un commit. El hook ejecutó Spotless y corrigió el formato antes de completar el commit.

La configuración quedó registrada en el commit `5eb0fb5` (`chore(build): añade Spotless para formateo automático`) y la comprobación práctica del hook en el commit `b0f04db` (`test(hooks): comprueba formateo automático`).

Aunque el enunciado proponía realizar dos commits separados para el plugin y el hook, el historial real contiene un único commit de configuración para ambos. No se ha añadido al documento ningún commit inexistente.

### Parte F — Provocar y resolver un conflicto

Se crearon dos ramas a partir de `main` para provocar un conflicto de Git mediante modificaciones diferentes sobre la misma línea del fichero `ApiError.java`.

En la rama `conflict/message-a` se modificó la línea para añadir el comentario `// Rama A`. El cambio quedó registrado en el commit `ec683b5` (`test(conflict): modifica mensaje en rama A`).

Posteriormente, en la rama `conflict/message-b`, creada a partir de `main` antes de incorporar la rama A, se modificó esa misma línea de forma diferente, añadiendo el comentario `//Rama B`. El cambio se registró en el commit `60597c1` (`test(conflict): modifica mensaje en rama B`).

Después se incorporó primero la rama A a `main`. Al intentar fusionar la rama B, Git detectó el conflicto esperado porque ambas ramas habían modificado la misma línea.

El conflicto se resolvió manualmente eliminando los marcadores `<<<<<<<`, `=======` y `>>>>>>>` y conservando la modificación de la rama B. A continuación, se añadió el archivo resuelto al área de preparación y se completó la fusión mediante el commit `3a565b3` (`merge: resuelve conflicto entre ramas message-a y message-b`).

## Uso de inteligencia artificial

Durante la realización del boletín se utilizaron herramientas de inteligencia artificial como apoyo en el desarrollo del proyecto y en la documentación.

Para la creación inicial de la API se utilizó **GitHub Copilot Agent en VS Code**, mediante un prompt que solicitaba generar el proyecto desde cero, implementar el CRUD, las capas de la aplicación, las validaciones, las reglas de negocio, los tests unitarios y el empaquetado del JAR ejecutable. También se solicitó comprobar el funcionamiento del proyecto y corregir los errores detectados.

Asimismo, se utilizó GitHub Copilot Agent para implementar la regla de negocio que impide reabrir tareas completadas, añadiendo un test unitario específico.

Por otra parte, se utilizó **ChatGPT** como apoyo para estructurar, redactar y revisar este documento `boletin1.md`, relacionando los trabajos realizados con los commits correspondientes y con los apartados del enunciado.

El uso de estas herramientas se declara de forma diferenciada para dejar constancia de su participación tanto en el desarrollo como en la elaboración de la documentación.

<!-- Incluir capturas de pantalla de las comprobaciones o resultados que no queden reflejados en los commits. -->