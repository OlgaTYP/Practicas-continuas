# Boletín 2 — GitHub y colaboración con Pull Requests

> Memoria de la práctica 2. Pareja: Olga Yanes ([@OlgaTYP](https://github.com/OlgaTYP)) y Roxana Bareiro ([@rmbgeuskadi](https://github.com/rmbgeuskadi)).
> Repositorio: <https://github.com/OlgaTYP/Practicas-continuas>

## 1. Resumen de commits

### 1.1 Gobernanza del repositorio (Parte B)

| #   | Mensaje del commit                                   | Qué se hizo                                                                 | Autor                        | Enlace                                                                   |
| --- | ---------------------------------------------------- | --------------------------------------------------------------------------- | ---------------------------- | ------------------------------------------------------------------------ |
| 1   | `Creada la plantilla de pull_request_template.md`    | Plantilla de Pull Request.                                                  | Olga Yanes (OlgaTYP)         | [a3a846d](https://github.com/OlgaTYP/Practicas-continuas/commit/a3a846d) |
| 2   | `Modificacion de la plantilla pull_request_template` | Ajuste de la plantilla al formato del enunciado.                            | Olga Yanes (OlgaTYP)         | [f7cc2b8](https://github.com/OlgaTYP/Practicas-continuas/commit/f7cc2b8) |
| 3   | `Create enhancement.md`                              | Plantilla de Issue para mejoras.                                            | Roxana Bareiro (rmbgeuskadi) | [f3596c2](https://github.com/OlgaTYP/Practicas-continuas/commit/f3596c2) |
| 4   | `Create bug.md`                                      | Plantilla de Issue para errores.                                            | Roxana Bareiro (rmbgeuskadi) | [20be67c](https://github.com/OlgaTYP/Practicas-continuas/commit/20be67c) |
| 5   | `Create CODEOWNERS`                                  | Propietarios de código del proyecto.                                        | Roxana Bareiro (rmbgeuskadi) | [3958190](https://github.com/OlgaTYP/Practicas-continuas/commit/3958190) |
| 6   | `Create CONTRIBUTING.md`                             | Primera versión de la guía de contribución.                                 | Roxana Bareiro (rmbgeuskadi) | [c129fc4](https://github.com/OlgaTYP/Practicas-continuas/commit/c129fc4) |
| 7   | `Creación de plantilla CONTRIBUTING.md`              | Guía completa: entorno, hooks, pruebas, ramas, commits, revisión y fusión.  | Olga Yanes (OlgaTYP)         | [97ca45e](https://github.com/OlgaTYP/Practicas-continuas/commit/97ca45e) |
| 8   | `Modificación del archivo CODEOWNERS`                | Propietarios por ruta (capas de `src/`, tests, documentación y `.github/`). | Olga Yanes (OlgaTYP)         | [ef88541](https://github.com/OlgaTYP/Practicas-continuas/commit/ef88541) |
| 9   | `Add enhancement request template in Spanish`        | Plantilla de mejora traducida, con etiqueta `enhancement`.                  | Roxana Bareiro (rmbgeuskadi) | [a1bedca](https://github.com/OlgaTYP/Practicas-continuas/commit/a1bedca) |
| 10  | `Add Spanish bug report template`                    | Plantilla de bug traducida, con etiqueta `bug`.                             | Roxana Bareiro (rmbgeuskadi) | [dbd7f78](https://github.com/OlgaTYP/Practicas-continuas/commit/dbd7f78) |
| 11  | `Modifico plantillas enhancement.md y bug.md`        | Revisión final de las dos plantillas de Issue.                              | Roxana Bareiro (rmbgeuskadi) | [4f3d91f](https://github.com/OlgaTYP/Practicas-continuas/commit/4f3d91f) |

### 1.2 Pull Requests (Partes D, E y F)

| PR                                                                                                              | Issue                                                         | Commits                                                                                                                                                                                                                      | Qué se hizo                                                                                  | Autoría                                              | Revisión                          | Merge                                                                    |
| --------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | ---------------------------------------------------- | --------------------------------- | ------------------------------------------------------------------------ |
| [#3](https://github.com/OlgaTYP/Practicas-continuas/pull/3) (fork)                                              | [#2](https://github.com/OlgaTYP/Practicas-continuas/issues/2) | [555d05e](https://github.com/OlgaTYP/Practicas-continuas/commit/555d05e), [ea0ca83](https://github.com/OlgaTYP/Practicas-continuas/commit/ea0ca83), [2f23cd9](https://github.com/OlgaTYP/Practicas-continuas/commit/2f23cd9) | Filtro `GET /api/tasks?status=...`, sus tests y un ajuste de formato con Spotless.           | Maria Ballester y Lucía Olmos Martínez (otra pareja) | OlgaTYP y rmbgeuskadi: _Approved_ | [9811c38](https://github.com/OlgaTYP/Practicas-continuas/commit/9811c38) |
| [#6](https://github.com/OlgaTYP/Practicas-continuas/pull/6)                                                     | [#5](https://github.com/OlgaTYP/Practicas-continuas/issues/5) | [0c61248](https://github.com/OlgaTYP/Practicas-continuas/commit/0c61248), [005fbd6](https://github.com/OlgaTYP/Practicas-continuas/commit/005fbd6)                                                                           | Búsqueda por título (`?title=...`), parcial y sin distinguir mayúsculas.                     | Olga Yanes                                           | rmbgeuskadi: _Approved_           | [e25a2e4](https://github.com/OlgaTYP/Practicas-continuas/commit/e25a2e4) |
| [#7](https://github.com/OlgaTYP/Practicas-continuas/pull/7)                                                     | [#4](https://github.com/OlgaTYP/Practicas-continuas/issues/4) | [52ab9ad](https://github.com/OlgaTYP/Practicas-continuas/commit/52ab9ad)                                                                                                                                                     | Filtro por prioridad (`?priority=...`), combinable con el estado.                            | Roxana Bareiro                                       | OlgaTYP: _Approved_               | [0e679cc](https://github.com/OlgaTYP/Practicas-continuas/commit/0e679cc) |
| [#8](https://github.com/OlgaTYP/Practicas-continuas/pull/8)                                                     | —                                                             | [4351edd](https://github.com/OlgaTYP/Practicas-continuas/commit/4351edd)                                                                                                                                                     | Primera rama del conflicto: prioridad por defecto `HIGH`.                                    | Olga Yanes                                           | rmbgeuskadi: _Approved_           | [f8d93d9](https://github.com/OlgaTYP/Practicas-continuas/commit/f8d93d9) |
| [#9](https://github.com/OlgaTYP/Practicas-continuas/pull/9)                                                     | —                                                             | [f856bb3](https://github.com/OlgaTYP/Practicas-continuas/commit/f856bb3)                                                                                                                                                     | Segunda rama del conflicto: prioridad por defecto `LOW`; rebase sobre `main` y _force push_. | Roxana Bareiro                                       | OlgaTYP: _Approved_               | [8471b1e](https://github.com/OlgaTYP/Practicas-continuas/commit/8471b1e) |
| [mariaballesteer/Practicas-PC-parejas#9](https://github.com/mariaballesteer/Practicas-PC-parejas/pull/9) (fork) | #1 de su repo                                                 | —                                                                                                                                                                                                                            | Filtro por rango de fecha límite y ordenación del listado de tareas.                         | Olga Yanes                                           | mariaballesteer: _Approved_       | Fusionado por mariaballesteer                                            |

---

## 2. Detalle por commit

### 2.1 Gobernanza

#### `Creada la plantilla de pull_request_template.md` / `Modificacion de la plantilla pull_request_template`

- **Qué se hizo:** se creó `.github/pull_request_template.md` con los apartados del enunciado: _Qué hace este PR_ (con `Closes #`), _Cómo lo he probado_ (casillas de tests y prueba manual) y _Notas para quien revise_. GitHub carga la plantilla al abrir un PR sin importar si el nombre está en mayúsculas o minúsculas, así que `pull_request_template.md` funciona igual que `PULL_REQUEST_TEMPLATE.md`.
- **Autora:** Olga Yanes (OlgaTYP).
- **Relación con el boletín:** Parte B.
- **Uso de IA:** no se usó IA.

#### `Create enhancement.md` / `Create bug.md` / `Add ... in Spanish` / `Modifico plantillas enhancement.md y bug.md`

- **Qué se hizo:** se crearon `.github/ISSUE_TEMPLATE/enhancement.md` y `.github/ISSUE_TEMPLATE/bug.md`. Cada una tiene una cabecera YAML con `name`, `about`, `title` (`[Mejora] ` / `[Bug] `) y `labels`, de modo que la etiqueta `enhancement` o `bug` se asigna sola al crear el Issue. La plantilla de mejora pide la necesidad, la solución propuesta, alternativas y casos de uso; la de bug pide pasos de reproducción, comportamiento esperado y actual, entorno y evidencias. Después se tradujeron al español y se revisaron.
- **Autora:** Roxana Bareiro (rmbgeuskadi).
- **Relación con el boletín:** Parte B (plantillas y etiquetas de Issue).
- **Uso de IA:** no se usó IA.

#### `Create CODEOWNERS` / `Modificación del archivo CODEOWNERS`

- **Qué se hizo:** se creó `.github/CODEOWNERS`. Toda la aplicación (`*`) la revisan las dos integrantes, y además se afinó por rutas: `controller/` y `service/` → @OlgaTYP; `model/`, `exception/` y `repository/` → @rmbgeuskadi; tests, `docs/` y `.github/` → ambas; `README.md` y `CONTRIBUTING.md` → @OlgaTYP. Así, GitHub solicita automáticamente la revisión de la propietaria correspondiente en cada PR.
- **Autoras:** Roxana Bareiro (creación) y Olga Yanes (rutas específicas).
- **Relación con el boletín:** Parte B.
- **Uso de IA:** no se usó IA.

#### `Create CONTRIBUTING.md` / `Creación de plantilla CONTRIBUTING.md`

- **Qué se hizo:** se escribió `CONTRIBUTING.md` con requisitos previos, cómo clonar y preparar el repositorio, activación del hook (`git config core.hooksPath .githooks`), cómo compilar, ejecutar y probar, cómo validar el formato con Spotless, convención de ramas y de commits, política de fusión, requisitos de un PR y proceso de revisión.
- **Autoras:** Roxana Bareiro (primera versión) y Olga Yanes (versión completa).
- **Relación con el boletín:** Parte B.
- **Uso de IA:** en la versión completa se usó Copilot, pidiéndole que generase una descripción para el CONTRIBUTING.

### 2.2 Pull Requests

#### PR #3 — `feat(tarea): filtrar Get /api/tasks por estado` (desde fork)

- **Qué se hizo:** se añadió el parámetro opcional `status` a `GET /api/tasks`. Sin el parámetro, el endpoint devuelve todas las tareas como antes, y un estado inválido devuelve 400. Incluye tests del filtro y del estado inválido, y un commit de corrección de formato con Spotless.
- **Autoras:** Maria Ballester y Lucía Olmos Martínez (otra pareja), desde el fork `luciaolmosmartinez/Practicas-continuas`.
- **Issue:** [#2](https://github.com/OlgaTYP/Practicas-continuas/issues/2), cerrado con `Closes #2`.
- **Revisión:** aprobado por OlgaTYP y rmbgeuskadi; fusionado por rmbgeuskadi.
- **Relación con el boletín:** Parte F (PR desde fork recibido en nuestro repositorio).
- **Uso de IA:** se usó Copilot, pidiéndole que analizase el Issue y resolviese la funcionalidad sin modificar innecesariamente otras partes del código.

#### PR #6 — `feat: Añade búsqueda de tareas por título`

- **Qué se hizo:** se añadió el parámetro opcional `title` a `GET /api/tasks`. La búsqueda es parcial, no distingue mayúsculas y se combina con los demás filtros. Se añadieron tests y se documentó en el README.
- **Autora:** Olga Yanes (OlgaTYP). **Revisora:** Roxana Bareiro.
- **Issue:** [#5](https://github.com/OlgaTYP/Practicas-continuas/issues/5) (etiqueta `enhancement`).
- **Revisión:** rmbgeuskadi aprobó una primera versión. Mientras tanto se fusionó el PR #7 y aparecieron conflictos, así que se integró `main` en la rama ([005fbd6](https://github.com/OlgaTYP/Practicas-continuas/commit/005fbd6)). Ese nuevo commit **descartó automáticamente la aprobación anterior** (_review dismissed_), como establece la regla de protección. Tras revisar los archivos en conflicto, rmbgeuskadi volvió a aprobar y fusionó el PR. La rama se borró.
- **Relación con el boletín:** Parte D, primer ciclo.
- **Uso de IA:** se usó Copilot para proponer e implementar una mejora relacionada con la búsqueda de tareas.

#### PR #7 — `feat: Añade filtro por prioridad`

- **Qué se hizo:** se añadió el parámetro opcional `priority` (`LOW`, `MEDIUM`, `HIGH`, `URGENT`) a `GET /api/tasks`, combinable con `status`, con tests para valores válidos e inválidos.
- **Autora:** Roxana Bareiro (rmbgeuskadi). **Revisora:** Olga Yanes.
- **Issue:** [#4](https://github.com/OlgaTYP/Practicas-continuas/issues/4) (etiqueta `enhancement`).
- **Revisión:** aprobado por OlgaTYP, fusionado por rmbgeuskadi; la rama se borró.
- **Relación con el boletín:** Parte D, segundo ciclo, con los papeles intercambiados respecto al PR #6.
- **Uso de IA:** se usó Copilot para describir una mejora de filtros para la aplicación.

#### PR #8 — `test: Prepara conflicto de prioridad`

- **Qué se hizo:** en la rama `feat/conflicto-1` se cambió la prioridad por defecto de las tareas nuevas a `HIGH`.
- **Autora:** Olga Yanes. **Revisión:** aprobado por rmbgeuskadi y fusionado sin conflictos.
- **Relación con el boletín:** Parte E, primera rama.
- **Uso de IA:** no se usó IA.

#### PR #9 — `test: provoca conflicto de prioridad`

- **Qué se hizo:** en la rama `feat/conflicto-2`, creada desde el mismo punto, se cambió la misma línea a `LOW`. Al fusionarse el PR #8, GitHub marcó el PR #9 con conflicto. Se hizo rebase en local sobre `main`, se resolvió el conflicto y se subió con `git push --force-with-lease`; GitHub registró el evento _force-pushed_ y el PR quedó sin conflictos.
- **Autora:** Roxana Bareiro. **Revisión:** aprobado por OlgaTYP y fusionado por rmbgeuskadi.
- **Relación con el boletín:** Parte E, segunda rama y resolución.
- **Uso de IA:** no se usó IA.

---

## 3. Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Publicar el repositorio

Se creó en GitHub el repositorio público y vacío `OlgaTYP/Practicas-continuas`, sin README para evitar conflictos con el historial local. Olga Yanes, como creadora, dio de alta como colaboradora a Roxana Bareiro (@rmbgeuskadi) para que pudiera hacer push. Después se conectó el repositorio local del Boletín 1 y se subió el historial:

```bash
git remote add origin https://github.com/OlgaTYP/Practicas-continuas.git
git branch -M main
git push -u origin main
```

El `README.md` explica cómo poner todo en marcha desde cero: descripción, requisitos (Java 21, Maven), cómo construir (`mvn clean package`), cómo arrancar (`java -jar target/todo-api.jar`), cómo activar el hook de pre-commit del Boletín 1 (`git config core.hooksPath .githooks`) y la referencia de los endpoints.

### Parte B — Gobernanza del repositorio

Antes de abrir el primer PR se añadieron:

- `.github/pull_request_template.md`: plantilla que GitHub carga al abrir cada PR.
- `.github/ISSUE_TEMPLATE/enhancement.md` y `.github/ISSUE_TEMPLATE/bug.md`: las **plantillas de Issue** son ficheros Markdown con una cabecera YAML que GitHub muestra como opciones al pulsar _New issue_. Las **etiquetas** (_labels_) clasifican los Issues y PR (mejora, error…) y permiten filtrarlos. Con el campo `labels:` de la cabecera, cada plantilla asigna su etiqueta (`enhancement` o `bug`) automáticamente; por ejemplo, los Issues [#4](https://github.com/OlgaTYP/Practicas-continuas/issues/4) y [#5](https://github.com/OlgaTYP/Practicas-continuas/issues/5) se crearon con la plantilla de mejora y llevan la etiqueta `enhancement`.
- `.github/CODEOWNERS`: revisión global de ambas integrantes y propietarias específicas por ruta. Al abrir un PR, GitHub solicitó automáticamente la revisión de las propietarias.
- `CONTRIBUTING.md`: guía para quien quiera contribuir.

Tras subir estos cambios se comprobó que GitHub ofrecía las dos plantillas al crear un Issue, cargaba la plantilla en cada PR y asignaba revisoras según `CODEOWNERS`.

**Estrategia de fusión:** en _Settings → General → Pull Requests_ se puede activar o desactivar cada estrategia: _merge commit_, _squash and merge_ y _rebase and merge_. Se eligió **merge commit**, porque conserva los commits de la rama y añade un commit de merge (`Merge pull request #N ...`) que deja visible en el historial cuándo se integró cada PR. Todos los PR del boletín se fusionaron así (ver los commits de merge de la tabla 1.2).

### Parte C — Proteger la rama main

En _Settings → Branches_ se añadió una regla de protección para `main` con:

- _Require a pull request before merging_.
- _Require approvals_: al menos 1 aprobación.
- _Require review from Code Owners_.
- _Require conversation resolution before merging_.
- _Dismiss stale pull request approvals when new commits are pushed_.
- _Do not allow bypassing the above settings_.
- Sin _force pushes_ ni eliminación de `main`.

**Qué se consigue:** ningún cambio llega a `main` sin pasar por un PR revisado y aprobado por una propietaria del código. Las conversaciones abiertas bloquean la fusión, así que ningún comentario de revisión queda sin atender. Si se suben commits después de aprobar, la aprobación se descarta y hay que revisar de nuevo lo que realmente se va a fusionar; esto ocurrió en el PR #6. Las reglas se aplican también a las administradoras, y nadie puede reescribir ni borrar el historial de `main`.

**Prueba:** se intentó un push directo a `main`:

```bash
git switch main
echo "prueba" >> README.md
git commit -am "chore: intento de push directo"
git push          # rechazado por el servidor
```

El commit se creó en local, pero GitHub rechazó el push porque la rama está protegida y los cambios deben llegar mediante Pull Request.

### Parte D — Trabajo por Pull Requests

Se completaron dos ciclos completos, cada uno con una mejora real de la API e intercambiando los papeles:

| Ciclo | Issue                                                                                      | Rama                       | PR                                                          | Abre        | Revisa      |
| ----- | ------------------------------------------------------------------------------------------ | -------------------------- | ----------------------------------------------------------- | ----------- | ----------- |
| 1     | [#5](https://github.com/OlgaTYP/Practicas-continuas/issues/5) Buscar tareas por título     | `feat/busqueda-por-titulo` | [#6](https://github.com/OlgaTYP/Practicas-continuas/pull/6) | OlgaTYP     | rmbgeuskadi |
| 2     | [#4](https://github.com/OlgaTYP/Practicas-continuas/issues/4) Filtrar tareas por prioridad | `feat/filtro-prioridad`    | [#7](https://github.com/OlgaTYP/Practicas-continuas/pull/7) | rmbgeuskadi | OlgaTYP     |

En cada ciclo se creó el Issue con la plantilla de mejora y su etiqueta, una rama `feat/...`, la implementación (con ayuda de Copilot) y sus tests, el push de la rama y el PR con la plantilla y `Closes #N`. Después venían la revisión, la fusión con _merge commit_ y el borrado de la rama. Al fusionarse, cada PR cerró automáticamente su Issue.

En el PR #6 hubo además una ronda de corrección: tras fusionarse el PR #7 hubo que integrar `main` en la rama, la aprobación previa se descartó y se volvió a revisar antes de fusionar.

### Parte E — Provocar un conflicto en un PR

1. Desde el mismo commit de `main` se crearon `feat/conflicto-1` (prioridad por defecto `HIGH`) y `feat/conflicto-2` (prioridad por defecto `LOW`), que modificaban la misma línea.
2. La primera se fusionó vía [PR #8](https://github.com/OlgaTYP/Practicas-continuas/pull/8).
3. Al abrir el [PR #9](https://github.com/OlgaTYP/Practicas-continuas/pull/9) con la segunda rama, GitHub indicó que había conflictos con `main` y no permitía fusionar.
4. En local se hizo rebase de la segunda rama sobre `main`, se resolvió el conflicto y se subió la rama reescrita:

   ```bash
   git switch feat/conflicto-2
   git fetch origin
   git rebase origin/main        # conflicto en la misma línea
   # editar el archivo, quitar los marcadores y dejar la versión final
   git add .
   git rebase --continue
   git push --force-with-lease
   ```

5. GitHub actualizó el PR automáticamente (evento _force-pushed_) y el conflicto desapareció. En el historial se ve que el commit [f856bb3](https://github.com/OlgaTYP/Practicas-continuas/commit/f856bb3) ya parte del merge del PR #8 ([f8d93d9](https://github.com/OlgaTYP/Practicas-continuas/commit/f8d93d9)). Tras la aprobación se fusionó.

```text
*   8471b1e Merge pull request #9 from OlgaTYP/feat/conflicto-2
|\
| * f856bb3 test: provoca conflicto de prioridad
|/
*   f8d93d9 Merge pull request #8 from OlgaTYP/feat/conflicto-1
|\
| * 4351edd test: Prepara conflicto de prioridad
|/
*   e25a2e4 Merge pull request #6 from OlgaTYP/feat/busqueda-por-titulo
```

`--force-with-lease` es necesario porque el rebase reescribe la rama ya publicada. A diferencia de `--force`, rechaza el push si alguien ha subido commits nuevos a la rama remota entretanto. La protección de `main` no afecta, porque el _force push_ se hace sobre la rama de feature.

### Parte F — PR con fork

**PR recibido en nuestro repositorio.** Abrimos el Issue [#2](https://github.com/OlgaTYP/Practicas-continuas/issues/2) (filtrar tareas por estado) y la otra pareja (Maria Ballester y Lucía Olmos Martínez), sin permisos de escritura en nuestro repositorio, lo resolvió desde su fork con el [PR #3](https://github.com/OlgaTYP/Practicas-continuas/pull/3). Lo aprobamos las dos y se fusionó.

**PR enviado al repositorio de la otra pareja.** Olga Yanes hizo un fork de [mariaballesteer/Practicas-PC-parejas](https://github.com/mariaballesteer/Practicas-PC-parejas) y resolvió su Issue #1 con el PR **[mariaballesteer/Practicas-PC-parejas#9](https://github.com/mariaballesteer/Practicas-PC-parejas/pull/9)** — _feat: filtrar y ordenar tareas por fecha y prioridad_. El PR amplía el listado de tareas para filtrar por rango de fecha límite y ordenar los resultados, manteniendo los filtros existentes por estado y prioridad. Fue **aprobado y fusionado por @mariaballesteer**. Antes hubo un primer intento ([#8](https://github.com/mariaballesteer/Practicas-PC-parejas/pull/8)) que se cerró sin fusionar y se sustituyó por el #9.

---

## 4. Evidencias (capturas)

Las capturas se entregan en imágenes junto a esta memoria:

1. Intento de push directo a `main` rechazado por GitHub (Parte C).
2. Regla de protección de `main` configurada (Parte C).
3. PR #9 abierto con el aviso de conflicto con `main` (Parte E).
4. Resolución del conflicto en local (rebase y `git push --force-with-lease`) (Parte E).
5. PR #9 actualizado automáticamente y sin conflictos (Parte E).
6. PR [mariaballesteer/Practicas-PC-parejas#9](https://github.com/mariaballesteer/Practicas-PC-parejas/pull/9) abierto y aprobado en el repositorio de la otra pareja (Parte F).

---

## 5. Uso de IA en la elaboración de la memoria

La memoria se elaboró con ayuda de ChatGPT por las dos integrantes, con el siguiente prompt:

> «Redacta el Boletín 02 en `boletines/boletin2.md` siguiendo la plantilla proporcionada y las instrucciones del Boletín 2».
