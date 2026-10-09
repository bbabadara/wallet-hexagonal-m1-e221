package org.ecole.attestationscolaire.wallet.infra.persistence.repository;

import org.ecole.attestationscolaire.wallet.infra.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
}
