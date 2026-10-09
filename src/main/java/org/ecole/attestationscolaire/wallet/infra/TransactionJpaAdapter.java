package org.ecole.attestationscolaire.wallet.infra;

import org.ecole.attestationscolaire.wallet.domain.Money;
import org.ecole.attestationscolaire.wallet.domain.Transaction;
import org.ecole.attestationscolaire.wallet.domain.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.infra.entities.TransactionEntity;
import org.ecole.attestationscolaire.wallet.infra.repositories.TransactionJpaRepository;

public class TransactionJpaAdapter implements TransactionHistoryRepository {
    private final TransactionJpaRepository repository;

    public TransactionJpaAdapter(TransactionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity saved = repository.save(toEntity(transaction));
        return toDomain(saved);
    }

    private TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getMontant().getAmount(),
                transaction.getMontant().getDevise(),
                transaction.getType(),
                transaction.getDate());
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                new Money(entity.getMontant(), entity.getDevise()),
                entity.getType(),
                entity.getDate());
    }
}