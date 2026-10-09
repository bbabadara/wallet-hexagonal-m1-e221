package org.ecole.attestationscolaire.wallet.domain.application;

import org.ecole.attestationscolaire.wallet.domain.Devise;
import org.ecole.attestationscolaire.wallet.domain.Money;
import org.ecole.attestationscolaire.wallet.domain.SoldeInsuffisantException;
import org.ecole.attestationscolaire.wallet.domain.Transaction;
import org.ecole.attestationscolaire.wallet.domain.TransactionHistoryRepository;
import org.ecole.attestationscolaire.wallet.domain.TransactionType;
import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WalletServiceTest {

    private InMemoryWalletRepository walletRepository;
    private InMemoryTransactionHistoryRepository transactionRepository;
    private WalletService service;

    @BeforeEach
    void setup() {
        walletRepository = new InMemoryWalletRepository();
        transactionRepository = new InMemoryTransactionHistoryRepository();
        service = new WalletService(walletRepository, transactionRepository);
    }

    @Test
    void crediterReussiEnregistreUneTransactionDeTypeCredit() {
        Wallet wallet = service.creerWallet("Awa Diop", new Money(new BigDecimal("100.00"), Devise.EUR));

        service.crediter(wallet.getId(), new Money(new BigDecimal("50.00"), Devise.EUR));

        assertEquals(1, transactionRepository.transactions.size());
        assertEquals(TransactionType.CREDIT, transactionRepository.transactions.get(0).getType());
        assertEquals(new BigDecimal("150.00"), service.getWallet(wallet.getId()).getSolde().getAmount());
    }

    @Test
    void debiterReussiEnregistreUneTransactionDeTypeDebit() {
        Wallet wallet = service.creerWallet("Awa Diop", new Money(new BigDecimal("100.00"), Devise.EUR));

        service.debiter(wallet.getId(), new Money(new BigDecimal("30.00"), Devise.EUR));

        assertEquals(1, transactionRepository.transactions.size());
        assertEquals(TransactionType.DEBIT, transactionRepository.transactions.get(0).getType());
        assertEquals(new BigDecimal("70.00"), service.getWallet(wallet.getId()).getSolde().getAmount());
    }

    @Test
    void debitRefusePourSoldeInsuffisantNEnregistreAucuneTransaction() {
        Wallet wallet = service.creerWallet("Awa Diop", new Money(new BigDecimal("10.00"), Devise.EUR));

        assertThrows(SoldeInsuffisantException.class,
                () -> service.debiter(wallet.getId(), new Money(new BigDecimal("50.00"), Devise.EUR)));

        assertTrue(transactionRepository.transactions.isEmpty());
        assertEquals(new BigDecimal("10.00"), service.getWallet(wallet.getId()).getSolde().getAmount());
    }

    private static class InMemoryWalletRepository implements WalletRepository {
        private final List<Wallet> wallets = new ArrayList<>();
        private long nextId = 1L;

        @Override
        public Wallet findById(Long id) {
            return wallets.stream()
                    .filter(w -> w.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("wallet inconnu : " + id));
        }

        @Override
        public Wallet save(Wallet wallet) {
            Wallet managed = wallet.getId() == null
                    ? new Wallet(nextId++, wallet.getSolde(), wallet.getTitulaire())
                    : wallet;
            wallets.removeIf(w -> managed.getId().equals(w.getId()));
            wallets.add(managed);
            return managed;
        }
    }

    private static class InMemoryTransactionHistoryRepository implements TransactionHistoryRepository {
        private final List<Transaction> transactions = new ArrayList<>();

        @Override
        public Transaction save(Transaction transaction) {
            transactions.add(transaction);
            return transaction;
        }
    }
}