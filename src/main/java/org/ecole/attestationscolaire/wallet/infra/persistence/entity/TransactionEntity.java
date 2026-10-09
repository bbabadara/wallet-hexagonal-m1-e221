package org.ecole.attestationscolaire.wallet.infra.persistence.entity;

import jakarta.persistence.*;
import org.ecole.attestationscolaire.wallet.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long walletId;

    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime date;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, Long walletId, BigDecimal montant, TransactionType type, LocalDateTime date) {
        this.id = id;
        this.walletId = walletId;
        this.montant = montant;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
