package org.ecole.attestationscolaire.wallet.domain;

public interface WalletRepository {
    Wallet findById(Long id);

    Wallet save(Wallet wallet);
}