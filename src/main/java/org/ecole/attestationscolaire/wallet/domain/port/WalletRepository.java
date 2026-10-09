package org.ecole.attestationscolaire.wallet.domain.port;

import org.ecole.attestationscolaire.wallet.domain.Wallet;

import java.util.Optional;

public interface WalletRepository {
    Wallet save(Wallet wallet);
    Optional<Wallet> findById(Long id);
}
