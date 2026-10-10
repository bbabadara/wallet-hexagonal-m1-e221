package org.ecole.attestationscolaire.wallet.domain.exception;

public class SoldeInsuffisantException extends RuntimeException {
    public SoldeInsuffisantException(String message) {
        super(message);
    }
}
