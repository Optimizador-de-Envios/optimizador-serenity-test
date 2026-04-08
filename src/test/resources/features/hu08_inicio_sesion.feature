# language: es
@HU08
Característica: HU-08 Iniciar sesión

  @TC-HU08-01 @critico
  Escenario: TC-HU08-01 - Inicio de sesión exitoso con JWT
    Dado que el usuario ya está registrado en la plataforma
    Cuando ingresa correo "qa.usuario@example.com" y contraseña "Password123"
    Entonces el sistema permite el acceso y habilita las funcionalidades protegidas

  @TC-HU08-04 @critico
  Escenario: TC-HU08-04 - Cierre de sesión bloquea acceso y limpia historial
    Dado que el usuario autenticado tiene historial de pedidos visible
    Cuando cierra sesión
    Entonces el sistema redirige a la página de inicio de sesión
    Y las rutas protegidas no son accesibles sin autenticación

  @TC-HU08-06 @medio
  Escenario: TC-HU08-06 - Restauración de sesión al recargar
    Dado que el usuario inició sesión correctamente
    Cuando recarga el navegador
    Entonces el sistema restaura la sesión y permanece en la ruta protegida
