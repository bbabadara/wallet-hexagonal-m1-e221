package org.ecole.attestationscolaire.wallet.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.ecole.attestationscolaire.wallet.domain.Devise;
import org.ecole.attestationscolaire.wallet.domain.TransactionType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal montant;
    @Enumerated(EnumType.STRING)
    private Devise devise;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private OffsetDateTime date;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, BigDecimal montant, Devise devise, TransactionType type, OffsetDateTime date) {
        this.id = id;
        this.montant = montant;
        this.devise = devise;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Devise getDevise() {
        return devise;
    }

    public TransactionType getType() {
        return type;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}