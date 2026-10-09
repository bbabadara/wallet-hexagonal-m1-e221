package org.ecole.attestationscolaire.config;

import org.ecole.attestationscolaire.domain.AttestationFormatter;
import org.ecole.attestationscolaire.domain.EtudiantRepository;
import org.ecole.attestationscolaire.domain.application.AttestationService;
import org.ecole.attestationscolaire.infra.EtudiantJpaAdapter;
import org.ecole.attestationscolaire.infra.TextAttestationFormatter;
import org.ecole.attestationscolaire.infra.repositories.EtudiantJpaRepository;
import org.ecole.attestationscolaire.wallet.domain.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.domain.WalletRepository;
import org.ecole.attestationscolaire.wallet.domain.application.WalletService;
import org.ecole.attestationscolaire.wallet.infra.TransactionJpaAdapter;
import org.ecole.attestationscolaire.wallet.infra.WalletJpaAdapter;
import org.ecole.attestationscolaire.wallet.infra.repositories.TransactionJpaRepository;
import org.ecole.attestationscolaire.wallet.infra.repositories.WalletJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WalletRepository walletRepository(WalletJpaRepository repository) {
        return new WalletJpaAdapter(repository);
    }

    @Bean
    public TransactionHistoryRepository transactionHistoryRepository(TransactionJpaRepository repository) {
        return new TransactionJpaAdapter(repository);
    }

    @Bean
    public WalletService walletService(
            WalletRepository walletRepository,
            TransactionHistoryRepository transactionHistoryRepository) {
        return new WalletService(walletRepository, transactionHistoryRepository);
    }

    @Bean
    public AttestationService attestationService(
            EtudiantRepository etudiantRepository,
            AttestationFormatter attestationFormatter) {
        return new AttestationService(etudiantRepository, attestationFormatter);
    }

    @Bean
    public EtudiantRepository etudiantRepository(EtudiantJpaRepository repository) {
        return new EtudiantJpaAdapter(repository);
    }

    @Bean
    public AttestationFormatter attestationFormatter() {
        return new TextAttestationFormatter();
    }
}