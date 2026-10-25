# Boletín 3: Docker — contenerizar la aplicación

> Pareja: Olga Yanes ([@OlgaTYP](https://github.com/OlgaTYP)) y Roxana Bareiro ([@rmbgeuskadi](https://github.com/rmbgeuskadi)).
> Repositorio: [OlgaTYP/Practicas-continuas](https://github.com/OlgaTYP/Practicas-continuas)
> Ramas de trabajo: `feat/docker` y `feat/docker-pr`, integradas en `main` mediante el [PR #11](https://github.com/OlgaTYP/Practicas-continuas/pull/11) (cierra el [Issue #10](https://github.com/OlgaTYP/Practicas-continuas/issues/10)).

## 1. Resumen de commits

| #   | Mensaje del commit                                                   | Qué se hizo                                                                                    | Parte | Autor          | Enlace                                                                   |
| --- | -------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- | ----- | -------------- | ------------------------------------------------------------------------ |
| 1   | `Añadir configuración de health actuator`                            | Exposición de `health` e `info` de Actuator en `application.properties`.                       | B     | Roxana Bareiro | [bc68569](https://github.com/OlgaTYP/Practicas-continuas/commit/bc68569) |
| 2   | `feat: Añadir dependencias de Spring Boot`                           | Dependencia `spring-boot-starter-actuator` en el `pom.xml`.                                    | B     | Olga Yanes     | [d1cd2f2](https://github.com/OlgaTYP/Practicas-continuas/commit/d1cd2f2) |
| 3   | `feat: Añadir el archivo Dockerfile`                                 | `Dockerfile` multi-stage (build con Maven + runtime con JRE, usuario no root y `HEALTHCHECK`). | C     | Olga Yanes     | [6e73561](https://github.com/OlgaTYP/Practicas-continuas/commit/6e73561) |
| 4   | `Añadir dockerignore`                                                | `.dockerignore` para excluir del contexto de build ficheros innecesarios y secretos.           | C     | Roxana Bareiro | [ae521d5](https://github.com/OlgaTYP/Practicas-continuas/commit/ae521d5) |
| 5   | `feat: Probar cache de Docker`                                       | Cambio de una línea en `TaskService` para medir la caché de capas.                             | C     | Olga Yanes     | [b559bfa](https://github.com/OlgaTYP/Practicas-continuas/commit/b559bfa) |
| 6   | `Añadir cambios para pruebas de caché Docker`                        | Cambio de una línea en `TodoApiApplication` para la misma prueba.                              | C     | Roxana Bareiro | [e28c33e](https://github.com/OlgaTYP/Practicas-continuas/commit/e28c33e) |
| 7   | `Merge remote-tracking branch 'origin/feat/docker' into feat/docker` | Integración de los dos commits anteriores, hechos en paralelo en la misma rama.                | C     | Roxana Bareiro | [7c82d74](https://github.com/OlgaTYP/Practicas-continuas/commit/7c82d74) |
| 8   | `feat: Sustituir H2 por PostgreSQL con Spring Data JPA e Hibernate`  | Persistencia con Spring Data JPA + driver de PostgreSQL; se elimina el repositorio en memoria. | D.1   | Olga Yanes     | [b780f8e](https://github.com/OlgaTYP/Practicas-continuas/commit/b780f8e) |
| 9   | `feat: Añadir configuración de Docker Compose`                       | `docker-compose.yml` con los servicios `db` (PostgreSQL 16) y `api`.                           | D.2   | Roxana Bareiro | [3bc3f8c](https://github.com/OlgaTYP/Practicas-continuas/commit/3bc3f8c) |
| 10  | `chore: Añadir plantilla de variables de entorno`                    | Plantilla `.env.example`.                                                                      | E     | Roxana Bareiro | [d1d20ca](https://github.com/OlgaTYP/Practicas-continuas/commit/d1d20ca) |
| 11  | `feat: Externalizar configuración de PostgreSQL con .env`            | Las credenciales de `docker-compose.yml` pasan a variables `${...}`.                           | E     | Roxana Bareiro | [f94549d](https://github.com/OlgaTYP/Practicas-continuas/commit/f94549d) |
| 12  | `feat: configurar entorno de desarrollo con Dev Container`           | `.devcontainer/devcontainer.json` y su `devcontainer-lock.json`.                               | F     | Olga Yanes     | [2cfcf16](https://github.com/OlgaTYP/Practicas-continuas/commit/2cfcf16) |
| 13  | `docs: documentar ejecución con Docker y Dev Container`              | Secciones del README para levantar el proyecto con Docker y con Dev Container.                 | G     | Olga Yanes     | [550e2f7](https://github.com/OlgaTYP/Practicas-continuas/commit/550e2f7) |
| —   | `Merge pull request #11 from OlgaTYP/feat/docker-pr`                 | Integración en `main` tras la aprobación de rmbgeuskadi.                                       | G     | Roxana Bareiro | [9c78761](https://github.com/OlgaTYP/Practicas-continuas/commit/9c78761) |

## 2. Detalle por commit

### 1. `Añadir configuración de health actuator`

- **Qué se hizo:** se añadieron a `application.properties` las propiedades `management.endpoints.web.exposure.include=health,info` y `management.endpoint.health.show-details=when-authorized`.
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** Parte B. Solo se exponen `health` e `info`, y el detalle de salud solo se muestra a usuarios autorizados. El endpoint `/actuator/health` es el que usa el `HEALTHCHECK` del Dockerfile.
- **Uso de IA:** no fue necesaria.

### 2. `feat: Añadir dependencias de Spring Boot`

- **Qué se hizo:** se añadió `spring-boot-starter-actuator` al `pom.xml`.
- **Autora:** Olga Yanes.
- **Relación con el boletín:** Parte B. Sin esta dependencia, las propiedades del commit anterior no tendrían efecto.
- **Uso de IA:** no fue necesaria.

### 3. `feat: Añadir el archivo Dockerfile`

- **Qué se hizo:** se creó el `Dockerfile` multi-stage del enunciado. La etapa `build` (`maven:3.9-eclipse-temurin-21`) descarga las dependencias y empaqueta el JAR. La etapa final (`eclipse-temurin:21-jre`) instala solo `curl` para el healthcheck, crea el usuario de sistema `app` (UID 1001), copia el JAR y lo ejecuta sin privilegios de root.
- **Autora:** Olga Yanes.
- **Relación con el boletín:** Parte C.
- **Uso de IA:** no fue necesaria.

### 4. `Añadir dockerignore`

- **Qué se hizo:** se añadió `.dockerignore` con `target/`, `.git/`, `.idea/`, `.vscode/`, `.devcontainer/`, `*.md`, `deploy/`, `.env` y `docker-compose*.yml`.
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** Partes C y E (paso 4). El contexto de build es más pequeño y el `.env` con credenciales nunca llega al motor de Docker ni a la imagen.
- **Uso de IA:** no fue necesaria.

### 5 y 6. `feat: Probar cache de Docker` / `Añadir cambios para pruebas de caché Docker`

- **Qué se hizo:** cada integrante cambió una línea de una clase Java (`TaskService` y `TodoApiApplication`) para reconstruir la imagen y medir el tiempo con las dos ordenaciones del Dockerfile.
- **Autoras:** Olga Yanes y Roxana Bareiro.
- **Relación con el boletín:** Parte C, demostración de la caché de capas. El Dockerfile se dejó en su orden original (`COPY pom.xml .` antes de `COPY src ./src`).
- **Uso de IA:** no se utilizó.

### 7. `Merge remote-tracking branch 'origin/feat/docker' into feat/docker`

- **Qué se hizo:** como los commits 5 y 6 se hicieron a la vez sobre la misma rama, se integró la rama remota en la local antes de continuar.
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** trabajo colaborativo en la rama `feat/docker`.
- **Uso de IA:** no fue necesaria.

### 8. `feat: Sustituir H2 por PostgreSQL con Spring Data JPA e Hibernate`

- **Qué se hizo:** se añadieron `spring-boot-starter-data-jpa` y el driver `org.postgresql:postgresql`. `Task` pasó a ser una entidad JPA, `TaskRepository` pasó a extender `JpaRepository` y se eliminó `InMemoryTaskRepository`. En `application.properties` se configuró el _datasource_ y `spring.jpa.hibernate.ddl-auto=update`.
- **Autora:** Olga Yanes.
- **Relación con el boletín:** Parte D.1.
- **Uso de IA:** Copilot, con el prompt: «Modifica ligeramente el proyecto existente para sustituir la base de datos H2 en memoria por PostgreSQL, manteniendo Spring Data JPA con Hibernate como capa de persistencia».

### 9. `feat: Añadir configuración de Docker Compose`

- **Qué se hizo:** se creó `docker-compose.yml` con el servicio `db` (`postgres:16`, volumen `db-data`, puerto solo en `127.0.0.1` y healthcheck con `pg_isready`) y el servicio `api` (construido desde el Dockerfile, que espera a que `db` esté _healthy_).
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** Parte D.2. En esta primera versión las credenciales aún estaban escritas en el YAML.
- **Uso de IA:** no se utilizó.

### 10. `chore: Añadir plantilla de variables de entorno`

- **Qué se hizo:** se añadió `.env.example` con `POSTGRES_DB`, `POSTGRES_USER` y `POSTGRES_PASSWORD=cambia_esto`.
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** Parte E, paso 1.
- **Uso de IA:** no se utilizó.

### 11. `feat: Externalizar configuración de PostgreSQL con .env`

- **Qué se hizo:** en `docker-compose.yml` se sustituyeron los valores escritos a mano por `${POSTGRES_DB}`, `${POSTGRES_USER}` y `${POSTGRES_PASSWORD:?...}`, y en el healthcheck por `$${POSTGRES_USER}` y `$${POSTGRES_DB}`.
- **Autora:** Roxana Bareiro.
- **Relación con el boletín:** Parte E, paso 5.
- **Uso de IA:** no se utilizó IA

### 12. `feat: configurar entorno de desarrollo con Dev Container`

- **Qué se hizo:** se creó `.devcontainer/devcontainer.json` (Java 21, Maven, Docker dentro del contenedor, puertos reenviados, preparación automática y extensiones de VS Code). VS Code generó además `devcontainer-lock.json`, que fija las versiones exactas de las _features_ utilizadas.
- **Autora:** Olga Yanes.
- **Relación con el boletín:** Parte F.
- **Uso de IA:** ChatGPT.

### 13. `docs: documentar ejecución con Docker y Dev Container`

- **Qué se hizo:** se añadieron al README las secciones _Levantar el proyecto con Docker_ y _Levantar el proyecto con Dev Container_.
- **Autora:** Olga Yanes.
- **Relación con el boletín:** Parte G.
- **Uso de IA:** ChatGPT.

## 3. Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Primeros pasos con Docker

Se instaló Docker Desktop en Windows y se comprobó la instalación:

```bash
docker --version
docker compose version
docker run hello-world
```

Los dos primeros comandos mostraron las versiones de Docker Engine y Docker Compose. `hello-world` descargó la imagen de prueba desde Docker Hub, creó un contenedor y mostró el mensaje de bienvenida, lo que confirma que el motor funciona y tiene acceso al registry.

### Parte B — Endpoint de salud

Se añadió Spring Boot Actuator al `pom.xml` ([d1cd2f2](https://github.com/OlgaTYP/Practicas-continuas/commit/d1cd2f2)) y se expusieron únicamente los endpoints necesarios ([bc68569](https://github.com/OlgaTYP/Practicas-continuas/commit/bc68569)):

```properties
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=when-authorized
```

Con la aplicación arrancada en local (`mvn spring-boot:run`) se consultó el endpoint de salud:

```powershell
curl.exe http://localhost:8080/actuator/health
```

```json
{ "status": "UP" }
```

### Parte C — Dockerfile multi-stage

Se añadieron el `Dockerfile` ([6e73561](https://github.com/OlgaTYP/Practicas-continuas/commit/6e73561)) y el `.dockerignore` ([ae521d5](https://github.com/OlgaTYP/Practicas-continuas/commit/ae521d5)) del enunciado. Después se construyó la imagen y se arrancó un contenedor:

```bash
docker build -t tareas-api:0.1 .
docker run --rm -p 8080:8080 tareas-api:0.1
# En otra terminal:
curl http://localhost:8080/api/tasks
docker ps        # la columna STATUS pasa de (health: starting) a (healthy)
```

En esta fase la aplicación aún guardaba los datos en memoria, así que el contenedor arrancaba sin necesidad de base de datos. `docker ps` mostró el contenedor como `(healthy)` cuando el `HEALTHCHECK` (un `curl` a `/actuator/health` cada 15 s, tras 40 s de margen inicial) obtuvo `UP`.

#### Comparación de tamaños

```bash
docker build --target build -t tareas-api:build .
docker images tareas-api
```

```text
IMAGE              ID             DISK USAGE   CONTENT SIZE
tareas-api:0.1     44251697f07b        551MB          160MB
tareas-api:build   0593d924e7b7       1.21GB          424MB
```

La imagen `tareas-api:build` ocupa más del doble que `tareas-api:0.1` (1,21 GB frente a 551 MB en disco). Con `docker history tareas-api:build` se ve que solo la capa de `mvn dependency:go-offline` añade 167 MB de dependencias, y la del `mvn package`, otros 57 MB. La diferencia sale de lo que contiene cada etapa:

- **`build`**: JDK completo (compilador y herramientas), Maven, el repositorio local `~/.m2` con todas las dependencias y plugins que descarga `dependency:go-offline`, el código fuente y la carpeta `target/` con los compilados intermedios.
- **Runtime (`0.1`)**: parte de `eclipse-temurin:21-jre`, que solo trae la máquina virtual para ejecutar, más `curl` y el JAR final. Nada de la etapa `build` llega a la imagen final, salvo lo copiado con `COPY --from=build`.

La imagen que se distribuye es más pequeña, se descarga antes y tiene menos superficie de ataque, porque no incluye compilador, Maven ni el código fuente.

#### Caché de capas

Para medir el tiempo de reconstrucción se cambió una línea de una clase Java en cada prueba ([b559bfa](https://github.com/OlgaTYP/Practicas-continuas/commit/b559bfa) y [e28c33e](https://github.com/OlgaTYP/Practicas-continuas/commit/e28c33e)):

```bash
time docker build -t tareas-api:0.1 .
```

1. **Orden original** (`COPY pom.xml .` → `RUN mvn dependency:go-offline` → `COPY src ./src`): al cambiar solo una clase, el `pom.xml` no cambia, así que Docker reutiliza desde la caché la capa de dependencias (`CACHED`). Solo repite `COPY src` y el `mvn package`, y la reconstrucción es rápida.

   ```text
   #9 [build 3/6] COPY pom.xml .
   #9 CACHED
   #10 [build 4/6] RUN mvn -B dependency:go-offline
   #10 CACHED
   #11 [build 5/6] COPY src ./src
   #12 [build 6/6] RUN mvn -B clean package -DskipTests

   real    0m10.583s
   ```

2. **Con `COPY src ./src` por encima de `COPY pom.xml .`**: cualquier cambio en el código invalida la capa de `COPY src` y todas las posteriores, incluida la descarga de dependencias. Maven vuelve a descargarlo todo en cada build, y el tiempo aumenta claramente.

   ```text
   #9 [build 3/6] COPY src ./src
   #10 [build 4/6] COPY pom.xml .
   #11 [build 5/6] RUN mvn -B dependency:go-offline
   #12 [build 6/6] RUN mvn -B clean package -DskipTests

   real    0m56.249s
   ```

Con el mismo cambio de una línea, la reconstrucción pasó de **10,6 s** a **56,2 s**, unas cinco veces más lenta. Casi todo ese tiempo es la nueva descarga de dependencias que la caché había evitado en el primer caso.

Docker invalida la caché a partir de la primera instrucción cuya entrada ha cambiado. Por eso conviene copiar primero lo que cambia poco (el `pom.xml`) y al final lo que cambia a menudo (el código). Tras la prueba, el Dockerfile se dejó en su orden original.

### Parte D — De H2 a PostgreSQL con Compose

#### D.1 Cambiar la base de datos

Hasta este boletín la aplicación guardaba las tareas en memoria con un repositorio propio (`InMemoryTaskRepository` sobre un `ConcurrentHashMap`), sin H2 ni JPA. Con Copilot se pasó a Spring Data JPA con Hibernate y PostgreSQL ([b780f8e](https://github.com/OlgaTYP/Practicas-continuas/commit/b780f8e)):

- `pom.xml`: `spring-boot-starter-data-jpa` y el driver `org.postgresql:postgresql`.
- `Task` pasa a ser una entidad `@Entity`, y `TaskRepository` extiende `JpaRepository`.
- `application.properties` no tiene ninguna propiedad de H2 e incluye `spring.jpa.hibernate.ddl-auto=update`, para que Hibernate cree o actualice las tablas al arrancar.

Las propiedades `spring.datasource.*` de `application.properties` tienen valores por defecto para desarrollo local. En Compose se sustituyen por las variables de entorno `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD`, porque en Spring Boot las variables de entorno tienen prioridad sobre el fichero de propiedades.

A partir de este cambio la imagen ya no arranca sola con `docker run`, porque necesita una base de datos. Desde aquí el stack se levanta siempre con Compose.

#### D.2 `docker-compose.yml`

Se creó el `docker-compose.yml` del enunciado ([3bc3f8c](https://github.com/OlgaTYP/Practicas-continuas/commit/3bc3f8c)). El servicio `db` expone el puerto 5432 solo en `127.0.0.1`, guarda los datos en el volumen nombrado `db-data` y declara un healthcheck con `pg_isready`. El servicio `api` se construye con el Dockerfile y espera a que `db` esté _healthy_ (`depends_on: condition: service_healthy`). No declara healthcheck propio porque hereda el `HEALTHCHECK` del Dockerfile.

#### D.3 Comprobar la persistencia

```bash
docker compose up --build -d
docker compose ps            # ambos servicios (healthy)
docker compose logs -f api
```

Se creó una tarea con los campos de nuestra entidad:

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Comprobar persistencia","description":"Tarea para comprobar la persistencia en PostgreSQL","status":"TODO","priority":"MEDIUM","deadline":"2026-12-31"}'

curl http://localhost:8080/api/tasks
```

La API devolvió la tarea con `id` 1, y el `GET` la listaba.

```bash
docker compose down
docker compose up -d
curl http://localhost:8080/api/tasks     # la tarea sigue ahí

docker compose down -v
docker compose up -d
curl http://localhost:8080/api/tasks     # []
```

Salida obtenida. El stack se levantó con `-p b3test` y la API en el puerto 18080, porque el 8080 y el 5432 estaban ocupados por el reenvío de puertos del Dev Container:

```text
$ docker ps
CONTAINER ID   IMAGE         COMMAND                  STATUS                    PORTS                     NAMES
a1dc323a5dda   b3test-api    "java -jar app.jar"      Up 11 seconds (healthy)   0.0.0.0:18080->8080/tcp   b3test-api-1
c83aaf50b4ee   postgres:16   "docker-entrypoint.s…"   Up 17 seconds (healthy)   5432/tcp                  b3test-db-1

$ curl -X POST .../api/tasks ...
{"id":1,"title":"Comprobar persistencia","description":"Tarea para comprobar la persistencia en PostgreSQL","status":"TODO","priority":"MEDIUM","deadline":"2026-12-31",...}

$ docker compose down && docker compose up -d && curl .../api/tasks
[{"id":1,"title":"Comprobar persistencia",...}]

$ docker compose down -v
 Volume b3test_db-data Removing
 Volume b3test_db-data Removed
$ docker compose up -d && curl .../api/tasks
[]
```

**Diferencia entre `down` y `down -v`:**

- `docker compose down` para y elimina los contenedores y la red del proyecto, pero **conserva los volúmenes nombrados**. Al volver a levantar el stack, PostgreSQL monta el mismo `db-data` y los datos siguen ahí.
- `docker compose down -v` elimina además los **volúmenes** declarados en el Compose (`db-data`). Se pierden los datos y PostgreSQL inicializa una base de datos vacía en el siguiente arranque.

La persistencia depende del volumen, no del contenedor: los contenedores se pueden destruir y recrear sin perder información mientras el volumen exista.

### Parte E — Sacar la configuración a un fichero `.env`

Con la versión de D.2, la contraseña estaba escrita en `docker-compose.yml` y acababa en el repositorio. Se sacó a un fichero `.env` siguiendo los pasos del enunciado.

1. **Plantilla `.env.example`** ([d1d20ca](https://github.com/OlgaTYP/Practicas-continuas/commit/d1d20ca)), versionada y sin valores reales:

   ```dotenv
   # Copia este fichero a .env y ajusta los valores
   POSTGRES_DB=tareas
   POSTGRES_USER=app
   POSTGRES_PASSWORD=cambia_esto
   ```

2. **`.env` local**, creado a partir de la plantilla con la contraseña de desarrollo:

   ```powershell
   Copy-Item .env.example .env    # equivalente a: cp .env.example .env
   ```

3. **`.env` fuera de Git:** `.gitignore` incluye `.env` desde el commit inicial del Boletín 1, y `git ls-files` solo muestra `.env.example`. El `.env` nunca se ha subido, así que no hizo falta `git rm --cached .env` ni cambiar la contraseña.
4. **`.env` fuera de la imagen:** se comprobó que `.dockerignore` contiene `.env` (Parte C), así que el fichero no se envía en el contexto de build.
5. **Variables en `docker-compose.yml`** ([f94549d](https://github.com/OlgaTYP/Practicas-continuas/commit/f94549d)):

   ```yaml
   services:
     db:
       image: postgres:16
       environment:
         POSTGRES_DB: ${POSTGRES_DB}
         POSTGRES_USER: ${POSTGRES_USER}
         POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:?Define POSTGRES_PASSWORD en el fichero .env}
       ports: ["127.0.0.1:5432:5432"]
       volumes:
         - db-data:/var/lib/postgresql/data
       healthcheck:
         test:
           ["CMD-SHELL", "pg_isready -U $${POSTGRES_USER} -d $${POSTGRES_DB}"]
         interval: 5s
         timeout: 3s
         retries: 10

     api:
       build: .
       ports: ["8080:8080"]
       environment:
         SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/${POSTGRES_DB}
         SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
         SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
       depends_on:
         db:
           condition: service_healthy

   volumes:
     db-data:
   ```

   - `${VAR}`: Compose sustituye la variable por su valor al procesar el YAML. El valor sale del entorno desde el que se ejecuta `docker compose` o del `.env`; si está en ambos, gana el entorno.
   - `${VAR:?mensaje}`: obliga a que la variable exista y no esté vacía. Si no, Compose no arranca y muestra el mensaje.
   - `$${VAR}`: `$$` escapa el `$`, así que Compose deja `${VAR}` literal y lo resuelve el shell dentro del contenedor de PostgreSQL, donde esas variables existen.

6. **Verificar la sustitución:**

   ```bash
   docker compose config
   ```

   En la salida aparecían `tareas`, `app` y la contraseña local en lugar de `${...}`. Como esta salida muestra la contraseña resuelta, no debe publicarse sin ocultarla.

7. **Levantar el stack desde cero:**

   ```bash
   docker compose down -v
   docker compose up --build -d
   docker compose ps
   curl http://localhost:8080/actuator/health     # {"status":"UP"}
   ```

PostgreSQL solo aplica `POSTGRES_USER`, `POSTGRES_PASSWORD` y `POSTGRES_DB` la primera vez que inicializa el volumen. Si se cambia la contraseña en `.env` con `db-data` ya creado, la base de datos sigue con la antigua y la API no puede conectarse. En desarrollo se soluciona con `docker compose down -v`, que borra los datos.

### Parte F — Dev container

Se creó `.devcontainer/devcontainer.json` ([2cfcf16](https://github.com/OlgaTYP/Practicas-continuas/commit/2cfcf16)) siguiendo la [Dev Containers spec](https://containers.dev/):

```json
{
  "name": "tareas-api",
  "image": "mcr.microsoft.com/devcontainers/java:21",
  "features": {
    "ghcr.io/devcontainers/features/java:1": {
      "version": "21",
      "installMaven": "true"
    },
    "ghcr.io/devcontainers/features/docker-in-docker:2": { "moby": false }
  },
  "forwardPorts": [8080, 5432],
  "postCreateCommand": "test -f .env || cp .env.example .env; if [ -d .githooks ]; then git config core.hooksPath .githooks; fi; mvn -B dependency:go-offline",
  "customizations": {
    "vscode": {
      "extensions": ["vscjava.vscode-java-pack", "ms-azuretools.vscode-docker"]
    }
  }
}
```

| Campo                              | Qué indica                                                                                                                                                                                             |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `name`                             | Nombre del entorno que muestra VS Code (`tareas-api`).                                                                                                                                                 |
| `image`                            | Imagen base del contenedor de desarrollo: `mcr.microsoft.com/devcontainers/java:21`, una imagen de Microsoft preparada para Java 21 con usuario no root, git y utilidades habituales.                  |
| `features`                         | Bloques reutilizables y versionados que instalan herramientas extra sobre la imagen base.                                                                                                              |
| `features` → `java:1`              | Asegura Java 21 (`"version": "21"`) e instala Maven (`"installMaven": "true"`).                                                                                                                        |
| `features` → `docker-in-docker:2`  | Instala un motor Docker propio dentro del contenedor de desarrollo. `"moby": false` hace que instale Docker CE oficial en lugar de Moby, la versión open source que empaquetan algunas distribuciones. |
| `forwardPorts`                     | Reenvía los puertos 8080 (API) y 5432 (PostgreSQL) del contenedor a `localhost` de la máquina anfitriona.                                                                                              |
| `postCreateCommand`                | Órdenes que se ejecutan una sola vez, cuando se crea el contenedor.                                                                                                                                    |
| `customizations.vscode.extensions` | Extensiones de VS Code que se instalan dentro del contenedor: Java Extension Pack y Docker.                                                                                                            |

**¿Para qué sirve `docker-in-docker`?** El dev container es a su vez un contenedor, y por defecto no tiene Docker dentro. Esta feature ejecuta un motor Docker aislado dentro del dev container, de modo que desde su terminal se pueden usar `docker build` y `docker compose up` como en la máquina local, sin compartir el motor del anfitrión.

**Las tres órdenes de `postCreateCommand`:**

1. `test -f .env || cp .env.example .env`: si no existe `.env`, lo crea a partir de la plantilla, para que Compose tenga sus variables desde el primer momento. Si ya existe, no lo toca.
2. `if [ -d .githooks ]; then git config core.hooksPath .githooks; fi`: si el repositorio tiene la carpeta `.githooks`, configura Git para usarla. Así el hook de pre-commit con Spotless (Boletín 1) queda activo sin que nadie tenga que acordarse de ejecutar el `git config`.
3. `mvn -B dependency:go-offline`: descarga por adelantado, en modo no interactivo (`-B`), todas las dependencias y plugins de Maven. Así la primera compilación o ejecución de tests dentro del contenedor no tiene que esperar a las descargas.

**Prueba:** se paró el stack local (`docker compose down`) para liberar los puertos, se instaló la extensión **Dev Containers** y se ejecutó **Dev Containers: Reopen in Container**. La primera apertura tardó más porque había que descargar la imagen e instalar las features. Después, la terminal mostraba `/workspaces/Practicas-continuas` con Java 21 y Maven disponibles. Desde esa terminal:

```bash
docker compose up --build
```

Desde el navegador de la máquina anfitriona, <http://127.0.0.1:8080/actuator/health> devolvió `{"status":"UP"}`, gracias al reenvío del puerto 8080.

### Parte G — Versionar los cambios

Se documentó en el README cómo levantar el proyecto con Docker y con Dev Container ([550e2f7](https://github.com/OlgaTYP/Practicas-continuas/commit/550e2f7)). Se creó el [Issue #10](https://github.com/OlgaTYP/Practicas-continuas/issues/10) (_Dockerizar la API y configurar el entorno Dev Container_, etiqueta `enhancement`) y, desde la rama de trabajo, la rama del PR:

```bash
git switch -c feat/docker-pr
git push -u origin feat/docker-pr
```

Se abrió el [PR #11](https://github.com/OlgaTYP/Practicas-continuas/pull/11) (`feat/docker-pr` → `main`) con la plantilla del Boletín 2 y `Closes #10`. El PR incluye los 13 commits del boletín:

- `Dockerfile` y `.dockerignore`
- `docker-compose.yml` y `.env.example`
- `.gitignore` con `.env` (ya presente desde el Boletín 1)
- `pom.xml` y `application.properties`, más las clases de persistencia JPA
- `.devcontainer/devcontainer.json`
- `README.md` actualizado

rmbgeuskadi lo revisó y aprobó, se fusionó con _merge commit_ ([9c78761](https://github.com/OlgaTYP/Practicas-continuas/commit/9c78761)) y cerró el Issue #10.

### Parte H — Registry de Docker (no modifica el repositorio)

Se publicó la imagen de la API en Docker Hub:

- **Repositorio:** [olgatyp/pc-todo-api](https://hub.docker.com/r/olgatyp/pc-todo-api)
- **Imagen:** `olgatyp/pc-todo-api:1.0`

Pasos seguidos:

1. **Crear el repositorio** `pc-todo-api` en Docker Hub (cuenta `olgatyp`).
2. **Iniciar sesión** desde la terminal:

   ```bash
   docker login        # Login Succeeded
   ```

3. **Etiquetar la imagen local.** La imagen generada por Compose se llama `<proyecto>-<servicio>`, en este caso `practicas-continuas-api`. Para subirla, el nombre debe empezar por el usuario de Docker Hub:

   ```bash
   docker tag practicas-continuas-api:latest olgatyp/pc-todo-api:1.0
   ```

4. **Subir la imagen:**

   ```bash
   docker push olgatyp/pc-todo-api:1.0
   ```

5. **Verificar** que se puede descargar:

   ```bash
   docker pull olgatyp/pc-todo-api:1.0
   ```

   ```text
   1.0: Pulling from olgatyp/pc-todo-api
   Digest: sha256:7e660632a3dc60d8c4a064e349e9116d3824bdd3b14f38a4070dfc03faa4e1ba
   Status: Image is up to date for olgatyp/pc-todo-api:1.0
   docker.io/olgatyp/pc-todo-api:1.0
   ```

Cualquiera puede descargarla con `docker pull olgatyp/pc-todo-api:1.0`. Para ejecutarla necesita una base de datos PostgreSQL, igual que con el Compose del proyecto.

### Parte I — Entorno de pruebas reproducible (no modifica el repositorio)

#### 1. Repositorio elegido

[spring-projects/spring-petclinic](https://github.com/spring-projects/spring-petclinic): la aplicación de referencia oficial de Spring, con miles de estrellas en GitHub y una batería de tests unitarios y de integración. Se clonó en `/tmp/spring-petclinic`, fuera del repositorio de la práctica.

#### 2. Análisis de requisitos

| Requisito                  | Valor                                                                              | Fuente                                 |
| -------------------------- | ---------------------------------------------------------------------------------- | -------------------------------------- |
| Lenguaje                   | Java                                                                               | README                                 |
| Versión                    | Java 17                                                                            | propiedad `java.version` del `pom.xml` |
| Build y gestor de paquetes | Apache Maven, mediante Maven Wrapper (`mvnw`)                                      | README, `mvnw` y `.mvn/wrapper/`       |
| Dependencias               | Se descargan de Maven Central según el `pom.xml`                                   | `pom.xml`                              |
| Dependencias del sistema   | Ninguna aparte del JDK; por defecto los tests usan una base de datos H2 en memoria | README                                 |

#### 3. Dockerfile

`Dockerfile.tests`:

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /workspace

COPY mvnw pom.xml .mvn/ ./
RUN chmod +x mvnw

CMD ["./mvnw", "-B", "test"]
```

La imagen fija el entorno de ejecución, JDK 17 (Eclipse Temurin), y lanza los tests con Maven Wrapper. Así se usa exactamente la versión de Maven que declara el proyecto, sin instalar Maven en la imagen.

#### 4. Construcción y ejecución

```bash
docker build -f Dockerfile.tests -t petclinic-tests:1.0 .

docker run --rm \
  -v "$PWD:/workspace" \
  -w /workspace \
  petclinic-tests:1.0
```

El código del repositorio clonado se monta en `/workspace` como volumen, así que los tests se ejecutan siempre sobre el código actual sin reconstruir la imagen. Al montarse el volumen, `mvnw`, `pom.xml` y `.mvn/` se toman del propio repositorio, y Maven Wrapper descarga Maven y las dependencias del proyecto dentro del contenedor durante la ejecución. `--rm` elimina el contenedor al terminar.

#### 5. Resultado

La ejecución terminó con `BUILD SUCCESS`:

| Tests ejecutados | Fallos | Errores | Omitidos | Tiempo      |
| ---------------- | ------ | ------- | -------- | ----------- |
| 79               | 0      | 0       | 2        | ≈ 1 min 9 s |

La batería de tests de spring-petclinic se ejecuta de forma reproducible en un contenedor con el JDK fijado, sin instalar Java ni Maven en la máquina.

## 4. Capturas de pantalla y otras evidencias

Se adjuntan capturas de:

1. `docker --version`, `docker compose version` y `docker run hello-world` (Parte A).
2. Salida de `docker compose ps` (Parte D).

Las salidas de terminal de `docker images tareas-api`, `time docker build` con los dos órdenes del Dockerfile, `docker ps` con los contenedores `(healthy)` y la prueba de persistencia con `down` / `down -v` están incluidas como texto en las Partes C y D.
3. Publicación y descarga de `olgatyp/pc-todo-api:1.0` (Parte H).
4. Construcción de `petclinic-tests:1.0` y resultado de Maven con `BUILD SUCCESS` (Parte I).
