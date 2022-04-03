package com.icai.practicas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void datos_correctos_respuesta_ok() {

        String address = "http://localhost:"+port+"/api/v1/process-step1";

        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("José Ramón Porro", "99173041P", "956150239");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        // Check
        then(result.getBody()).isEqualTo("{\"result\":\"OK\"}");
    }

    @Test
    public void datos_incorrectos_respuesta_ko() {

        String address = "http://localhost:" + port + "/api/v1/process-step1";
        
        // Telefono incorrecto
        ProcessController.DataRequest dataPrueba = new ProcessController.DataRequest("José Ramón Porro", "36104837K", "123ABCDEFG");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataPrueba, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        // Check
        then(result.getBody()).isEqualTo("{\"result\":\"KO\"}");

        // DNI Incorrecto
        dataPrueba = new ProcessController.DataRequest("José Ramón Porro", "00000000T", "987654321");
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<>(dataPrueba, headers);

        result = this.restTemplate.postForEntity(address, request, String.class);

        // Check
        then(result.getBody()).isEqualTo("{\"result\":\"KO\"}");
    }

    @Test
    public void datos_correctos_respuesta_ok_legacy() {
 
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        MultiValueMap<String, String> datos = new LinkedMultiValueMap<>();
        datos.add("fullName", "José Ramón Porro");
        datos.add("dni", "99173041P");
        datos.add("telefono", "956169105");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(datos, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        // Check
        then(result.getBody()).contains("Muchas gracias por enviar los datos");
    }

    @Test
    public void datos_incorrectos_respuesta_ko_legacy() {

        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        MultiValueMap<String, String> datos = new LinkedMultiValueMap<>();
        datos.add("fullName", "José Ramón Porro");
        datos.add("dni", "1234567Y8"); // Incorrecto
        datos.add("telefono", "987654321");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(datos, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        // Check
        then(result.getBody()).contains("Hemos tenido un problema con su solicitud");
    }
}
