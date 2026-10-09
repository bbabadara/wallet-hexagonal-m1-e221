package org.ecole.attestationscolaire.wallet.domain;

public class DeviseIncompatibleException extends RuntimeException {
    public DeviseIncompatibleException(String message) {
        super(message);
    }
}