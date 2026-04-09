# language: es
@HU06
Característica: HU-06 Visualizar ruta del envío en el mapa

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU06-01 @critico
  Esquema del escenario: TC-HU06-01 - Visualización de ruta con marcadores según prioridad
    Dado que el usuario autenticado ha confirmado un pedido con origen "<origen>" destino "<destino>" peso "<peso>" y prioridad "<prioridad>"
    Cuando visualiza la página de confirmación
    Entonces el mapa se renderiza con la ruta dibujada
    Y los marcadores de origen y destino son visibles

    Ejemplos:
      | origen  | destino   | peso | prioridad |
      | Bogotá  | Medellín  | 5    | COST      |
      | Bogotá  | Medellín  | 5    | TIME      |

  @TC-HU06-02 @alto
  Esquema del escenario: TC-HU06-02 - Ajuste automático del mapa a la ruta según prioridad
    Dado que el usuario autenticado ha confirmado un pedido con origen "<origen>" destino "<destino>" peso "<peso>" y prioridad "<prioridad>"
    Cuando visualiza la página de confirmación
    Entonces el contenedor del mapa es visible en pantalla
    Y la sección de ruta está desplegada correctamente

    Ejemplos:
      | origen  | destino   | peso | prioridad |
      | Bogotá  | Medellín  | 5    | COST      |
      | Bogotá  | Medellín  | 5    | TIME      |

  @TC-HU06-04 @alto
  Escenario: TC-HU06-04 - Intento de visualización sin datos de ruta
    Dado que el usuario autenticado está en el formulario de pedido
    Cuando no ha completado origen ni destino
    Entonces no se muestra el mapa de vista previa
