package org.ecole.attestationscolaire.wallet.domain;

public class MontantInvalideException extends RuntimeException {
    public MontantInvalideException(String message) {
        super(message);
    }
}