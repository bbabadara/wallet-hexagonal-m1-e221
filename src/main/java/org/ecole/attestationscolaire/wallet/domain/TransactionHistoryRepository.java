package org.ecole.attestationscolaire.wallet.domain;

public interface TransactionHistoryRepository {
    Transaction save(Transaction transaction);
}