package org.ecole.attestationscolaire.wallet.domain.application;

import org.ecole.attestationscolaire.wallet.domain.Money;
import org.ecole.attestationscolaire.wallet.domain.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.Transaction;
import org.ecole.attestationscolaire.wallet.domain.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.domain.TransactionType;
import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.WalletRepository;

import java.time.OffsetDateTime;

public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public WalletService(WalletRepository walletRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.walletRepository = walletRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public Wallet creerWallet(String titulaire, Money soldeInitial) {
        if (soldeInitial == null || soldeInitial.isNegative()) {
            throw new MontantInvalideException("Solde initial invalide : " + soldeInitial);
        }
        return walletRepository.save(new Wallet(null, soldeInitial, titulaire));
    }

    public Wallet crediter(Long walletId, Money montant) {
        Wallet wallet = walletRepository.findById(walletId);
        wallet.crediter(montant);
        walletRepository.save(wallet);
        transactionHistoryRepository.save(
                new Transaction(null, montant, TransactionType.CREDIT, OffsetDateTime.now()));
        return wallet;
    }

    public Wallet debiter(Long walletId, Money montant) {
        Wallet wallet = walletRepository.findById(walletId);
        wallet.debiter(montant);
        walletRepository.save(wallet);
        transactionHistoryRepository.save(
                new Transaction(null, montant, TransactionType.DEBIT, OffsetDateTime.now()));
        return wallet;
    }

    public Wallet getWallet(Long walletId) {
        return walletRepository.findById(walletId);
    }
}