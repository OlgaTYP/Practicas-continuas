# Boletín 1: Git y proyecto Maven

## 1. Resumen de commits

| # | Mensaje del commit | Qué se hizo | Autor | Enlace |
|---|--------------------|-------------|-------|--------|
| 1 | `chore: proyecto Maven inicial de la API de tareas` | Inicialización del proyecto Maven y estructura base de la API REST. | OlgaTYP | [1bb3b87](https://github.com/tu-usuario/todo-api/commit/1bb3b87) |
| 2 | `chore(editor): añade .editorconfig` | Añadido un archivo de configuración del editor para unificar indentación y finales de línea. | OlgaTYP | [943de0d](https://github.com/tu-usuario/todo-api/commit/943de0d) |
| 3 | `feat/descripcion-feat` | Creación de una rama de trabajo para incorporar cambios funcionales y documentales. | OlgaTYP | [665dc1a](https://github.com/tu-usuario/todo-api/commit/665dc1a) |
| 4 | `docs(api): añade endpoints y ejemplos JSON` | Documentación del arranque y de los endpoints de la API con ejemplos de uso. | OlgaTYP | [665dc1a](https://github.com/tu-usuario/todo-api/commit/665dc1a) |
| 5 | `chore(build): añade Spotless para formateo automático` | Integración de Spotless en Maven para comprobar y aplicar estilo Java automáticamente. | OlgaTYP | [feae1a1](https://github.com/tu-usuario/todo-api/commit/feae1a1) |
| 6 | `chore(hooks): añade hook de pre-commit con Spotless` | Configuración del hook local para ejecutar el formateo antes de cada commit. | OlgaTYP | [4b2d9fb](https://github.com/tu-usuario/todo-api/commit/4b2d9fb) |
| 7 | `test(hook): autoformatea con Spotless` | Verificación práctica del hook y comprobación del formato en un commit real. | OlgaTYP | [d96108b](https://github.com/tu-usuario/todo-api/commit/d96108b) |
| 8 | `feat(readme): cambia titulo en rama A` | Cambio de la cabecera del README en una rama paralela para simular un conflicto. | OlgaTYP | [34e4555](https://github.com/tu-usuario/todo-api/commit/34e4555) |
| 9 | `feat(readme): cambia titulo en rama B` | Cambio del mismo punto del README en otra rama para provocar el conflicto de merge. | OlgaTYP | [a635cf6](https://github.com/tu-usuario/todo-api/commit/a635cf6) |
| 10 | `merge: resuelve conflicto en README` | Resolución del conflicto y cierre del merge con la versión final correcta. | OlgaTYP | [e00759c](https://github.com/tu-usuario/todo-api/commit/e00759c) |
| 11 | `docs(project): añade documentación final de la práctica` | Documentación final del proyecto, entregable formal de la práctica. | OlgaTYP | [9226c71](https://github.com/tu-usuario/todo-api/commit/9226c71) |

## 2. Detalle por commit

### 1. `chore: proyecto Maven inicial de la API de tareas`
- **Qué se hizo:** se inicializó el repositorio y se creó la estructura base con Maven y Spring Boot para una API REST de gestión de tareas.
- **Autor:** OlgaTYP
- **Relación con el boletín:** es el punto de partida de la práctica y corresponde a la Parte A y a la Parte B del enunciado.
- **Uso de IA:** GitHub Copilot. Se usó para generar la estructura del proyecto y la base del dominio de la API.

### 2. `chore(editor): añade .editorconfig`
- **Qué se hizo:** se configuró un estilo uniforme para el editor, especialmente para la indentación y las líneas finales.
- **Autor:** OlgaTYP
- **Relación con el boletín:** forma parte de la Parte D y ayuda a evitar problemas de formato entre sistemas operativos.
- **Uso de IA:** no se utilizó IA en esta tarea concreta.

### 3. `docs(api): añade endpoints y ejemplos JSON`
- **Qué se hizo:** se documentó el arranque de la aplicación, la URL base y los endpoints con ejemplos de peticiones y respuestas JSON.
- **Autor:** OlgaTYP
- **Relación con el boletín:** responde a la Parte C, donde se demuestra cómo arrancar la API y validar los endpoints en local.
- **Uso de IA:** GitHub Copilot. Se pidió ayuda para redactar una documentación clara y consistente con la API creada.

### 4. `chore(build): añade Spotless para formateo automático`
- **Qué se hizo:** se añadió el plugin de Spotless al `pom.xml` para validar y estandarizar el código Java.
- **Autor:** OlgaTYP
- **Relación con el boletín:** corresponde a la Parte E y supone la automatización del formato en el proyecto.
- **Uso de IA:** GitHub Copilot. Se pidió ayuda para integrar la dependencia y la configuración del plugin.

### 5. `chore(hooks): añade hook de pre-commit con Spotless`
- **Qué se hizo:** se creó el hook en `.githooks/pre-commit` para que se ejecute `mvn -q spotless:apply` antes de cada commit.
- **Autor:** OlgaTYP
- **Relación con el boletín:** está directamente relacionado con la parte de automatización del formateo y con la prevención de commits con código sin limpiar.
- **Uso de IA:** GitHub Copilot. Se utilizó para verificar la configuración del hook en Git.

### 6. `test(hook): autoformatea con Spotless`
- **Qué se hizo:** se provocó un desorden intencionado en la indentación para comprobar que el hook reformateaba antes del commit.
- **Autor:** OlgaTYP
- **Relación con el boletín:** es la validación práctica de la Parte E y demuestra que el mecanísmo de pre-commit funciona realmente.
- **Uso de IA:** no se utilizó IA en esta comprobación funcional.

### 7. `feat(readme): cambia titulo en rama A`
- **Qué se hizo:** se modificó la cabecera del README en una rama paralela.
- **Autor:** OlgaTYP
- **Relación con el boletín:** se realizó con el objetivo de generar un conflicto de merge y practicar la Parte F.
- **Uso de IA:** no se utilizó IA.

### 8. `feat(readme): cambia titulo en rama B`
- **Qué se hizo:** se produjo una segunda modificación en la misma línea del README, en otra rama, para provocar un conflicto real de merge.
- **Autor:** OlgaTYP
- **Relación con el boletín:** esta rama es la que desencadenó el conflicto de Git de la Parte F.
- **Uso de IA:** no se utilizó IA.

### 9. `merge: resuelve conflicto en README`
- **Qué se hizo:** se editó manualmente el archivo conflictivo y se cerró el merge con un commit final limpio.
- **Autor:** OlgaTYP
- **Relación con el boletín:** corresponde directamente a la Parte F del boletín y a la resolución del conflicto en Git.
- **Uso de IA:** no se utilizó IA.

### 10. `docs(project): añade documentación final de la práctica`
- **Qué se hizo:** se redactó la documentación formal del proyecto y del proceso seguido durante la práctica.
- **Autor:** OlgaTYP
- **Relación con el boletín:** consolida la entrega final del boletín y recoge todo el trabajo realizado.
- **Uso de IA:** GitHub Copilot. Se usó para estructurar y redactar una versión final clara, ordenada y formal.

## 3. Descripción narrativa de lo hecho en la sesión y relación con los commits

### Parte A — Instalación y verificación del entorno

La primera parte del trabajo consistió en comprobar que el entorno de desarrollo estaba correctamente preparado. Se verificó la presencia de Git, Java y Maven, y se configuró la identidad del usuario para que los commits tuviesen un autor claro. Además, se estableció una política de pull segura y se fijó la rama principal en `main`, lo que ayuda a mantener un flujo de trabajo más ordenado y consistente.

Este punto es esencial porque, aunque parezca preliminar, determina que todas las operaciones posteriores de Git, compilación y ejecución se realicen sobre un entorno estable y reproducible.

### Parte B — Generación de la aplicación con ayuda de IA

A continuación se generó la estructura inicial de la API REST con Spring Boot y Maven. La idea era crear una aplicación no trivial de gestión de tareas, con entidad, validaciones, operaciones CRUD, manejo de errores y separación por capas. Aunque el proyecto se desarrolló bajo una estructura de backend bastante clásica, la parte importante aquí fue entender el alcance del dominio y dejarlo preparado para pruebas y validación local.

La IA fue útil para acelerar la creación de la base del proyecto y para organizar el código siguiendo patrones habituales de Spring, pero siempre con revisión manual posterior para asegurar que la solución fuese coherente con los requisitos del boletín.

### Parte C — Construcción y pruebas en local

Una vez creada la base del proyecto, se ejecutó la compilación con Maven y se comprobó que el artefacto generaba un JAR ejecutable. A continuación se levantó la aplicación y se probó la API con peticiones HTTP para comprobar que los endpoints respondían correctamente y que la API se encontraba en funcionamiento.

Este paso fue importante porque cerró el ciclo de “generar → compilar → arrancar → probar”, y además permitió detectar problemas de validación y de binding antes de continuar con el control de versiones y el formateo.

### Parte D — Control de versiones con Git

La siguiente etapa se centró en la organización del repositorio. Se inicializó Git, se añadieron archivos básicos como `.gitignore` y `.gitattributes`, y se realizaron los commits iniciales de la estructura del proyecto y del estilo del editor. Más adelante se creó una rama de trabajo para hacer cambios en el código y se integró esa rama en `main`, con el historial de commits visible como evidencia del proceso.

Este bloque fue clave para aplicar buenas prácticas de Git y para comprender cómo todo el trabajo se va registrando en el historial del repositorio, además de permitir un flujo de trabajo estable en equipo.

### Parte E — Formateo automático en cada commit

En esta fase se añadió Spotless al proyecto para que el estilo Java fuese uniforme y verificable automáticamente. La comprobación con `mvn spotless:check` y la corrección con `mvn spotless:apply` permitieron adoptar una calidad mínima de código que luego se convierten en barrera del pipeline de CI.

Posteriormente se configuró el hook de pre-commit para que el formateo se ejecutase de forma automática antes de confirmar cambios. Esta práctica es muy útil porque impide cometer código sucio o desorganizado, y reforzó la disciplina de calidad en el proyecto.

### Parte F — Resolución de conflicto de merge

La última parte del boletín se centró en la resolución de conflictos en Git. Se crearon dos ramas distintas sobre la misma línea del README y se provocó un conflicto de merge de forma intencionada. La resolución se hizo de forma manual eliminando los marcadores del conflicto y dejando la versión final correcta.

Este ejercicio fue muy útil porque refleja un escenario real de trabajo colaborativo: dos personas cambian la misma línea y Git necesita que el desarrollador resuelva la discrepancia con criterio técnico.

## 4. Capturas de pantalla y evidencias visuales

La evidencia de la práctica quedó reflejada principalmente en la consola y en el historial de Git, por lo que no fue necesario añadir capturas de pantalla de los cambios en el código. Sin embargo, los resultados relevantes fueron:

- salida de `git --version`, `java -version` y `mvn -version`
- ejecución satisfactoria de `mvn clean package`
- arranque de la aplicación con `java -jar target/*.jar`
- salida de `mvn spotless:check` con formato correcto
- mensaje del hook ejecutándose en commit
- historial final con merges y conflictos resueltos

Dado que la práctica se han validado principalmente con comandos de consola y commits reales, las evidencias quedan integradas en la historia del repositorio y en la propia ejecución del proyecto.

## 5. Conclusión

El Boletín 1 ha servido para poner en práctica los pilares básicos del trabajo con proyectos Java y Git: preparación del entorno, generación del proyecto, validación local, control de versiones, automatización del estilo y resolución de conflictos. La combinación de estas tareas refleja un flujo de trabajo muy cercano al desarrollo profesional, y permite dejar el repositorio en un estado ordenado, reproducible y listo para seguir ampliando la aplicación.

Además, la integración de la IA en la generación del proyecto y la documentación ha permitido acelerar el proceso, siempre con revisión humana y criterio técnico para asegurar que el resultado cumpla los requisitos de la práctica.
