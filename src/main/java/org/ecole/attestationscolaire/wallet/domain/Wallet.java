package org.ecole.attestationscolaire.wallet.domain;

public class Wallet {
    private final Long id;
    private final String titulaire;
    private Money solde;

    public Wallet(Long id, Money solde, String titulaire) {
        this.id = id;
        this.solde = solde;
        this.titulaire = titulaire;
    }

    public Long getId() {
        return id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public Money getSolde() {
        return solde;
    }

    public void crediter(Money montant) {
        requireValidOperation(montant);
        this.solde = solde.plus(montant);
    }

    public void debiter(Money montant) {
        requireValidOperation(montant);
        if (montant.isGreaterThan(solde)) {
            throw new SoldeInsuffisantException(
                    "Solde insuffisant : debit de " + montant + " alors que le solde est de " + solde);
        }
        this.solde = solde.minus(montant);
    }

    private void requireValidOperation(Money montant) {
        if (montant == null || montant.getAmount() == null || montant.isNegative()) {
            throw new MontantInvalideException("Montant invalide : " + montant);
        }
        if (montant.getDevise() != solde.getDevise()) {
            throw new DeviseIncompatibleException(
                    "Devise incompatible : " + montant.getDevise() + " != " + solde.getDevise());
        }
    }
}