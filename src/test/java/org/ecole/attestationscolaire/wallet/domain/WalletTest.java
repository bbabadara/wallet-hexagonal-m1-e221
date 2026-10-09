package org.ecole.attestationscolaire.wallet.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalletTest {

    private Wallet walletAvecSoldeInitial() {
        return new Wallet(1L, new Money(new BigDecimal("100.00"), Devise.EUR), "Awa Diop");
    }

    @Test
    void crediterAugmenteLeSolde() {
        Wallet wallet = walletAvecSoldeInitial();

        wallet.crediter(new Money(new BigDecimal("50.00"), Devise.EUR));

        assertEquals(new BigDecimal("150.00"), wallet.getSolde().getAmount());
    }

    @Test
    void debiterDiminueLeSolde() {
        Wallet wallet = walletAvecSoldeInitial();

        wallet.debiter(new Money(new BigDecimal("30.00"), Devise.EUR));

        assertEquals(new BigDecimal("70.00"), wallet.getSolde().getAmount());
    }

    @Test
    void debiterSuperieurAuSoldeEstRefuseEtLaisseLeSoldeInchange() {
        Wallet wallet = walletAvecSoldeInitial();

        assertThrows(SoldeInsuffisantException.class,
                () -> wallet.debiter(new Money(new BigDecimal("200.00"), Devise.EUR)));

        assertEquals(new BigDecimal("100.00"), wallet.getSolde().getAmount());
    }

    @Test
    void crediterOuDebiterDansUneDeviseDifferenteEstRefuse() {
        Wallet wallet = walletAvecSoldeInitial();

        assertThrows(DeviseIncompatibleException.class,
                () -> wallet.debiter(new Money(new BigDecimal("10.00"), Devise.USD)));
        assertThrows(DeviseIncompatibleException.class,
                () -> wallet.crediter(new Money(new BigDecimal("10.00"), Devise.XOF)));

        assertEquals(new BigDecimal("100.00"), wallet.getSolde().getAmount());
    }

    @Test
    void debiterUnMontantNegatifEstRefuse() {
        Wallet wallet = walletAvecSoldeInitial();

        assertThrows(MontantInvalideException.class,
                () -> wallet.debiter(new Money(new BigDecimal("-5.00"), Devise.EUR)));

        assertEquals(new BigDecimal("100.00"), wallet.getSolde().getAmount());
    }
}