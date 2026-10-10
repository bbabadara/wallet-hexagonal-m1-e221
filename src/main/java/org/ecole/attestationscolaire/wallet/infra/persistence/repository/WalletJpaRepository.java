package org.ecole.attestationscolaire.wallet.infra.persistence.repository;

import org.ecole.attestationscolaire.wallet.infra.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}
