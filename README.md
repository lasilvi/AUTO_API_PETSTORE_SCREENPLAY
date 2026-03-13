# AUTO API PETSTORE SCREENPLAY

Proyecto de automatización de pruebas API usando **Serenity BDD + Cucumber + Java + Screenplay Pattern**.

## 1. Descripción del proyecto

Este proyecto automatiza el ciclo CRUD del recurso **Pet** de la API pública de PetStore.

- Qué hace:
  - Crea, consulta, actualiza y elimina mascotas mediante servicios REST.
- Qué tipo de pruebas automatiza:
  - Pruebas funcionales API orientadas a comportamiento (BDD).
  - Escenarios E2E del flujo CRUD.
- Tecnologías principales:
  - Java 17
  - Serenity BDD
  - Cucumber
  - JUnit 4
  - Gradle
  - Screenplay Pattern

## 2. Requisitos previos

- JDK 17 instalado y configurado en `JAVA_HOME`.
- Gradle (opcional, porque se incluye wrapper).
- Conexión a internet para consumir la API de PetStore.

## 3. Estructura del proyecto

Estructura típica orientada a Screenplay:

```text
src
├── main
└── test
    ├── java
    │   ├── tasks
    │   ├── interactions
    │   ├── questions
    │   ├── userinterfaces
    │   ├── stepdefinitions
    │   └── runners
    └── resources
        └── features
```

Cómo se interpreta cada carpeta en Screenplay:

- `features`:
  - Contiene archivos `.feature` con escenarios Gherkin.
  - Define el comportamiento esperado en lenguaje de negocio.
  - Es el punto de entrada del flujo BDD.

- `stepdefinitions`:
  - Contiene el mapeo entre pasos Gherkin y código Java.
  - Orquesta el actor y delega en Tasks/Questions.
  - En Screenplay, evita lógica compleja y actúa como capa de traducción.

- `runners`:
  - Configura ejecución de Cucumber con Serenity (`@RunWith`, `@CucumberOptions`).
  - Define rutas de features, glue, tags y plugins.
  - Dispara la ejecución completa de escenarios.

- `tasks`:
  - Contiene acciones de negocio de alto nivel que realiza el actor.
  - Ejemplo en API: crear, consultar, actualizar o eliminar una mascota.
  - Es la unidad principal de comportamiento reutilizable en Screenplay.

- `interactions`:
  - Contiene acciones técnicas de bajo nivel reutilizables.
  - Ejemplo: construir headers comunes, autenticar, transformar payloads.
  - Una Task puede componer varias Interactions.

- `questions`:
  - Contiene verificaciones que el actor usa para validar resultados.
  - Ejemplo: obtener código HTTP o validar un campo del response body.
  - En Screenplay, separa claramente acción (Task) de validación (Question).

- `userinterfaces`:
  - En UI tests define locators/páginas.
  - En pruebas API suele estar vacío o no aplicarse.
  - Se mantiene por consistencia de arquitectura Screenplay.



## 4. Flujo de ejecución de una prueba

Flujo paso a paso:

1. **Feature file**
   - Cucumber lee el escenario en Gherkin (por ejemplo, CRUD de mascota).

2. **Step Definitions**
   - Cada paso (`Given/When/Then`) se enlaza a un método Java.

3. **Actor**
   - Se crea el actor de Screenplay (ejemplo: `Tester`).
   - El actor ejecuta acciones con `attemptsTo(...)`.

4. **Task**
   - El actor ejecuta una Task de negocio, por ejemplo `CreatePet.with(pet)`.
   - La Task llama al endpoint correspondiente.

5. **Interaction** (cuando aplica)
   - Una Task puede apoyarse en Interactions para pasos técnicos reutilizables.

6. **Question**
   - El actor responde preguntas para validar resultados esperados.
   - También puede validarse con `SerenityRest.lastResponse()`.

7. **Reporte de Serenity**
   - Serenity consolida evidencia, resultados y trazabilidad del escenario.



## 5. Cómo ejecutar las pruebas

Con Gradle (recomendado en este proyecto):

```bash
gradle clean test
```

Con Gradle Wrapper:

```bash
./gradlew clean test
```

En Windows:

```bat
gradlew.bat clean test
```

Comando equivalente en proyectos Maven:

```bash
mvn clean verify
```

## 6. Reportes

Al finalizar la ejecución, Serenity genera reportes HTML con detalle de escenarios, pasos, estado y evidencia.

Ubicaciones comunes en este proyecto:

- `target/site/serenity/index.html`
- `gradle-build/reports/tests/test/index.html`
- `gradle-build/cucumber-reports/cucumber.html`

Recomendación:


---

Si deseas, puedo añadir una sección corta de **convenciones de código Screenplay** (nombres de Tasks, manejo de datos de prueba, y estrategia de assertions) para estandarizar el equipo.