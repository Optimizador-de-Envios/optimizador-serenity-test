# language: es
@HU05
Característica: HU-05 Seleccionar y confirmar proveedor

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU05-01 @critico
  Escenario: TC-HU05-01 - Selección de un proveedor disponible
    Dado que el usuario autenticado visualiza recomendación para origen "Bogotá" destino "Medellín" peso "5" con prioridad "COST"
    Cuando selecciona el proveedor recomendado
    Y confirma la selección
    Entonces el sistema muestra la pantalla de confirmación con los datos del pedido
