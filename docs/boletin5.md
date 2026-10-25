# Boletín 5: Entrega continua — publicar la imagen y versionar

> Pareja: Olga Yanes ([@OlgaTYP](https://github.com/OlgaTYP)) y Roxana Bareiro ([@rmbgeuskadi](https://github.com/rmbgeuskadi)).
> Repositorio: [OlgaTYP/Practicas-continuas](https://github.com/OlgaTYP/Practicas-continuas)

## Resumen de commits

| #   | Mensaje del commit                                          | Qué se hizo                                                                                                                            | Autor       | Enlace                                                                   |
| --- | ----------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ----------- | ------------------------------------------------------------------------ |
| 1   | `ci: Añadir release - workflow`                             | Creación de `.github/workflows/release.yml`, que construye la imagen Docker y la publica en GitHub Container Registry (GHCR).          | OlgaTYP     | [39c4d98](https://github.com/OlgaTYP/Practicas-continuas/commit/39c4d98) |
| —   | `Merge pull request #21 from OlgaTYP/feat/release-workflow` | Integración en `main` del workflow mediante el [PR #21](https://github.com/OlgaTYP/Practicas-continuas/pull/21), aprobado por OlgaTYP. | rmbgeuskadi | [7f60608](https://github.com/OlgaTYP/Practicas-continuas/commit/7f60608) |
| 2   | `doc: Añadir boletines en docs`                             | Se traen las memorias de los boletines 1 a 5 a `docs/`.                                                                                 | OlgaTYP     | [c67eb98](https://github.com/OlgaTYP/Practicas-continuas/commit/c67eb98) |
| 3   | `feat(docs): añade script para generar HTML`                | Script `docs/build-site.mjs`, que convierte los `.md` en HTML y genera `docs/site/index.html`, con su `package.json`.                    | OlgaTYP     | [64d8ee3](https://github.com/OlgaTYP/Practicas-continuas/commit/64d8ee3) |
| 4   | `chore(docs): ignora los archivos HTML generados`           | `docs/site/` en el `.gitignore`.                                                                                                       | Roxana Bareiro | [10f457c](https://github.com/OlgaTYP/Practicas-continuas/commit/10f457c) |
| 5   | `ci(pages): automatiza la publicación de documentación`     | Workflow `.github/workflows/pages.yml`, que genera la web y la publica en GitHub Pages.                                                  | Roxana Bareiro | [0b0d6ab](https://github.com/OlgaTYP/Practicas-continuas/commit/0b0d6ab) |
| —   | `Merge pull request #22 from OlgaTYP/feat/github-pages-docs` | Integración en `main` de los commits 2 a 5 mediante el [PR #22](https://github.com/OlgaTYP/Practicas-continuas/pull/22), aprobado por OlgaTYP. | rmbgeuskadi | [18f2416](https://github.com/OlgaTYP/Practicas-continuas/commit/18f2416) |
| 6   | `fix(pages): instala dependencia marked`                    | Paso `npm install marked` en el workflow, que fallaba porque faltaba la dependencia.                                                     | Roxana Bareiro | [177189b](https://github.com/OlgaTYP/Practicas-continuas/commit/177189b) |
| —   | `Merge pull request #23 from OlgaTYP/feat/github-pages-docs` | Integración en `main` de la corrección mediante el [PR #23](https://github.com/OlgaTYP/Practicas-continuas/pull/23), aprobado y fusionado por OlgaTYP. | OlgaTYP | [fbb610d](https://github.com/OlgaTYP/Practicas-continuas/commit/fbb610d) |

Además de los commits, en este boletín se crearon en GitHub:

| Elemento                      | Qué es                                                                                      | Autor          | Enlace                                                                                                                 |
| ----------------------------- | ------------------------------------------------------------------------------------------- | -------------- | ---------------------------------------------------------------------------------------------------------------------- |
| Etiqueta `v1.0.0`             | Etiqueta anotada sobre `7f60608`: «Primera versión publicable de la API de tareas».         | Roxana Bareiro | [v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/tree/v1.0.0)                                                   |
| Release `v1.0.0`              | _v1.0.0 — Primera versión publicable_, con notas generadas automáticamente.                 | OlgaTYP        | [Release v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/releases/tag/v1.0.0)                                   |
| Paquete `practicas-continuas` | Imagen Docker publicada en GHCR con los tags `sha-7f60608`, `latest`, `1.0.0`, `1.0` y `1`. | GitHub Actions | [ghcr.io/olgatyp/practicas-continuas](https://github.com/users/OlgaTYP/packages/container/package/practicas-continuas) |
| Web de documentación          | Boletines publicados en GitHub Pages.                                                       | GitHub Actions | [olgatyp.github.io/Practicas-continuas](https://olgatyp.github.io/Practicas-continuas/)                                |

## Detalle por commit

### 1. `ci: Añadir release - workflow`

- **Qué se hizo:** se creó `.github/workflows/release.yml` con el job `publish`. El job hace checkout, inicia sesión en `ghcr.io` con el `GITHUB_TOKEN`, calcula los tags de la imagen con `docker/metadata-action` y construye y sube la imagen con `docker/build-push-action`. Se dispara con cada push a `main` y con cada etiqueta `v*.*.*`.
- **Autor:** OlgaTYP. El PR #21 lo abrió rmbgeuskadi, lo aprobó OlgaTYP y lo fusionó rmbgeuskadi.
- **Relación con el boletín:** Parte A.
- **Uso de IA:** no se ha utilizado IA

### 2. `doc: Añadir boletines en docs`

- **Qué se hizo:** se añadieron a `docs/` las memorias de todas las sesiones: `boletin1A.md` y `boletin1B.md` (el Boletín 1 fue individual y hay una memoria por integrante), `boletin2.md`, `boletin3.md`, `boletin4.md` y `boletin5.md`. Se eliminó el antiguo `docs/boletin1.md`. El commit incluye además las dos correcciones de coherencia descritas en el Boletín 1: `indent_size = 2` en `.editorconfig` y el permiso de ejecución del hook `.githooks/pre-commit`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte D (traer las memorias al repositorio de la API).
- **Uso de IA:** <!-- PENDIENTE: indicar si se usó IA en este commit -->

### 3. `feat(docs): añade script para generar HTML`

- **Qué se hizo:** se añadió `docs/build-site.mjs`, un script en Node que lee todos los `.md` de `docs/`, los convierte a HTML con la librería `marked` y genera en `docs/site/` una página por boletín y un `index.html` que enlaza a todos. Se añadieron también `docs/package.json` y `docs/package-lock.json` con la dependencia `marked`, y `docs/site/` y `docs/node_modules/` al `.gitignore`.
- **Autor:** OlgaTYP
- **Relación con el boletín:** Parte D (script de generación).
- **Uso de IA:** se generó con ayuda de IA (Claude Code), pidiéndole un script que leyese los `.md` de `docs/` y generase un `.html` por cada uno y un `index.html` en `docs/site/`.

### 4. `chore(docs): ignora los archivos HTML generados`

- **Qué se hizo:** se añadió `docs/site/` al `.gitignore` para que la web generada no se suba al repositorio.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte D.
- **Uso de IA:** <!-- PENDIENTE: indicar si se usó IA en este commit -->

### 5. `ci(pages): automatiza la publicación de documentación`

- **Qué se hizo:** se creó `.github/workflows/pages.yml`, que en cada push a `main` genera la web con el script y la publica en GitHub Pages con las acciones oficiales (`configure-pages`, `upload-pages-artifact` y `deploy-pages`).
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte D (automatización).
- **Uso de IA:** <!-- PENDIENTE: indicar si se usó IA en este commit -->

### 6. `fix(pages): instala dependencia marked`

- **Qué se hizo:** la primera ejecución del workflow tras fusionar el PR #22 falló en el paso *Generate documentation website*: el runner no tenía instalada la librería `marked`, que el script importa. Se añadió el paso `npm install marked` antes de generar la web, y con el PR #23 el workflow pasó a verde.
- **Autor:** Roxana Bareiro
- **Relación con el boletín:** Parte D.
- **Uso de IA:** <!-- PENDIENTE: indicar si se usó IA en este commit -->

## Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Pipeline de publicación

Se añadió el workflow `.github/workflows/release.yml` ([39c4d98](https://github.com/OlgaTYP/Practicas-continuas/commit/39c4d98)):

```yaml
name: Release

on:
  push:
    branches: [main]
    tags: ["v*.*.*"]

permissions:
  contents: read
  packages: write

jobs:
  publish:
    runs-on: ubuntu-latest
    timeout-minutes: 20
    steps:
      - uses: actions/checkout@v4

      - name: Login en GHCR
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Metadatos (tags)
        id: meta
        uses: docker/metadata-action@v5
        with:
          images: ghcr.io/${{ github.repository }}
          tags: |
            type=sha,prefix=sha-
            type=semver,pattern={{version}}
            type=semver,pattern={{major}}.{{minor}}
            type=semver,pattern={{major}}
            type=raw,value=latest,enable={{is_default_branch}}

      - name: Construir y publicar
        uses: docker/build-push-action@v6
        with:
          context: .
          push: true
          tags: ${{ steps.meta.outputs.tags }}
          labels: ${{ steps.meta.outputs.labels }}
```

Puntos clave:

- **`permissions`:** el `GITHUB_TOKEN` solo puede leer el código (`contents: read`) y escribir en el registro de paquetes (`packages: write`). No hace falta ningún secreto propio para publicar en GHCR.
- **`docker/login-action`:** autentica contra `ghcr.io` con el usuario que dispara el workflow (`github.actor`) y el token temporal del job.
- **`docker/metadata-action`:** calcula la lista de tags a partir del evento que disparó el workflow, siguiendo las reglas de `tags:`, y genera las etiquetas OCI (origen, revisión, fecha…).
- **`docker/build-push-action`:** construye la imagen con el `Dockerfile` multi-stage del Boletín 3 y la sube con todos los tags calculados.
- **Nombre de la imagen:** `ghcr.io/${{ github.repository }}`, que se convierte en `ghcr.io/olgatyp/practicas-continuas`. Docker exige minúsculas en los nombres de imagen.

#### Qué ocurre en cada caso y qué tags se generan

**1) Al fusionar un PR a `main`.** La fusión del PR #21 generó un push a `main` con el commit de merge `7f60608`, y se lanzó el workflow ([ejecución](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40214227581)). Las reglas de `metadata-action` se evalúan así:

| Regla                                                | ¿Genera tag?                                                             | Tag           |
| ---------------------------------------------------- | ------------------------------------------------------------------------ | ------------- |
| `type=sha,prefix=sha-`                               | Sí, siempre: usa el SHA corto del commit.                                | `sha-7f60608` |
| `type=semver,...` (las tres)                         | No: solo actúan cuando el evento es una etiqueta con formato de versión. | —             |
| `type=raw,value=latest,enable={{is_default_branch}}` | Sí: el push es a la rama por defecto (`main`).                           | `latest`      |

Resultado: **`sha-7f60608`** y **`latest`**. El tag `sha-…` identifica de forma inmutable qué commit contiene la imagen, y `latest` siempre apunta a lo último que hay en `main`.

**2) Al publicar una etiqueta `vX.Y.Z`.** Con `v1.0.0`, el evento es un push de etiqueta ([ejecución](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40215844961)):

| Regla                                                | ¿Genera tag?                                | Tag           |
| ---------------------------------------------------- | ------------------------------------------- | ------------- |
| `type=sha,prefix=sha-`                               | Sí: la etiqueta apunta al mismo commit.     | `sha-7f60608` |
| `type=semver,pattern={{version}}`                    | Sí: versión completa, sin la `v`.           | `1.0.0`       |
| `type=semver,pattern={{major}}.{{minor}}`            | Sí.                                         | `1.0`         |
| `type=semver,pattern={{major}}`                      | Sí.                                         | `1`           |
| `type=raw,value=latest,enable={{is_default_branch}}` | No: una etiqueta no es la rama por defecto. | —             |

Además, `metadata-action` trabaja por defecto con `flavor: latest=auto`, que añade `latest` cuando se publica una versión semver estable (no _pre-release_). Resultado: **`1.0.0`**, **`1.0`**, **`1`**, **`sha-7f60608`** y **`latest`**.

Los tres niveles semver permiten que cada usuario elija cuánto quiere actualizar. `1.0.0` queda fijo para siempre; `1.0` recibirá los parches (`1.0.1`, `1.0.2`…); `1` recibirá también las versiones menores (`1.1.0`…), pero nunca un cambio incompatible (`2.0.0`).

#### Comprobación en Packages

Tras el primer push a `main`, la imagen apareció en la pestaña **Packages** del repositorio ([ghcr.io/olgatyp/practicas-continuas](https://github.com/users/OlgaTYP/packages/container/package/practicas-continuas)) con los tags `sha-7f60608` y `latest`.

El comportamiento se repitió con cada fusión posterior: los merges de los PR #22 y #23 (Parte D) publicaron `sha-18f2416` y `sha-fbb610d`, y `latest` pasó a apuntar a la imagen más reciente de `main`. En cambio, `1.0.0`, `1.0` y `1` siguen apuntando a la imagen de la versión etiquetada.

### Parte B — Publicar una versión de verdad

Se creó la etiqueta anotada y se subió al remoto:

```bash
git tag -a v1.0.0 -m "Primera versión publicable de la API de tareas"
git push origin v1.0.0
```

Una etiqueta **anotada** es un objeto propio de Git, con autor (Roxana Bareiro), fecha y mensaje, a diferencia de una etiqueta ligera, que es solo un puntero a un commit. Es la forma recomendada para marcar versiones.

El push de la etiqueta volvió a disparar el workflow `Release`. El historial de ejecuciones muestra tres, todas correctas:

| Evento                                         | Ref      | Commit    | Resultado                                                                             |
| ---------------------------------------------- | -------- | --------- | ------------------------------------------------------------------------------------- |
| Push (merge del PR #21)                        | `main`   | `7f60608` | ✅ [success](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40214227581) |
| Push de etiqueta                               | `v1.0.0` | `7f60608` | ✅ [success](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40214677170) |
| Push de etiqueta (etiqueta anotada definitiva) | `v1.0.0` | `7f60608` | ✅ [success](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40215844961) |

Después, el paquete muestra la imagen con los cinco tags. Todos apuntan exactamente a la misma imagen (mismo _digest_), porque se construyeron a partir del mismo commit:

```text
latest       sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
sha-7f60608  sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
1.0.0        sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
1.0          sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
1            sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
```

Por último, se creó la **Release** [v1.0.0 — Primera versión publicable](https://github.com/OlgaTYP/Practicas-continuas/releases/tag/v1.0.0) a partir de la etiqueta, con **Generate release notes**. GitHub generó automáticamente la lista de PR incluidos en la versión (del #3 al #21) y los nuevos contribuidores: @luciaolmosmartinez, de la pareja que contribuyó desde el fork, @rmbgeuskadi y @OlgaTYP.

### Parte C — Verificar la imagen publicada

El paquete es público, así que la imagen se descarga sin autenticarse. Se levantó PostgreSQL y la API en una red de Docker, igual que hace el `docker-compose.yml`:

```bash
docker network create tareas-net

docker run -d --name db --network tareas-net \
  -e POSTGRES_DB=tareas -e POSTGRES_USER=app -e POSTGRES_PASSWORD=secret_local_dev \
  postgres:16

docker pull ghcr.io/olgatyp/practicas-continuas:1.0.0
docker run -d --name api --network tareas-net -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tareas \
  -e SPRING_DATASOURCE_USERNAME=app \
  -e SPRING_DATASOURCE_PASSWORD=secret_local_dev \
  ghcr.io/olgatyp/practicas-continuas:1.0.0

curl http://localhost:8080/actuator/health
```

Salida obtenida. La API se publicó en el puerto 18080 porque el 8080 estaba ocupado por el reenvío de puertos del Dev Container:

```text
$ docker pull ghcr.io/olgatyp/practicas-continuas:1.0.0
Digest: sha256:a68c2e78dbebeca0ec47f4b0bcbe095fc3cc5e13af7ab1fa3a8a84439197a827
Status: Downloaded newer image for ghcr.io/olgatyp/practicas-continuas:1.0.0
ghcr.io/olgatyp/practicas-continuas:1.0.0

$ docker ps
NAMES     IMAGE                                       STATUS                   PORTS
api       ghcr.io/olgatyp/practicas-continuas:1.0.0   Up 7 seconds (healthy)   0.0.0.0:18080->8080/tcp
db        postgres:16                                 Up 12 seconds            5432/tcp

$ curl http://localhost:18080/actuator/health
{"status":"UP"}
```

Los dos contenedores se comunican por la red `tareas-net`, donde Docker resuelve el nombre `db` como host de PostgreSQL. La API arrancó, se conectó a la base de datos y su `HEALTHCHECK` la marcó como `(healthy)`. Al terminar se limpió todo:

```bash
docker rm -f db api && docker network rm tareas-net
```

### Parte D — GitHub Pages de la documentación

La web está publicada en **<https://olgatyp.github.io/Practicas-continuas/>**.

#### 1. Traer las memorias a `docs/`

Las memorias de cada sesión se copiaron a la carpeta `docs/` de la raíz del repositorio de la API ([c67eb98](https://github.com/OlgaTYP/Practicas-continuas/commit/c67eb98)), una por sesión entregada:

```text
docs/
├── boletin1A.md   ← Boletín 1 (individual): memoria de Olga Yanes
├── boletin1B.md   ← Boletín 1 (individual): memoria de Roxana Bareiro
├── boletin2.md
├── boletin3.md
├── boletin4.md
├── boletin5.md
├── build-site.mjs
├── package.json
└── package-lock.json
```

#### 2. Script de generación

El script `docs/build-site.mjs` ([64d8ee3](https://github.com/OlgaTYP/Practicas-continuas/commit/64d8ee3)) se escribió en Node con ayuda de IA y usa una única dependencia, [`marked`](https://www.npmjs.com/package/marked), para convertir Markdown a HTML. Lo que hace:

1. Lee todos los `.md` de `docs/` y los ordena de forma natural (`boletin1A`, `boletin1B`, `boletin2`…).
2. Convierte cada uno a HTML con soporte de GitHub Flavored Markdown (tablas, bloques de código, enlaces). Toma el título de la página del primer encabezado `#`.
3. Envuelve cada página en una plantilla común: menú superior con todos los boletines, enlaces *anterior / siguiente* al final, estilos para modo claro y oscuro, y diseño adaptado al móvil.
4. Reescribe los enlaces entre boletines (`boletin2.md` → `boletin2.html`) para que funcionen en la web.
5. Genera `index.html` con una tarjeta por boletín: título y la línea de autoría (`> Pareja: …`).
6. Si existe `docs/img/`, la copia a `docs/site/img/`, para que las capturas enlazadas desde los `.md` se vean también en la web.
7. Borra `docs/site/` antes de generar, para que no queden páginas de boletines eliminados.

**Prueba en local:**

```bash
cd docs
npm ci
npm run build
```

```text
  boletin1A.md → site/boletin1A.html
  boletin1B.md → site/boletin1B.html
  boletin2.md → site/boletin2.html
  boletin3.md → site/boletin3.html
  boletin4.md → site/boletin4.html
  boletin5.md → site/boletin5.html
  index.html
Web generada en .../docs/site (6 páginas + index.html)
```

Después se abrió `docs/site/index.html` en el navegador para revisar el resultado.

#### 3. Ignorar la web generada

`docs/site/` está en el `.gitignore` ([64d8ee3](https://github.com/OlgaTYP/Practicas-continuas/commit/64d8ee3), [10f457c](https://github.com/OlgaTYP/Practicas-continuas/commit/10f457c)), junto con `docs/node_modules/`. La web es un resultado de build: se regenera a partir de los `.md` en cada publicación, así que versionarla solo duplicaría contenido y provocaría conflictos.

#### 4. Workflow de publicación

`.github/workflows/pages.yml` ([0b0d6ab](https://github.com/OlgaTYP/Practicas-continuas/commit/0b0d6ab), corregido en [177189b](https://github.com/OlgaTYP/Practicas-continuas/commit/177189b)):

```yaml
name: Publish documentation to GitHub Pages

on:
  push:
    branches:
      - main
  workflow_dispatch:

permissions:
  contents: read
  pages: write
  id-token: write

concurrency:
  group: pages
  cancel-in-progress: true

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Setup Node.js
        uses: actions/setup-node@v4
        with:
          node-version: 22

      - name: Install dependencies
        run: npm install marked

      - name: Generate documentation website
        run: node docs/build-site.mjs

      - name: Configure GitHub Pages
        uses: actions/configure-pages@v5

      - name: Upload site artifact
        uses: actions/upload-pages-artifact@v4
        with:
          path: docs/site/

  deploy:
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    runs-on: ubuntu-latest
    needs: build
    steps:
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

- **Disparadores:** cada push a `main` y, con `workflow_dispatch`, también a mano desde la pestaña *Actions*.
- **`permissions`:** `pages: write` para publicar e `id-token: write` para que `deploy-pages` se autentique con un token OIDC. No hace falta ningún secreto.
- **`concurrency: pages`:** si llegan dos pushes seguidos, solo se publica la versión más reciente.
- **Job `build`:** instala Node 22 y `marked`, ejecuta el script y sube `docs/site/` como artefacto de Pages (`upload-pages-artifact`).
- **Job `deploy`:** espera a `build` (`needs`) y publica el artefacto con `deploy-pages` en el entorno `github-pages`. La URL publicada aparece en el resumen de la ejecución.

#### 5. Configuración del repositorio

- El repositorio es **público**, condición necesaria para GitHub Pages en una cuenta gratuita.
- En *Settings → Pages* se cambió **Source** a **GitHub Actions**, para que publique el workflow en lugar de una rama.

#### 6. Publicación y corrección

| Ejecución | Commit | Resultado |
|-----------|--------|-----------|
| Merge del PR #22 | `18f2416` | ❌ [failure](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40231217234): falla el paso *Generate documentation website* |
| Merge del PR #23 | `fbb610d` | ✅ [success](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40233030276): `build` y `deploy` correctos |

La primera ejecución falló porque el runner parte de una máquina limpia y el script importa `marked`, que no estaba instalada. En local funcionaba porque `docs/node_modules/` ya existía. Se corrigió añadiendo el paso `Install dependencies` ([177189b](https://github.com/OlgaTYP/Practicas-continuas/commit/177189b), [PR #23](https://github.com/OlgaTYP/Practicas-continuas/pull/23)). Node busca los módulos subiendo por los directorios, así que `marked`, instalada en la raíz del repositorio, la encuentra también el script de `docs/`.

#### 7. Comprobación

Tras la ejecución correcta, la web responde en <https://olgatyp.github.io/Practicas-continuas/>. El índice enlaza a las seis memorias (`boletin1A.html`, `boletin1B.html`, `boletin2.html` … `boletin5.html`) y cada página muestra el contenido completo del boletín con sus tablas y bloques de código. Esta web es la documentación que se entrega.

## Capturas de pantalla y otras evidencias

- Ejecuciones del workflow `Release`: [push a main](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40214227581) y [etiqueta v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40215844961).
- Paquete en GHCR con los tags `latest`, `sha-7f60608`, `1.0.0`, `1.0` y `1`: [ghcr.io/olgatyp/practicas-continuas](https://github.com/users/OlgaTYP/packages/container/package/practicas-continuas).
- Release: [v1.0.0 — Primera versión publicable](https://github.com/OlgaTYP/Practicas-continuas/releases/tag/v1.0.0).
- Ejecuciones del workflow de Pages: [fallida (PR #22)](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40231217234) y [correcta (PR #23)](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40233030276).
- Web publicada: [olgatyp.github.io/Practicas-continuas](https://olgatyp.github.io/Practicas-continuas/).
- Las salidas de terminal de los _digests_ (Parte B) y de la verificación de la imagen con `docker pull`, `docker ps` y `curl` (Parte C) están incluidas como texto en sus apartados.
