package org.ecole.attestationscolaire.wallet.domain.application;

import org.ecole.attestationscolaire.wallet.domain.*;
import org.ecole.attestationscolaire.wallet.domain.exception.WalletNotFoundException;
import org.ecole.attestationscolaire.wallet.domain.port.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.domain.port.WalletRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public WalletService(WalletRepository walletRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.walletRepository = walletRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public Wallet createWallet(String titulaire, BigDecimal soldeInitial) {
        Wallet wallet = new Wallet(null, soldeInitial, titulaire);
        return walletRepository.save(wallet);
    }

    public Optional<Wallet> getWallet(Long id) {
        return walletRepository.findById(id);
    }

    public Wallet credit(Long walletId, BigDecimal montant) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet introuvable : " + walletId));
        wallet.crediter(montant);
        Wallet saved = walletRepository.save(wallet);
        transactionHistoryRepository.save(new Transaction(null, walletId, montant, TransactionType.CREDIT, LocalDateTime.now()));
        return saved;
    }

    public Wallet debit(Long walletId, BigDecimal montant) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet introuvable : " + walletId));
        wallet.debiter(montant);
        Wallet saved = walletRepository.save(wallet);
        transactionHistoryRepository.save(new Transaction(null, walletId, montant, TransactionType.DEBIT, LocalDateTime.now()));
        return saved;
    }
}
