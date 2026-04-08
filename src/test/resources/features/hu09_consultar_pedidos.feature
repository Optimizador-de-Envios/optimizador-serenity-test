# language: es
@HU09
Característica: HU-09 Consultar pedidos del usuario

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU09-01 @critico
  Escenario: TC-HU09-01 - Visualización de pedidos del usuario autenticado
    Dado que el usuario autenticado tiene pedidos confirmados en su cuenta
    Cuando consulta su historial de pedidos
    Entonces el sistema muestra sus pedidos con origen, destino, peso, prioridad y proveedor

  @TC-HU09-02 @alto
  Escenario: TC-HU09-02 - Usuario sin pedidos registrados
    Dado que el usuario autenticado no tiene pedidos registrados
    Cuando consulta su historial de pedidos
    Entonces el sistema informa que no existen pedidos registrados
