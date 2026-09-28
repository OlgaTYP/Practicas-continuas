# Guía de contribución

Gracias por interesarte en contribuir a este proyecto. Este repositorio contiene una API REST en Java/Spring Boot para gestionar tareas, con validación, manejo centralizado de errores y pruebas unitarias.

Este documento resume todo lo que un colaborador necesita saber para trabajar en el proyecto de forma segura y consistente.

## 1. Requisitos previos

Antes de empezar, asegúrate de tener instalado lo siguiente:

- Java 21
- Maven 3.9 o superior
- Git
- Un editor o IDE compatible con Java (IntelliJ IDEA, VS Code con Java, Eclipse, etc.)

Para comprobar la versión instalada:

```bash
git --version
java -version
mvn -version
```

## 2. Clonar y preparar el repositorio

```bash
git clone <url-del-repositorio>
cd todo-api
```

A continuación, activa los hooks del repositorio para que el formateo se ejecute automáticamente antes de cada commit:

```bash
git config core.hooksPath .githooks
```

Esto hace que el hook local en `.githooks/pre-commit` se ejecute al hacer commit y aplique el formato con Spotless antes de guardar cambios.

> Si el hook no se ejecuta en sistemas Unix/Linux/macOS, asegúrate de que el archivo tenga permisos ejecutables:
>
> ```bash
> chmod +x .githooks/pre-commit
> ```

## 3. Configuración del entorno de desarrollo

El proyecto usa Maven y Spring Boot. La configuración principal está en `pom.xml`.

### Dependencias principales

- Spring Boot 3.3.4
- Spring Web
- Spring Validation
- Spring Boot Test
- Spotless para formateo automático

### Compilar el proyecto

```bash
mvn clean package
```

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API queda disponible por defecto en:

```text
http://localhost:8080
```

### Ejecutar pruebas

```bash
mvn test
```

### Validar formateo

```bash
mvn spotless:check
```

Si necesitas corregir el formato sin hacer commit:

```bash
mvn spotless:apply
```

## 4. Cómo contribuir

Se recomienda seguir este flujo:

1. Crear una rama a partir de `main`.
2. Trabajar en un cambio concreto y acotado.
3. Ejecutar pruebas y validaciones.
4. Comprobar que el código queda formateado.
5. Abrir un Pull Request describiendo claramente el cambio.
6. Esperar revisión y aprobación.

### Convención de ramas

Usa nombres claros y consistentes:

- `feature/nombre-de-la-funcionalidad`
- `fix/correccion-de-bug`
- `docs/mejora-documentacion`
- `refactor/nombre-del-refactor`

Ejemplo:

```bash
git checkout -b feature/add-task-priority-validation
```

### Convención de commits

Se recomienda mensajes cortos y descriptivos, por ejemplo:

- `feat: añade validación de prioridad`
- `fix: corrige error de binding en PUT /tasks/{id}`
- `docs: actualiza guía de contribución`
- `test: añade pruebas para servicio de tareas`
- `chore: aplica formateo con Spotless`

## 5. Política de fusión

Se sigue una política de integración conservadora:

- No se hace push directo a `main`.
- Todo cambio debe llegar mediante Pull Request.
- El PR debe incluir una descripción del problema y la solución.
- Deben pasar las comprobaciones relevantes del proyecto.
- El código debe quedar formateado correctamente.
- La rama debe estar actualizada respecto a `main` antes de fusionar.
- La fusión debe hacerse con revisión previa de otra persona del equipo o del mantenedor.

### Política recomendada

- Preferiblemente, usar merge commit o squash merge según lo que convenga al historial del repositorio.
- Evitar cambios de alcance amplio en un único PR si se puede descomponer en varios.
- Si un PR requiere más de una revisión, se recomienda dejar comentarios claros y específicos.

## 6. Requisitos para un Pull Request

Antes de abrir un PR, asegúrate de que:

- La rama está actualizada con `main`.
- El código compila correctamente.
- Las pruebas pasan.
- El formato está aplicado.
- La descripción del PR es clara.
- Se adjuntan enlaces o referencias relevantes si hay issues relacionados.

El repositorio incluye una plantilla en `.github/pull_request_template.md` que debes usar para documentar el PR.

## 7. Revisión de código

Durante la revisión se debe verificar:

- Que el cambio cumple el objetivo descrito.
- Que la lógica es correcta y no rompe el comportamiento existente.
- Que hay pruebas suficientes para la parte modificada.
- Que el código sigue los estándares del proyecto.
- Que no se introducen errores de validación o regresiones.

Las revisiones deben ser constructivas y centradas en el cambio, no en la persona que lo ha escrito.

## 8. Hooks y calidad del repositorio

El proyecto incluye un hook local en `.githooks/pre-commit`.

El comportamiento del hook es:

```sh
#!/bin/sh
echo "Formatting with Spotless..."
mvn -q spotless:apply || exit 1
git add -u
```

Esto significa que, antes de cada commit, se ejecuta la formateación automática con Spotless y posteriormente se vuelven a añadir al índice los archivos modificados.

Este hook ayuda a evitar que código mal formateado llegue al repositorio.

## 9. Flujo recomendado para trabajar localmente

```bash
git checkout main
git pull --ff-only

git checkout -b feature/mi-cambio
# hacer cambios
mvn test
mvn spotless:check

git add .
git commit -m "feat: describe el cambio"
git push origin feature/mi-cambio
```

Luego se abre el Pull Request desde GitHub y se sigue el flujo de revisión.

## 10. Estructura del proyecto

```text
todo-api/
├── src/
│   ├── main/java/
│   │   └── com/example/todoapi/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── exception/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── test/java/
├── .githooks/
├── .github/
├── pom.xml
├── README.md
├── CONTRIBUTING.md
└── docs/
```

## 11. Buenas prácticas

- Mantén los cambios pequeños y específicos.
- Haz commits con mensajes claros.
- No incluyas cambios no relacionados en el mismo PR.
- Añade o actualiza pruebas cuando cambies el comportamiento.
- Si detectas un bug, documenta su causa y su corrección.
- No fuerces cambios de estilo que no formen parte del problema que estás resolviendo.

## 12. Contacto y soporte

Si tienes dudas sobre la estructura del proyecto, los endpoints, la validación o el proceso de PR, consulta primero:

- el README del proyecto,
- la documentación de la API,
- la plantilla de Pull Request,
- y el historial de commits para ver ejemplos recientes.

Gracias por colaborar y ayudar a mantener la calidad del proyecto.

