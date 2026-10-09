package org.ecole.attestationscolaire.wallet.domain;

import org.ecole.attestationscolaire.wallet.domain.exception.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.exception.SoldeInsuffisantException;

import java.math.BigDecimal;
import java.util.Objects;

public class Wallet {
    private final Long id;
    private final String titulaire;
    private BigDecimal solde;

    public Wallet(Long id, BigDecimal solde, String titulaire) {
        this.id = id;
        this.titulaire = Objects.requireNonNull(titulaire, "titulaire ne peut pas etre null");
        this.solde = solde == null ? BigDecimal.ZERO : solde;
        if (this.solde.signum() < 0) {
            throw new MontantInvalideException("Solde initial ne peut pas etre negatif");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void crediter(BigDecimal amount) {
        requirePositiveAmount(amount);
        this.solde = this.solde.add(amount);
    }

    public void debiter(BigDecimal amount) {
        requirePositiveAmount(amount);
        if (amount.compareTo(solde) > 0) {
            throw new SoldeInsuffisantException("Solde insuffisant : debit de " + amount + " alors que le solde est de " + solde);
        }
        this.solde = this.solde.subtract(amount);
    }

    private void requirePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new MontantInvalideException("Montant invalide : " + amount);
        }
    }
}
