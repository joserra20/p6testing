package com.icai.practicas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DNITests {

    private DNI dni;

    @Test
    void formatoCorrecto() {

        dni = new DNI("02364298J"); // Correcto
        Boolean expectedResult = true;
        Boolean result = dni.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void dniNoValido() {

        dni = new DNI("00000000R"); // DNI no válido
        Boolean expectedResult = false;
        Boolean result = dni.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void formatoIncorrecto() {

        dni = new DNI("v2364298J"); // Formato incorrecto
        Boolean expectedResult = false;
        Boolean result = dni.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void letraNoValida() {

        dni = new DNI("02364298Ñ"); // Letra Ñ no valida
        Boolean expectedResult = false;
        Boolean result = dni.validar();

        Assertions.assertEquals(expectedResult,result);
    }

}