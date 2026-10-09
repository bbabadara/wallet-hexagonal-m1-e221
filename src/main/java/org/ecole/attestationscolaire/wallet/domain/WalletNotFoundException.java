package org.ecole.attestationscolaire.wallet.domain;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}