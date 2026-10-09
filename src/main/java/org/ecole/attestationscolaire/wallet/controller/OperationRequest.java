package org.ecole.attestationscolaire.wallet.controller;

import java.math.BigDecimal;

public record OperationRequest(BigDecimal montant, String devise) {
}