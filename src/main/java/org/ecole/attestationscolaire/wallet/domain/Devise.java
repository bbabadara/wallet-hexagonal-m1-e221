package org.ecole.attestationscolaire.wallet.domain;

public enum Devise {
    EUR, USD, XOF, MAD, CHF;

    public static Devise fromString(String value) {
        if (value == null) throw new IllegalArgumentException("Devise invalide");
        try {
            return Devise.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Devise invalide : " + value);
        }
    }
}
