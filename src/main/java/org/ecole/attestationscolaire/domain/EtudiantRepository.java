package org.ecole.attestationscolaire.domain;

public interface EtudiantRepository {
    Etudiant findById(Long id) throws Exception;
}
