package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.Dummy;
import ar.edu.utn.frc.tup.lciii.models.DummyDetail;
import ar.edu.utn.frc.tup.lciii.models.DummyType;
import ar.edu.utn.frc.tup.lciii.services.DummyServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
Esta anotación se utiliza para configurar JUnit para que se ejecute con el soporte de Spring.
Proporciona una infraestructura especial para la ejecución de pruebas de Spring Boot.
 */
@RunWith(SpringRunner.class)
/*
Esta anotación se utiliza para las pruebas de integración de controladores REST en aplicaciones
web. Configura un entorno de prueba específico para probar los controladores y las solicitudes HTTP.
 */
@WebMvcTest(DummyController.class)
class DummyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
    Esta anotación se utiliza para crear un objeto simulado (mock) de una dependencia de Spring.
    Permite simular el comportamiento de los componentes externos y controlar sus respuestas
    durante las pruebas unitarias.
     */
    @MockBean
    private DummyServiceInterface dummyServiceInterface;


    @Test
    void getDummy() throws Exception {
        // Test data
        Dummy dummy = new Dummy(1L,
                "Dummy",
                null,
                new DummyDetail("Dummy Detail"),
                DummyType.TYPE_A,
                LocalDateTime.now());
        // Given
        Mockito.when(dummyServiceInterface.getDummy(1L)).thenReturn(dummy);
        // When
        mvc.perform(
                get("/dummies/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dummy)));
    }

    @Test
    void searchDummy() throws Exception {
        // Test data
        List<Dummy> dummies = Arrays.asList(new Dummy(1L,
                "Dummy",
                null,
                new DummyDetail("Dummy Detail"),
                DummyType.TYPE_A,
                LocalDateTime.now()));
        // Given
        Mockito.when(dummyServiceInterface.searchByInfo("Dummy")).thenReturn(dummies);
        // When
        mvc.perform(
                        get("/dummies?info=Dummy")
                                .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dummies)));
    }

}