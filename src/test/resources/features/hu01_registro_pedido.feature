# language: es
@HU01
Característica: HU-01 Registrar pedido de envío

  Antecedentes:
    Dado que el usuario está autenticado en la plataforma

  @TC-HU01-01 @critico
  Escenario: TC-HU01-01 - Registro exitoso con usuario autenticado
    Dado que el usuario autenticado necesita enviar un producto
    Cuando ingresa origen "Bogotá", destino "Medellín" y peso "5"
    Entonces el formulario avanza al cálculo conservando los datos

  @TC-HU01-02 @alto
  Escenario: TC-HU01-02 - Autocompletado restringido a Colombia
    Dado que el usuario autenticado registra un pedido
    Cuando escribe texto parcial "Bog" en el campo de origen
    Entonces el sistema muestra sugerencias que contienen "Colombia"
    Y el usuario puede seleccionar una sugerencia válida
