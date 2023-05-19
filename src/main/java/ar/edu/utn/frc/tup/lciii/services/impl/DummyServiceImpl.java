package ar.edu.utn.frc.tup.lciii.services.impl;


import ar.edu.utn.frc.tup.lciii.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.models.Dummy;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.DummyJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.DummyServiceInterface;
import org.hibernate.annotations.Where;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*
@Component = Se utiliza para marcar una clase como un componente de Spring.
Indica que la clase es un candidato para ser detectado y configurado automáticamente por Spring.

Esta anotación se utiliza para marcar una clase como un servicio de negocio.
Indica que la clase contiene la lógica de negocio de la aplicación y se utiliza
para separar las responsabilidades de la capa de presentación y la capa de acceso a datos.
 */
@Service
public class DummyServiceImpl implements DummyServiceInterface {

    @Autowired
    private DummyJpaRepository dummyJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapper mergerMapper;

    @Override
    public Dummy getDummy(Long id) {
        return modelMapper.map(dummyJpaRepository.getReferenceById(id), Dummy.class);
    }

    @Override
    public List<Dummy> getAllDummy() {
        List<Dummy> dummies = new ArrayList<>();
        List<DummyEntity> dummyEntities = dummyJpaRepository.findAll();
        dummyEntities.forEach(d ->dummies.add( modelMapper.map(d, Dummy.class)));
        return dummies;
    }

    @Override
    public Dummy createDummy(Dummy dummy) {
        DummyEntity dummyEntity = modelMapper.map(dummy, DummyEntity.class);
        dummyEntity.getDummyItems().forEach(i -> i.setDummy(dummyEntity));
        dummyEntity.getDummyDetail().setDummy(dummyEntity);
        dummyEntity.setCreatedDate(LocalDateTime.now());
        return modelMapper.map(dummyJpaRepository.save(dummyEntity), Dummy.class);
    }

    @Override
    public Dummy updateDummy(Dummy dummy) {
        DummyEntity dummyEntity = dummyJpaRepository.getReferenceById(dummy.getId());
        mergerMapper.map(dummy, dummyEntity);
        dummyEntity.setUpdatedDate(LocalDateTime.now());
        return modelMapper.map(dummyJpaRepository.save(dummyEntity), Dummy.class);
    }

    @Override
    public Dummy deleteDummy(Long id) {
        DummyEntity dummyEntity = dummyJpaRepository.getReferenceById(id);
        dummyEntity.setUpdatedDate(LocalDateTime.now());
        dummyEntity.setIsDeleted(true);
        return modelMapper.map(dummyJpaRepository.save(dummyEntity), Dummy.class);
    }

    @Override
    public List<Dummy> searchByInfo(String info) {
        List<Dummy> dummies = new ArrayList<>();
        DummyEntity dummyEntity = new DummyEntity();
        dummyEntity.setDummyInfo(info);
        Example<DummyEntity> dummyExample = Example.of(dummyEntity);
        List<DummyEntity> dummyEntities = dummyJpaRepository.findAll(dummyExample);
        dummyEntities.forEach(d ->dummies.add( modelMapper.map(d, Dummy.class)));
        return dummies;
    }
}
