# language: es
@HU02
Característica: HU-02 Definir prioridad del envío

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU02-01 @critico
  Esquema del escenario: TC-HU02-01 - Selección de prioridad de envío (<prioridad>)
    Dado que el usuario autenticado registró un pedido válido con origen "Bogotá" destino "Medellín" y peso "5"
    Cuando selecciona la prioridad "<prioridad>"
    Entonces el sistema registra la prioridad y permite continuar con la recomendación

    Ejemplos:
      | prioridad |
      | COST      |
      | TIME      |
