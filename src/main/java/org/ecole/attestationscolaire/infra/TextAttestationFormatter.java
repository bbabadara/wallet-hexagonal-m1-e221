package org.ecole.attestationscolaire.infra;

import org.ecole.attestationscolaire.domain.AttestationFormatter;
import org.ecole.attestationscolaire.domain.Etudiant;

public class TextAttestationFormatter implements AttestationFormatter {
    @Override
    public String formatEtudiant(Etudiant etudiant) {
        return "Attestation de scolarité - " + etudiant.getNom() + " - Filiere : " + etudiant.getFiliere();
    }
}
