package org.ecole.attestationscolaire.wallet.api.rest.dto;

import java.math.BigDecimal;

public record WalletResponse(Long id, String titulaire, BigDecimal solde) {
}
