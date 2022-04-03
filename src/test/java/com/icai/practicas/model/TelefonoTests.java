package com.icai.practicas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TelefonoTests {
    
    private Telefono telefono;

    @Test
    void telefonoCorrecto() {

        telefono= new Telefono("+34 9561692063"); 
        Boolean expectedResult= true;
        Boolean result = telefono.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void telefonoCorrecto2() {

        telefono= new Telefono("9561692063"); 
        Boolean expectedResult= true;
        Boolean result = telefono.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void telefonoIncorrecto() {

        telefono= new Telefono("B14486294"); // Formato incorrecto
        Boolean expectedResult= false;
        Boolean result = telefono.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void telefonoIncorrecto2() {

        telefono= new Telefono("9571859208567392"); // Longitud excedida
        Boolean expectedResult= false;
        Boolean result = telefono.validar();

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    void telefonoIncorrecto3() {

        telefono= new Telefono("9"); 
        Boolean expectedResult= false;
        Boolean result = telefono.validar();

        Assertions.assertEquals(expectedResult,result);
    }
}
