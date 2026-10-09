package org.ecole.attestationscolaire.infra;

import org.ecole.attestationscolaire.domain.Etudiant;
import org.ecole.attestationscolaire.domain.EtudiantRepository;
import org.ecole.attestationscolaire.infra.entities.EtudiantEntity;
import org.ecole.attestationscolaire.infra.repositories.EtudiantJpaRepository;

public class EtudiantJpaAdapter implements EtudiantRepository {
    private final EtudiantJpaRepository repository;

    public EtudiantJpaAdapter(EtudiantJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Etudiant findById(Long id) throws Exception {
        EtudiantEntity etudiantEntity = repository.findById(id).orElseThrow(() -> new Exception("id"));
        return new Etudiant(etudiantEntity.getId(), etudiantEntity.getNom(), etudiantEntity.getFiliere());
    }
}
