package org.ecole.attestationscolaire.wallet.infra.repositories;

import org.ecole.attestationscolaire.wallet.infra.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
}