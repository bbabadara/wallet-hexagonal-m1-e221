package org.ecole.attestationscolaire.wallet.domain.port;

import org.ecole.attestationscolaire.wallet.domain.Transaction;

public interface TransactionHistoryRepository {
    Transaction save(Transaction transaction);
}
