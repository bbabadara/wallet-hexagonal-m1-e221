package org.ecole.attestationscolaire.wallet.domain.exception;

public class MontantInvalideException extends RuntimeException {
    public MontantInvalideException(String message) {
        super(message);
    }
}
