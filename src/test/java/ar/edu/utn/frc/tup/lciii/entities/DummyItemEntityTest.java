package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.repositories.jpa.DummyItemJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
/*
Esta anotación se utiliza para las pruebas de integración que involucran
repositorios de Spring Data JPA. Configura un entorno de prueba
específico para las operaciones de persistencia de datos.
 */
@DataJpaTest
class DummyItemEntityTest {

    @Autowired
    /*
     Es una interfaz que proporciona métodos convenientes para interactuar
     con la base de datos durante las pruebas de integración.
     Permite realizar operaciones CRUD y consultas a la base de datos utilizando JPA.
     */
    private TestEntityManager entityManager;

    @Autowired
    private DummyItemJpaRepository dummyItemJpaRepository;

    @Test
    public void getReferenceById() {
        DummyEntity dummyEntity = new DummyEntity();
        dummyEntity.setId(1L);
        dummyEntity.setDummyInfo("Dummy");

        DummyItemEntity dummyItemEntity = new DummyItemEntity();
        dummyItemEntity.setDummy(dummyEntity);
        dummyItemEntity.setDummyItemDetail("Dummy Item Detail");
        dummyItemEntity.setDummyItemQuantity(BigDecimal.valueOf(10));
        dummyItemEntity.setDummyItemAmount(BigDecimal.valueOf(300));

        entityManager.persist(dummyEntity);
        entityManager.persist(dummyItemEntity);
        entityManager.flush();

        DummyItemEntity result = dummyItemJpaRepository.getReferenceById(1L);
        assertEquals("Dummy Item Detail", result.getDummyItemDetail());
    }

}