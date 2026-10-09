package org.ecole.attestationscolaire.wallet.domain;

public class SoldeInsuffisantException extends RuntimeException {
    public SoldeInsuffisantException(String message) {
        super(message);
    }
}