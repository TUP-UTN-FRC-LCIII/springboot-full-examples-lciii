package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.DummyDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyDetailJpaRepository extends JpaRepository<DummyDetailEntity, Long> {
}
