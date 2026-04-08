# language: es
@HU07
Característica: HU-07 Registrar usuario

  @TC-HU07-01 @critico
  Escenario: TC-HU07-01 - Registro exitoso de usuario
    Dado que una persona desea utilizar la plataforma
    Cuando ingresa nombre "Usuario QA", un correo único y contraseña "Password123"
    Entonces el sistema registra al usuario y puede iniciar sesión con la cuenta creada
