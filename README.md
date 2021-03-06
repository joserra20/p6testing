# Practica 6: Testing de una aplicacion de Spring Boot

### DNI & Telefono (Unit Tests)
Se han configurado varias pruebas comprobando las diferentes casuisticas posibles y verificando el correcto funcionamiento de la validación de formato, longitud, etc.

### ProcessController (E2E Tests)
Se han configurado 4 pruebas 'End to End' que cubren todas las casuisticas posibles cuando se llama al servicio.

### Ejecución de los tests
Para ejecutar los tests se debe ejecutar el siguiente comando:
```
mvn test
```

### GitPod
Se puede visualizar el código del proyecto haciendo click en el siguiente boton.

[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/joserra20/p6testing)

## Referencias

- https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/boot-features-testing.html
- https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/web/client/TestRestTemplate.html
- https://www.urlencoder.org/
