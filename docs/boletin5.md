# Boletín 5: Entrega continua — publicar la imagen y versionar

> Pareja: Olga Yanes ([@OlgaTYP](https://github.com/OlgaTYP)) y Roxana Bareiro ([@rmbgeuskadi](https://github.com/rmbgeuskadi)).
> Repositorio: [OlgaTYP/Practicas-continuas](https://github.com/OlgaTYP/Practicas-continuas)

## Resumen de commits

| #   | Mensaje del commit                                          | Qué se hizo                                                                                                                            | Autor       | Enlace                                                                   |
| --- | ----------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ----------- | ------------------------------------------------------------------------ |
| 1   | `ci: Añadir release - workflow`                             | Creación de `.github/workflows/release.yml`, que construye la imagen Docker y la publica en GitHub Container Registry (GHCR).          | OlgaTYP     | [39c4d98](https://github.com/OlgaTYP/Practicas-continuas/commit/39c4d98) |
| —   | `Merge pull request #21 from OlgaTYP/feat/release-workflow` | Integración en `main` del workflow mediante el [PR #21](https://github.com/OlgaTYP/Practicas-continuas/pull/21), aprobado por OlgaTYP. | rmbgeuskadi | [7f60608](https://github.com/OlgaTYP/Practicas-continuas/commit/7f60608) |

Además de los commits, en este boletín se crearon en GitHub:

| Elemento                      | Qué es                                                                                      | Autor          | Enlace                                                                                                                 |
| ----------------------------- | ------------------------------------------------------------------------------------------- | -------------- | ---------------------------------------------------------------------------------------------------------------------- |
| Etiqueta `v1.0.0`             | Etiqueta anotada sobre `7f60608`: «Primera versión publicable de la API de tareas».         | Roxana Bareiro | [v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/tree/v1.0.0)                                                   |
| Release `v1.0.0`              | _v1.0.0 — Primera versión publicable_, con notas generadas automáticamente.                 | OlgaTYP        | [Release v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/releases/tag/v1.0.0)                                   |
| Paquete `practicas-continuas` | Imagen Docker publicada en GHCR con los tags `sha-7f60608`, `latest`, `1.0.0`, `1.0` y `1`. | GitHub Actions | [ghcr.io/olgatyp/practicas-continuas](https://github.com/users/OlgaTYP/packages/container/package/practicas-continuas) |

## Detalle por commit

### 1. `ci: Añadir release - workflow`

- **Qué se hizo:** se creó `.github/workflows/release.yml` con el job `publish`. El job hace checkout, inicia sesión en `ghcr.io` con el `GITHUB_TOKEN`, calcula los tags de la imagen con `docker/metadata-action` y construye y sube la imagen con `docker/build-push-action`. Se dispara con cada push a `main` y con cada etiqueta `v*.*.*`.
- **Autor:** OlgaTYP. El PR #21 lo abrió rmbgeuskadi, lo aprobó OlgaTYP y lo fusionó rmbgeuskadi.
- **Relación con el boletín:** Parte A.
- **Uso de IA:** no se ha utilizado IA

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

_Pendiente de realizar._

## Capturas de pantalla y otras evidencias

- Ejecuciones del workflow `Release`: [push a main](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40214227581) y [etiqueta v1.0.0](https://github.com/OlgaTYP/Practicas-continuas/actions/runs/40215844961).
- Paquete en GHCR con los tags `latest`, `sha-7f60608`, `1.0.0`, `1.0` y `1`: [ghcr.io/olgatyp/practicas-continuas](https://github.com/users/OlgaTYP/packages/container/package/practicas-continuas).
- Release: [v1.0.0 — Primera versión publicable](https://github.com/OlgaTYP/Practicas-continuas/releases/tag/v1.0.0).
- Las salidas de terminal de los _digests_ (Parte B) y de la verificación de la imagen con `docker pull`, `docker ps` y `curl` (Parte C) están incluidas como texto en sus apartados.
