# Boletín 4: Integración continua con GitHub Actions

## Resumen de commits

| #   | Mensaje del commit                                      | Qué se hizo                                                                                                                    | Autor          | Enlace                                                                   |
| --- | ------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ | -------------- | ------------------------------------------------------------------------ |
| 1   | `ci: Añadir pipeline inicial de integración continua`   | Creación de `.github/workflows/ci.yml` con un único job `build` que ejecuta `mvn verify`.                                      | OlgaTYP        | [4f0c34d](https://github.com/OlgaTYP/Practicas-continuas/commit/4f0c34d) |
| 2   | `test: provocar fallo para comprobar el CI`             | Se cambia una aserción de `TaskServiceTest` (2 → 3) para que el test falle.                                                    | Roxana Bareiro | [5bdd886](https://github.com/OlgaTYP/Practicas-continuas/commit/5bdd886) |
| 3   | `test: corregir error provocado para comprobar el CI`   | Se restaura la aserción correcta para que el pipeline vuelva a verde.                                                          | Roxana Bareiro | [34174e7](https://github.com/OlgaTYP/Practicas-continuas/commit/34174e7) |
| 4   | `ci: ajustar carga del agente en los tests`             | Primer intento de arreglar Mockito en el runner con `MAVEN_OPTS=-XX:+EnableDynamicAgentLoading`.                               | Roxana Bareiro | [581d7d9](https://github.com/OlgaTYP/Practicas-continuas/commit/581d7d9) |
| 5   | `ci: añado dependecia de mockito-core al pom`           | Se añade `mockito-core` explícitamente al `pom.xml`.                                                                           | Roxana Bareiro | [7f90200](https://github.com/OlgaTYP/Practicas-continuas/commit/7f90200) |
| 6   | `ci: fix  Mockito initialization in GitHub Actions`     | Se fija la versión de Mockito/Byte Buddy y se carga el agente con `argLine` en Surefire; se elimina `MAVEN_OPTS` del workflow. | OlgaTYP        | [437f52e](https://github.com/OlgaTYP/Practicas-continuas/commit/437f52e) |
| 7   | `ci: separar pipeline en jobs de lint test y package`   | Reestructuración del `ci.yml` en tres jobs encadenados con `needs` e informe de tests y subida del JAR.                        | Roxana Bareiro | [23040ec](https://github.com/OlgaTYP/Practicas-continuas/commit/23040ec) |
| 8   | `chore: ignorar configuración local de VS Code`         | Se añade `.vscode` al `.gitignore`.                                                                                            | Roxana Bareiro | [728f2e7](https://github.com/OlgaTYP/Practicas-continuas/commit/728f2e7) |
| 9   | `test: comprobar que lint bloquea el pipeline`          | Primer intento de desordenar `TaskController` (commit con `--no-verify`).                                                      | OlgaTYP        | [808a9e4](https://github.com/OlgaTYP/Practicas-continuas/commit/808a9e4) |
| 10  | `Revert "test: comprobar que lint bloquea el pipeline"` | Se revierte el intento anterior para repetir la prueba.                                                                        | OlgaTYP        | [4a1e555](https://github.com/OlgaTYP/Practicas-continuas/commit/4a1e555) |
| 11  | `test: comprobar que lint bloquea el pipeline`          | Se desordena `TaskController` sin pasar el hook y se activa el CI también en la rama `feat/ci-pipeline-jobs`.                  | OlgaTYP        | [7a0660c](https://github.com/OlgaTYP/Practicas-continuas/commit/7a0660c) |
| 12  | `style: corregir formato de TaskController`             | Se vuelve a dar formato correcto a `TaskController` para que el pipeline pase.                                                 | OlgaTYP        | [1c3c7da](https://github.com/OlgaTYP/Practicas-continuas/commit/1c3c7da) |
| 13  | `ci: restaurar activación del pipeline`                 | El `push` vuelve a dispararse solo en `main`.                                                                                  | Roxana Bareiro | [e50f371](https://github.com/OlgaTYP/Practicas-continuas/commit/e50f371) |
| 14  | `test: comprobar bloqueo de fusion con test fallido`    | Se añade un test que falla siempre (`assertTrue(false)`) para comprobar que GitHub impide el merge.                            | OlgaTYP        | [db19ff1](https://github.com/OlgaTYP/Practicas-continuas/commit/db19ff1) |
| 15  | `test: solucionado error intencional`                   | Se corrige el test para que pase y se pueda fusionar el PR.                                                                    | Roxana Bareiro | [1ffb898](https://github.com/OlgaTYP/Practicas-continuas/commit/1ffb898) |
| 16  | `ci: añadir resumen de PR con Ollama`                   | Nuevo workflow `main.yml` que resume cada PR con un modelo de Ollama Cloud y lo publica como comentario.                       | OlgaTYP        | [8685f52](https://github.com/OlgaTYP/Practicas-continuas/commit/8685f52) |
| 17  | `ci: correccion permisos workflow`                      | Se cambia `pull-requests: read` por `pull-requests: write`.                                                                    | Roxana Bareiro | [5f5f39f](https://github.com/OlgaTYP/Practicas-continuas/commit/5f5f39f) |
| 18  | `ci: Actualiza OLLAMA_API_KEY to OLLAMA_PC_API_KEY`     | Se apunta el workflow al secreto `OLLAMA_PC_API_KEY`.                                                                          | OlgaTYP        | [424bb3c](https://github.com/OlgaTYP/Practicas-continuas/commit/424bb3c) |
| 19  | `fix: corregir configuración de revisión por IA`        | Misma corrección del nombre del secreto en la rama `fix/ollama-ai-review`.                                                     | Roxana Bareiro | [688c365](https://github.com/OlgaTYP/Practicas-continuas/commit/688c365) |
| 20  | `docs: añadir badge del estado del CI`                  | Se añade al `README.md` el badge de estado del workflow `ci.yml`.                                                              | Roxana Bareiro | [40d43ea](https://github.com/OlgaTYP/Practicas-continuas/commit/40d43ea) |

## Detalle por commit

### 1. `ci: Añadir pipeline inicial de integración continua`

- **Qué se hizo:** se creó `.github/workflows/ci.yml` con el job `build`: checkout, JDK 21 Temurin con caché de Maven y `mvn --batch-mode verify`. Incluye `concurrency`, `permissions: contents: read` y `timeout-minutes: 15`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte A.
- **Uso de IA:** no se ha usado.

### 2. `test: provocar fallo para comprobar el CI`

- **Qué se hizo:** en `TaskServiceTest` se cambió `assertEquals(2, tasks.size())` por `assertEquals(3, tasks.size())` para que el test fallase a propósito.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte B (el PR #12 se pone en rojo).
- **Uso de IA:** no se ha usado.

### 3. `test: corregir error provocado para comprobar el CI`

- **Qué se hizo:** se restauró la aserción original y se hizo push a la misma rama.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte B (el check vuelve a verde).
- **Uso de IA:** no se ha usado.

### 4. `ci: ajustar carga del agente en los tests`

- **Qué se hizo:** en el runner los tests con Mockito fallaban al cargar dinámicamente el agente de Byte Buddy en JDK 21. Se probó a añadir `MAVEN_OPTS: -XX:+EnableDynamicAgentLoading` al paso de Maven.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte A/B (hacer que el pipeline funcione en GitHub Actions).
- **Uso de IA:** no se ha usado.

### 5. `ci: añado dependecia de mockito-core al pom`

- **Qué se hizo:** se declaró `mockito-core` en el `pom.xml` para controlar su versión.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte A/B.
- **Uso de IA:** no se ha usado.

### 6. `ci: fix  Mockito initialization in GitHub Actions`

- **Qué se hizo:** se fijaron `mockito.version` (5.11.0) y `byte-buddy.version` (1.14.19) y se configuró `maven-surefire-plugin` con `-javaagent:` apuntando al `byte-buddy-agent`. Así el agente se carga al arrancar la JVM y no hace falta `MAVEN_OPTS` en el workflow.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte A/B (solución definitiva del fallo de Mockito en CI).
- **Uso de IA:** GitHub Copilot (modo agente), pidiéndole que arreglase la inicialización de Mockito en GitHub Actions a partir del log del job fallido.

### 7. `ci: separar pipeline en jobs de lint test y package`

- **Qué se hizo:** el `ci.yml` pasó a tener tres jobs: `lint` (`mvn -B spotless:check`), `test` (`needs: lint`, `mvn -B verify` y publicación del informe JUnit con `dorny/test-reporter@v3` bajo `if: always()`) y `package` (`needs: test`, `mvn -B package -DskipTests` y subida del JAR con `actions/upload-artifact@v4`). Se ampliaron los permisos con `actions: read` y `checks: write`.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte C.
- **Uso de IA:** no se ha usado.

### 8. `chore: ignorar configuración local de VS Code`

- **Qué se hizo:** se añadió `.vscode` al `.gitignore` para no subir la configuración local del editor.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** tarea auxiliar durante la Parte C.
- **Uso de IA:** no se ha usado.

### 9. `test: comprobar que lint bloquea el pipeline`

- **Qué se hizo:** primer intento de desordenar la indentación de `TaskController` haciendo el commit con `git commit --no-verify`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte C (provocar fallo de formato).
- **Uso de IA:** no se ha usado.

### 10. `Revert "test: comprobar que lint bloquea el pipeline"`

- **Qué se hizo:** se revirtió el commit anterior para repetir la prueba, esta vez con el pipeline activado también en la rama de trabajo.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte C.
- **Uso de IA:** no se ha usado.

### 11. `test: comprobar que lint bloquea el pipeline`

- **Qué se hizo:** se volvió a desordenar `TaskController` saltando el hook con `--no-verify` y, de forma temporal, se añadió `feat/ci-pipeline-jobs` a las ramas de `push` del `ci.yml`. El job `lint` falló y `test` y `package` quedaron sin ejecutarse.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte C.
- **Uso de IA:** no se ha usado.

### 12. `style: corregir formato de TaskController`

- **Qué se hizo:** se volvió a dar formato correcto a `TaskController` y se hizo push. El pipeline completo pasó a verde y generó el informe de tests y el artefacto `app-jar`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte C (corregir el formato y volver a ejecutar el pipeline).
- **Uso de IA:** no se ha usado.

### 13. `ci: restaurar activación del pipeline`

- **Qué se hizo:** se quitó `feat/ci-pipeline-jobs` del disparador `push`, que vuelve a ser solo `main` como pide el enunciado.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte C (deja el `ci.yml` como en el enunciado).
- **Uso de IA:** no se ha usado.

### 14. `test: comprobar bloqueo de fusion con test fallido`

- **Qué se hizo:** se añadió a `TaskServiceTest` un test `testQueFallaIntencionadamente` con `assertTrue(false)` y se abrió el PR #16 contra `main`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte D (comprobar que GitHub impide fusionar con un check en rojo).
- **Uso de IA:** no se ha usado.

### 15. `test: solucionado error intencional`

- **Qué se hizo:** el test se renombró a `testQueNoFallaIntencionadamente` con `assertTrue(true)`. Los checks pasaron a verde y el PR se pudo fusionar.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte D.
- **Uso de IA:** no se ha usado.

### 16. `ci: añadir resumen de PR con Ollama`

- **Qué se hizo:** se creó `.github/workflows/main.yml` (“AI Pull Request Review”). Se dispara en `pull_request` (`opened`, `synchronize`, `reopened`) y usa `actions/github-script@v7`: obtiene los ficheros cambiados y su diff, los envía a `https://ollama.com/api/chat` con el modelo `gemma4:31b` y publica un comentario en el PR con el resumen en español. La API key se lee de un secreto del repositorio y, si algo falla, se publica el comentario “La IA ha fallado”.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte E (gestión de secretos y revisión por IA).
- **Uso de IA:** se ha usado ChatGPT para que muestre las instrucciones de como configurar Ollama.El prompt que se utilizó fue: Proporciona una guía técnica, estructurada y detallada para configurar Ollama Cloud e integrarlo con GitHub Actions. Incluye la obtención de la API key, su almacenamiento seguro mediante GitHub Actions Secrets, la configuración del acceso a la API y la verificación de su correcto funcionamiento, siguiendo las mejores prácticas de seguridad.

### 17. `ci: correccion permisos workflow`

- **Qué se hizo:** se cambió `pull-requests: read` por `pull-requests: write` para que el workflow pudiera comentar en el PR.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte E.
- **Uso de IA:** no se ha usado.

### 18. `ci: Actualiza OLLAMA_API_KEY to OLLAMA_PC_API_KEY`

- **Qué se hizo:** se cambió el secreto leído por el workflow a `secrets.OLLAMA_PC_API_KEY`, que es el nombre con el que se creó en _Settings → Secrets and variables → Actions_.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte E.
- **Uso de IA:** no se ha usado.

### 19. `fix: corregir configuración de revisión por IA`

- **Qué se hizo:** la misma corrección del nombre del secreto, en la rama `fix/ollama-ai-review` (PR #19), para que el workflow funcionase en `main`.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte E.
- **Uso de IA:** no se ha usado.

### 20. `docs: añadir badge del estado del CI`

- **Qué se hizo:** se añadió al principio del `README.md` el badge `actions/workflows/ci.yml/badge.svg?branch=main`, enlazado a la página del workflow.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte E (badge del estado del CI).
- **Uso de IA:** no se ha usado.

## Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A

Se creó el primer pipeline en `.github/workflows/ci.yml` (commit 1). Tiene un job `build` que se ejecuta en `ubuntu-latest`, descarga el código, instala JDK 21 con caché de Maven y ejecuta `mvn --batch-mode verify`, que compila, pasa los tests y empaqueta.

Al ejecutarse en GitHub Actions los tests con Mockito fallaban al cargar el agente de Byte Buddy en JDK 21. Primero se probó con `MAVEN_OPTS` (commit 4) y declarando `mockito-core` (commit 5). La solución definitiva fue cargar el agente con `-javaagent` desde la configuración de Surefire en el `pom.xml` (commit 6). Así funciona igual en local y en CI sin tocar el workflow.

**¿Qué sentido tiene el campo `concurrency`?**
`group: ci-${{ github.ref }}` agrupa las ejecuciones por rama o PR, y `cancel-in-progress: true` cancela la ejecución en curso de ese grupo cuando llega un push nuevo a la misma rama. Solo importa el resultado del último commit, así que no tiene sentido terminar ejecuciones de commits que ya están obsoletos. Así se ahorran minutos de runner, el resultado llega antes y no hay varias ejecuciones de la misma rama compitiendo.

**¿Cuándo se dispara el workflow?**

- En cada `push` a la rama `main`, por ejemplo al fusionar un PR.
- En cada `pull_request`, sea cual sea la rama destino, con los tipos por defecto: al abrirlo (`opened`), al hacer push a la rama del PR (`synchronize`) y al reabrirlo (`reopened`).

Un push a otra rama que no tenga PR abierto no lanza el workflow. Por eso en la Parte C se añadió temporalmente la rama `feat/ci-pipeline-jobs` al disparador (commit 11) y después se quitó (commit 13).

### Parte B

En la rama `feat/ci-pipeline-assertion` se rompió a propósito una aserción de `TaskServiceTest`, esperando 3 tareas en vez de 2 (commit 2). Se abrió el PR #12 y el check `build` se puso en **rojo**: `mvn verify` falló en la fase de tests y el PR mostraba el check fallido.

Después se restauró la aserción y se hizo push a la misma rama (commit 3). El workflow se volvió a lanzar por el evento `synchronize`, el check pasó a **verde** y el PR #12 se fusionó. Así se ve que el CI detecta un test roto sin que nadie tenga que ejecutarlo en local.

### Parte C

Se dividió el pipeline en tres jobs encadenados con `needs` (commit 7):

1. **`lint`**: `mvn -B spotless:check`, que comprueba el formato con el Spotless del Boletín 1.
2. **`test`** (`needs: lint`): `mvn -B verify` y publicación del informe JUnit como check con `dorny/test-reporter@v3`.
3. **`package`** (`needs: test`): `mvn -B package -DskipTests` y subida del JAR como artefacto `app-jar` durante 7 días.

Se añadieron los permisos `actions: read` y `checks: write`, que necesita `dorny/test-reporter` para leer los runs y publicar el informe como check del PR.

Para probar el `lint` se desordenó la indentación de `TaskController` y se hizo el commit con `git commit --no-verify` para saltar el hook de pre-commit. El primer intento (commit 9) se revirtió (commit 10) y se repitió (commit 11). El job **`lint` falló** y **`test` y `package` aparecieron como omitidos (_skipped_)**: no llegaron a ejecutarse.

Después se corrigió el formato (commit 12), se hizo push y el pipeline pasó entero a verde (PR #13).

<!-- Capturas: sustituir por las imágenes reales -->

![Job lint fallido y test/package omitidos](img/boletin4/lint-fallido.png)
![Informe de tests "Resultados JUnit"](img/boletin4/informe-tests.png)
![Artefacto app-jar generado](img/boletin4/app-jar.png)

**¿Por qué es útil que `lint` vaya primero?**
Es el job más rápido y barato: no compila ni ejecuta tests. Si el formato es incorrecto el PR no se va a poder fusionar de todas formas, así que no tiene sentido gastar minutos en tests y empaquetado. Además el error queda claro: si falla `lint` el problema es de formato y no de lógica. Es el principio de _fail fast_.

**¿Por qué el paso del informe lleva `if: always()`?**
Por defecto un paso solo se ejecuta si los anteriores han ido bien (`success()`). Si `mvn verify` falla, el informe no se publicaría, y es precisamente cuando más falta hace saber qué test ha fallado y por qué. Con `if: always()` el informe se publica pase lo que pase en los tests, y el PR muestra los resultados JUnit con los tests fallidos sin tener que buscar en el log.

**¿Por qué `package` vuelve a construir el proyecto si `test` ya ejecutó `mvn verify`? ¿Cómo podrían compartirse los archivos?**
Cada job se ejecuta en una máquina virtual nueva y limpia. No comparten sistema de ficheros, así que el `target/` que generó `test` no existe en la máquina de `package`, que tiene que volver a hacer checkout y compilar (con `-DskipTests`, porque los tests ya se pasaron).

Para no repetir la compilación, el job `test` podría subir el JAR o la carpeta `target/` con `actions/upload-artifact` y el job `package` descargarlo con `actions/download-artifact` usando el mismo `name`. Otra opción es `actions/cache`, aunque la caché está pensada para dependencias y no para resultados de build. La caché de Maven (`cache: maven`) ya evita volver a descargar las dependencias en cada job.

### Parte D

En _Settings → Branches_ se editó la regla de protección de `main` del Boletín 2:

- Se activó **“Require status checks to pass before merging”** con los checks `lint`, `test` y `package`.
- Se marcó **“Require branches to be up to date before merging”**: si `main` ha avanzado, hay que actualizar la rama del PR antes de fusionar, para que los checks se hayan ejecutado sobre el código que de verdad va a quedar en `main`.
- Se activó **“Do not allow bypassing the above settings”**, para que tampoco los administradores puedan saltarse la regla.

Para comprobarlo se abrió el PR #16 con un test que siempre falla (commit 14). El job `test` se puso en rojo, `package` no se ejecutó y GitHub mostró el botón de merge deshabilitado con el aviso de que faltaban checks obligatorios. Al corregir el test (commit 15) los tres checks pasaron a verde y el PR se pudo fusionar.

<!-- Captura: sustituir por la imagen real -->

![Merge bloqueado por checks obligatorios](img/boletin4/merge-bloqueado.png)

### Parte E

**Revisión de PR con IA y gestión de secretos.** Se creó el workflow `.github/workflows/main.yml` (commit 16). Se dispara en cada `pull_request` (`opened`, `synchronize`, `reopened`) y con `actions/github-script@v7`:

1. Obtiene la lista de ficheros cambiados y su diff con la API de GitHub, limitado a 20 000 caracteres.
2. Lo envía a la API de Ollama Cloud (`https://ollama.com/api/chat`) con el modelo `gemma4:31b` y un prompt de sistema que pide un resumen en español y que trate el diff como datos y no como instrucciones.
3. Publica la respuesta como comentario del PR con el título “Resumen de cambios generado por IA”.
4. Si falla cualquier paso (no hay API key, la API devuelve un error HTTP o la respuesta viene vacía), el `catch` publica el comentario **“La IA ha fallado”**.

La API key de Ollama **no está en el repositorio**. Se guardó como secreto en _Settings → Secrets and variables → Actions_ con el nombre `OLLAMA_PC_API_KEY` y el workflow la lee con `${{ secrets.OLLAMA_PC_API_KEY }}` como variable de entorno. GitHub cifra los secretos, los oculta en los logs (`***`) y no los pasa a workflows lanzados desde forks.

Hubo que hacer dos ajustes: dar permiso `pull-requests: write` para poder comentar (commit 17) y corregir el nombre del secreto, que al principio no coincidía con el creado en GitHub y hacía que se publicase “La IA ha fallado” (commits 18 y 19, PR #19).

**Badge del CI.** Se añadió al principio del `README.md` el badge del workflow `ci.yml` en la rama `main` (commit 20, PR #20). Muestra en todo momento si el último pipeline de `main` está en verde o en rojo.
