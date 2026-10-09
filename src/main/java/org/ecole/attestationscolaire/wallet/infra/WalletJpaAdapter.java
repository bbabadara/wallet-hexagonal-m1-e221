package org.ecole.attestationscolaire.wallet.infra;

import org.ecole.attestationscolaire.wallet.domain.Money;
import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.WalletNotFoundException;
import org.ecole.attestationscolaire.wallet.domain.WalletRepository;
import org.ecole.attestationscolaire.wallet.infra.entities.WalletEntity;
import org.ecole.attestationscolaire.wallet.infra.repositories.WalletJpaRepository;

public class WalletJpaAdapter implements WalletRepository {
    private final WalletJpaRepository repository;

    public WalletJpaAdapter(WalletJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet findById(Long id) {
        return repository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new WalletNotFoundException("Wallet introuvable : " + id));
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = repository.save(toEntity(wallet));
        return toDomain(entity);
    }

    private WalletEntity toEntity(Wallet wallet) {
        return new WalletEntity(
                wallet.getId(),
                wallet.getTitulaire(),
                wallet.getSolde().getAmount(),
                wallet.getSolde().getDevise());
    }

    private Wallet toDomain(WalletEntity entity) {
        return new Wallet(
                entity.getId(),
                new Money(entity.getMontant(), entity.getDevise()),
                entity.getTitulaire());
    }
}