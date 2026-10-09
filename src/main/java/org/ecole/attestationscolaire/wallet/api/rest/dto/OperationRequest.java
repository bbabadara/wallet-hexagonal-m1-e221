package org.ecole.attestationscolaire.wallet.api.rest.dto;

import java.math.BigDecimal;

public record OperationRequest(BigDecimal montant) {
}
