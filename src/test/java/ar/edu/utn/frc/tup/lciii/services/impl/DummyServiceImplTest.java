package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.Application;
import ar.edu.utn.frc.tup.lciii.entities.DummyDetailEntity;
import ar.edu.utn.frc.tup.lciii.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.models.Dummy;
import ar.edu.utn.frc.tup.lciii.models.DummyDetail;
import ar.edu.utn.frc.tup.lciii.models.DummyType;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.DummyJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.DummyServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
/*
Esta anotación se utiliza para cargar y configurar el contexto de Spring Boot
durante las pruebas de integración. Carga todas las configuraciones de la
aplicación y permite acceder a los beans administrados por Spring en las pruebas.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
class DummyServiceImplTest {

    @MockBean
    private DummyJpaRepository dummyJpaRepository;

    /*
    Esta anotación se utiliza para crear un objeto simulado (spy)
    que se crea a partir de un objeto real de una dependencia de Spring.
    Permite simular el comportamiento de los componentes externos y controlar
    sus respuestas durante las pruebas unitarias o ejecutar el comportamiento original del objeto real.
     */
    @SpyBean
    private DummyServiceInterface dummyServiceInterface;

    @Test
    void getDummy() {
        // Test data
        DummyEntity dummyEntity = new DummyEntity();
        dummyEntity.setId(1L);
        dummyEntity.setDummyInfo("Dummy");
        dummyEntity.setDummyDetail(new DummyDetailEntity(1L, dummyEntity, "Dummy Detail"));
        dummyEntity.setType(DummyType.TYPE_A);
        dummyEntity.setIsDeleted(false);
        // Given
        Mockito.when(dummyJpaRepository.getReferenceById(1L)).thenReturn(dummyEntity);
        // When
        Dummy result = dummyServiceInterface.getDummy(1L);
        // Then
        assertEquals("Dummy", result.getDummyInfo());
    }
}