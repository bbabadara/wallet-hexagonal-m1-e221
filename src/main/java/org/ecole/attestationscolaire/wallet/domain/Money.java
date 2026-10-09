package org.ecole.attestationscolaire.wallet.domain;

import org.ecole.attestationscolaire.wallet.domain.exception.DeviseIncompatibleException;
import org.ecole.attestationscolaire.wallet.domain.exception.MontantInvalideException;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;
    private final Devise devise;

    public Money(BigDecimal amount, Devise devise) {
        if (amount == null || amount.signum() < 0) {
            throw new MontantInvalideException("Montant invalide : " + amount);
        }
        this.amount = amount;
        this.devise = Objects.requireNonNull(devise, "devise ne peut pas etre null");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Devise getDevise() {
        return devise;
    }

    public Money plus(Money other) {
        requireSameDevise(other);
        return new Money(this.amount.add(other.amount), this.devise);
    }

    public Money minus(Money other) {
        requireSameDevise(other);
        return new Money(this.amount.subtract(other.amount), this.devise);
    }

    public boolean isGreaterThan(Money other) {
        requireSameDevise(other);
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    private void requireSameDevise(Money other) {
        if (other == null || this.devise != other.devise) {
            throw new DeviseIncompatibleException("Devise incompatible : " + (other == null ? "null" : other.devise) + " != " + this.devise);
        }
    }

    @Override
    public String toString() {
        return amount + " " + devise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.equals(money.amount) && devise == money.devise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, devise);
    }
}
