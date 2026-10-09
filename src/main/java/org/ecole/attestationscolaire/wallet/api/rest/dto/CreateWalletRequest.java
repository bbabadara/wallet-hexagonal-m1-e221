package org.ecole.attestationscolaire.wallet.api.rest.dto;

import java.math.BigDecimal;

public record CreateWalletRequest(String titulaire, BigDecimal soldeInitial) {
}
