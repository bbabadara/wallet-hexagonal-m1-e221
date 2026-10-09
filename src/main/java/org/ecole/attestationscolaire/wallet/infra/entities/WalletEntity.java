package org.ecole.attestationscolaire.wallet.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.ecole.attestationscolaire.wallet.domain.Devise;

import java.math.BigDecimal;

@Entity
public class WalletEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String titulaire;
    private BigDecimal montant;
    @Enumerated(EnumType.STRING)
    private Devise devise;

    public WalletEntity() {
    }

    public WalletEntity(Long id, String titulaire, BigDecimal montant, Devise devise) {
        this.id = id;
        this.titulaire = titulaire;
        this.montant = montant;
        this.devise = devise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }
}