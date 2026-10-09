package org.ecole.attestationscolaire.domain.application;

import org.ecole.attestationscolaire.domain.AttestationFormatter;
import org.ecole.attestationscolaire.domain.EtudiantRepository;


public class AttestationService {
    private final EtudiantRepository repository;
    private final AttestationFormatter formatter;

    public AttestationService(EtudiantRepository repository, AttestationFormatter formatter) {
        this.repository = repository;
        this.formatter = formatter;
    }

    public String genererAttestation(Long etudiantId) throws Exception {
        return formatter.formatEtudiant(repository.findById(etudiantId));
    }
}
