package org.ecole.attestationscolaire.wallet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private final Long id;
    private final Long walletId;
    private final BigDecimal montant;
    private final TransactionType type;
    private final LocalDateTime date;

    public Transaction(Long id, Long walletId, BigDecimal montant, TransactionType type, LocalDateTime date) {
        this.id = id;
        this.walletId = Objects.requireNonNull(walletId, "walletId ne peut pas etre null");
        this.montant = Objects.requireNonNull(montant, "montant ne peut pas etre null");
        this.type = Objects.requireNonNull(type, "type ne peut pas etre null");
        this.date = date == null ? LocalDateTime.now() : date;
    }

    public Long getId() {
        return id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
