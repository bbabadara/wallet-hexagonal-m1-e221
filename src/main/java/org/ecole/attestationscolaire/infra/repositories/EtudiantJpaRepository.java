package org.ecole.attestationscolaire.infra.repositories;

import org.ecole.attestationscolaire.infra.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantJpaRepository extends JpaRepository<EtudiantEntity, Long> {
}
