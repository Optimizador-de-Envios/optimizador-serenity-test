# language: es
@HU04
Característica: HU-04 Obtener opciones alternativas de proveedores

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU04-01 @critico
  Escenario: TC-HU04-01 - Visualización de alternativas disponibles
    Dado que el usuario autenticado obtuvo una recomendación para origen "Bogotá" destino "Medellín" peso "5" con prioridad "COST"
    Cuando el sistema muestra la página de resultados
    Entonces aparece la recomendación principal con proveedor, costo y tiempo
    Y las alternativas disponibles se muestran con proveedor, costo y tiempo

  @TC-HU04-02 @alto
  Escenario: TC-HU04-02 - Ausencia de opciones alternativas
    Dado que el usuario autenticado obtuvo una recomendación para origen "Bogotá" destino "Medellín" peso "5" con prioridad "COST"
    Cuando el sistema muestra la página de resultados
    Y no existen alternativas
    Entonces la UI informa que no hay alternativas disponibles
