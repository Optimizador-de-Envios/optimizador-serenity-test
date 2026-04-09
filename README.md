# Optimizador de Envíos — Suite de Pruebas E2E con Serenity BDD

**Proyecto:** Optimizador de Envíos 
**Developer:** [Santiago Angarita Avila](https://github.com/sanavi01)
**QA Engineer:** [Nahuel Lemes](https://github.com/nahulemesf)  
**Ambiente:** QA (`http://localhost` vía Nginx proxy · frontend en `http://localhost/app`)  
**Framework:** Serenity BDD 4.x · Cucumber JUnit Platform · Selenium WebDriver (Chrome)  
**Estado final del suite:** ✅ BUILD SUCCESS — 18 escenarios pasados, 0 fallidos (1 ignorado con `@ignore`)

---

## Implementación del patrón Screenplay

Las pruebas siguen el patrón **Screenplay**, que organiza la automatización en torno a **actores** que realizan **tareas** (`Task`) y formulan **preguntas** (`Question`) sobre el estado del sistema, en lugar de depender directamente de Page Objects.

### Estructura de capas

| Capa | Carpeta | Responsabilidad |
|---|---|---|
| **Actors** | `stepdefinitions/` | Orquestan el flujo: `theActorCalled(...).attemptsTo(...)` |
| **Tasks** | `tasks/` | Acciones compuestas reutilizables (ej. `LoginAs`, `RegisterShipment`) |
| **Questions** | `questions/` | Verificaciones sobre el estado de la UI (ej. `TheConfirmationPage.isVisible()`) |
| **UI Targets** | `ui/` | Localizadores de elementos encapsulados como `Target` estáticos |
| **Hooks** | `hooks/` + `stepdefinitions/hooks/` | Setup del escenario (`OnStage`) y apertura del navegador |
| **Util** | `util/` | Constantes y datos de prueba centralizados (`TestData`) |

### Flujo típico de un escenario

```
Step Definition
  └── attemptsTo(Task)
        └── Task interactúa con Target (UI)
              └── Question verifica el resultado esperado
```

### Ventajas frente a Page Objects puros

- Los step definitions permanecen **legibles y libres de lógica de UI**.
- Las Tasks son **reutilizables** entre distintos escenarios sin duplicar pasos.
- Las Questions **desacoplan las aserciones** de la navegación, facilitando el mantenimiento cuando cambia la UI.

---

## Reporte de bugs

Durante la ejecución del suite se encontraron limitaciones de entorno que impiden ejecutar ciertos casos de prueba con los datos mockeados actuales.

→ [Ver reporte completo](docs/BUG_REPORT.md)

## Requisitos previos

| Herramienta | Versión mínima |
|---|---|
| Java (JDK) | 17 |
| Gradle | 9.0 (incluido via wrapper) |
| Google Chrome | última estable |

## Estructura del proyecto

```
src/
├── main/
│   ├── java/                          # Código fuente principal (si aplica)
│   └── resources/                     # Recursos principales
└── test/
    ├── java/org/<paquete>/
    │   ├── hooks/                     # Tasks de apertura de navegador, setup
    │   ├── questions/                 # Questions del patrón Screenplay
    │   ├── runners/                   # Runners de Cucumber (JUnit 5 @Suite)
    │   ├── stepdefinitions/           # Glue de Cucumber (step definitions)
    │   │   └── hooks/                 # Hooks de Cucumber (@Before, @After)
    │   ├── tasks/                     # Tasks del patrón Screenplay
    │   ├── ui/                        # Page Objects / Target definitions
    │   └── util/                      # Utilidades y datos de prueba
    └── resources/
        ├── features/                  # Archivos .feature (Gherkin)
        ├── logback-test.xml           # Configuración de logging
        └── serenity.conf              # Configuración de Serenity y WebDriver
```

---

## Cobertura de casos de prueba

Referencia cruzada entre los casos definidos en `TEST_CASES.md` y los escenarios Serenity implementados.

| TC | Descripción breve | Feature | Tags | Estado |
|---|---|---|---|---|
| TC-HU07-01 | Registro exitoso de usuario | `hu07_registro_usuario.feature` | `@critico` | ✅ Ejecutado |
| TC-HU08-01 | Inicio de sesión exitoso con JWT | `hu08_inicio_sesion.feature` | `@critico` | ✅ Ejecutado |
| TC-HU08-04 | Cierre de sesión bloquea acceso y limpia historial | `hu08_inicio_sesion.feature` | `@critico` | ✅ Ejecutado |
| TC-HU08-06 | Restauración de sesión al recargar | `hu08_inicio_sesion.feature` | `@medio` | ✅ Ejecutado |
| TC-HU01-01 | Registro exitoso de pedido con usuario autenticado | `hu01_registro_pedido.feature` | `@critico` | ✅ Ejecutado |
| TC-HU01-02 | Autocompletado restringido a Colombia | `hu01_registro_pedido.feature` | `@alto` | ✅ Ejecutado |
| TC-HU02-01 | Selección de prioridad COST | `hu02_prioridad_envio.feature` | `@critico` | ✅ Ejecutado |
| TC-HU02-01 | Selección de prioridad TIME | `hu02_prioridad_envio.feature` | `@critico` | ✅ Ejecutado |
| TC-HU04-01 | Visualización de alternativas disponibles | `hu04_alternativas_proveedores.feature` | `@critico` | ✅ Ejecutado |
| TC-HU04-02 | Ausencia de opciones alternativas | `hu04_alternativas_proveedores.feature` | `@alto @ignore` | ⚠️ Ignorado |
| TC-HU05-01 | Selección y confirmación de proveedor — COST | `hu05_seleccionar_confirmar.feature` | `@critico` | ✅ Ejecutado |
| TC-HU05-01 | Selección y confirmación de proveedor — TIME | `hu05_seleccionar_confirmar.feature` | `@critico` | ✅ Ejecutado |
| TC-HU06-01 | Visualización de ruta con marcadores — COST | `hu06_ruta_mapa.feature` | `@critico` | ✅ Ejecutado |
| TC-HU06-01 | Visualización de ruta con marcadores — TIME | `hu06_ruta_mapa.feature` | `@critico` | ✅ Ejecutado |
| TC-HU06-02 | Ajuste automático del mapa a la ruta — COST | `hu06_ruta_mapa.feature` | `@alto` | ✅ Ejecutado |
| TC-HU06-02 | Ajuste automático del mapa a la ruta — TIME | `hu06_ruta_mapa.feature` | `@alto` | ✅ Ejecutado |
| TC-HU06-04 | Sin datos de ruta no se muestra el mapa | `hu06_ruta_mapa.feature` | `@alto` | ✅ Ejecutado |
| TC-HU09-01 | Visualización de pedidos del usuario autenticado | `hu09_consultar_pedidos.feature` | `@critico` | ✅ Ejecutado |
| TC-HU09-02 | Usuario sin pedidos muestra mensaje informativo | `hu09_consultar_pedidos.feature` | `@alto` | ✅ Ejecutado |

> El TC marcado con ⚠️ `@ignore` está bloqueado por limitación de entorno (datos mockeados). Ver [reporte de bugs](docs/BUG_REPORT.md).

---

## Cómo ejecutar las pruebas

### Requisitos previos

- Java 17+
- Google Chrome (última versión estable)
- `frontend` corriendo en `http://localhost` (o la aplicación levantada con Docker Compose desde `/infra`)

### Ejecutar el suite completo

```bash
./gradlew clean test aggregate
```

Esto ejecuta todos los runners, genera el reporte Serenity y lo consolida en un único reporte HTML.

### Ejecutar una historia de usuario individual

Cada HU tiene su propio runner para ejecución local desde el IDE o por línea de comandos:

| Historia | Runner |
|---|---|
| HU-01 Registrar pedido | `HU01Runner.java` |
| HU-02 Prioridad de envío | `HU02Runner.java` |
| HU-04 Alternativas de proveedores | `HU04Runner.java` |
| HU-05 Seleccionar y confirmar | `HU05Runner.java` |
| HU-06 Ruta en el mapa | `HU06Runner.java` |
| HU-07 Registrar usuario | `HU07Runner.java` |
| HU-08 Iniciar sesión | `HU08Runner.java` |
| HU-09 Consultar pedidos | `HU09Runner.java` |

### Ver el reporte HTML

Después de ejecutar, el reporte unificado queda en:

```
target/site/serenity/index.html
```

### Modo headless

Por defecto, las pruebas se ejecutan con Chrome visible (`headless.mode = false`). Para correr sin abrir el navegador, cambiar en `src/test/resources/serenity.conf`:

```hocon
headless.mode = true
```

---

## Estado del suite

| Historia | Feature | Escenarios | Pasados | Ignorados |
|---|---|---|---|---|
| HU-07 Registrar usuario | `hu07_registro_usuario.feature` | 1 | 1 | 0 |
| HU-08 Iniciar sesión | `hu08_inicio_sesion.feature` | 3 | 3 | 0 |
| HU-01 Registrar pedido | `hu01_registro_pedido.feature` | 2 | 2 | 0 |
| HU-02 Prioridad de envío | `hu02_prioridad_envio.feature` | 2 | 2 | 0 |
| HU-04 Alternativas proveedores | `hu04_alternativas_proveedores.feature` | 2 | 1 | 1 |
| HU-05 Seleccionar y confirmar | `hu05_seleccionar_confirmar.feature` | 2 | 2 | 0 |
| HU-06 Ruta en el mapa | `hu06_ruta_mapa.feature` | 5 | 5 | 0 |
| HU-09 Consultar pedidos | `hu09_consultar_pedidos.feature` | 2 | 2 | 0 |
| **Total** | **8 features** | **19** | **18** | **1** |

---
