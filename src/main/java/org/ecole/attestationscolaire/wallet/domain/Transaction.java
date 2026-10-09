package org.ecole.attestationscolaire.wallet.domain;

import java.time.OffsetDateTime;

public class Transaction {
    private final Long id;
    private final Money montant;
    private final TransactionType type;
    private final OffsetDateTime date;

    public Transaction(Long id, Money montant, TransactionType type, OffsetDateTime date) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Money getMontant() {
        return montant;
    }

    public TransactionType getType() {
        return type;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}