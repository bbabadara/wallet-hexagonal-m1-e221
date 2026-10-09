package org.ecole.attestationscolaire.wallet.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;
    private final Devise devise;

    public Money(BigDecimal amount, Devise devise) {
        this.amount = Objects.requireNonNull(amount, "amount");
        this.devise = Objects.requireNonNull(devise, "devise");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Devise getDevise() {
        return devise;
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    public boolean isGreaterThan(Money other) {
        ensureSameDevise(other);
        return amount.compareTo(other.amount) > 0;
    }

    public Money plus(Money other) {
        ensureSameDevise(other);
        return new Money(amount.add(other.amount), devise);
    }

    public Money minus(Money other) {
        ensureSameDevise(other);
        return new Money(amount.subtract(other.amount), devise);
    }

    private void ensureSameDevise(Money other) {
        if (this.devise != other.devise) {
            throw new DeviseIncompatibleException(
                    "Devise incompatible : " + other.devise + " != " + this.devise);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return amount.compareTo(money.amount) == 0 && devise == money.devise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), devise);
    }

    @Override
    public String toString() {
        return amount.toPlainString() + " " + devise;
    }
}