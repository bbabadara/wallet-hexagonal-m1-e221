package org.ecole.attestationscolaire.wallet.infra.adapter;

import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.port.WalletRepository;
import org.ecole.attestationscolaire.wallet.infra.persistence.entity.WalletEntity;
import org.ecole.attestationscolaire.wallet.infra.persistence.repository.WalletJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletJpaAdapter implements WalletRepository {
    private final WalletJpaRepository repository;

    public WalletJpaAdapter(WalletJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = toEntity(wallet);
        WalletEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    private WalletEntity toEntity(Wallet wallet) {
        return new WalletEntity(wallet.getId(), wallet.getTitulaire(), wallet.getSolde());
    }

    private Wallet toDomain(WalletEntity entity) {
        return new Wallet(entity.getId(), entity.getSolde(), entity.getTitulaire());
    }
}
