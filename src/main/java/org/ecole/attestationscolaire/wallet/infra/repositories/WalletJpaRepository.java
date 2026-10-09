package org.ecole.attestationscolaire.wallet.infra.repositories;

import org.ecole.attestationscolaire.wallet.infra.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}