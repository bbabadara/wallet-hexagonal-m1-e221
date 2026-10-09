package org.ecole.attestationscolaire.wallet.infra.adapter;

import org.ecole.attestationscolaire.wallet.domain.Transaction;
import org.ecole.attestationscolaire.wallet.domain.port.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.infra.persistence.entity.TransactionEntity;
import org.ecole.attestationscolaire.wallet.infra.persistence.repository.TransactionJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionJpaAdapter implements TransactionHistoryRepository {
    private final TransactionJpaRepository repository;

    public TransactionJpaAdapter(TransactionJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    private TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(transaction.getId(), transaction.getWalletId(), transaction.getMontant(), transaction.getType(), transaction.getDate());
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(entity.getId(), entity.getWalletId(), entity.getMontant(), entity.getType(), entity.getDate());
    }
}
