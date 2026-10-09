package org.ecole.attestationscolaire.wallet.domain;

import org.ecole.attestationscolaire.wallet.domain.exception.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.exception.SoldeInsuffisantException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void crediter_should_increase_balance() {
        Wallet wallet = new Wallet(1L, new BigDecimal("100.00"), "Alice");
        BigDecimal soldeAvant = wallet.getSolde();
        wallet.crediter(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), wallet.getSolde());
        assertTrue(wallet.getSolde().compareTo(soldeAvant) > 0);
    }

    @Test
    void debiter_should_decrease_balance_when_sufficient() {
        Wallet wallet = new Wallet(1L, new BigDecimal("100.00"), "Bob");
        wallet.debiter(new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), wallet.getSolde());
    }

    @Test
    void debiter_should_throw_when_insufficient_and_not_change_balance() {
        Wallet wallet = new Wallet(1L, new BigDecimal("50.00"), "Charlie");
        BigDecimal soldeAvant = wallet.getSolde();
        assertThrows(SoldeInsuffisantException.class, () -> wallet.debiter(new BigDecimal("100.00")));
        assertEquals(soldeAvant, wallet.getSolde());
        assertTrue(soldeAvant.signum() >= 0);
    }
}
