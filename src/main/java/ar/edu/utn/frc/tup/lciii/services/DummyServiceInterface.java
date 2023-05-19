package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Dummy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyServiceInterface {

    Dummy getDummy(Long id);

    List<Dummy> getAllDummy();

    Dummy createDummy(Dummy dummy);

    Dummy updateDummy(Dummy dummy);

    Dummy deleteDummy(Long id);

    List<Dummy> searchByInfo(String info);
}
