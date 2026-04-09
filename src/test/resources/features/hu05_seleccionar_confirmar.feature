# language: es
@HU05
Característica: HU-05 Seleccionar y confirmar proveedor

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU05-01 @critico
  Esquema del escenario: TC-HU05-01 - Selección y confirmación de proveedor según prioridad
    Dado que el usuario autenticado visualiza recomendación para origen "<origen>" destino "<destino>" peso "<peso>" con prioridad "<prioridad>"
    Cuando selecciona el proveedor recomendado
    Y confirma la selección
    Entonces el sistema muestra la pantalla de confirmación con los datos del pedido

    Ejemplos:
      | origen  | destino   | peso | prioridad |
      | Bogotá  | Medellín  | 5    | COST      |
      | Bogotá  | Medellín  | 5    | TIME      |
