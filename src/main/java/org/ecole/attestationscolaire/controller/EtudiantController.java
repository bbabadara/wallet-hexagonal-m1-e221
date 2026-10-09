package org.ecole.attestationscolaire.controller;

import org.ecole.attestationscolaire.domain.application.AttestationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantController {
    private final AttestationService attestationService;

    public EtudiantController(AttestationService attestationService) {
        this.attestationService = attestationService;
    }

    @GetMapping("/attestations/{id}")
    public String attestations(@PathVariable Long id) throws Exception {
        return attestationService.genererAttestation(id);
    }
}
