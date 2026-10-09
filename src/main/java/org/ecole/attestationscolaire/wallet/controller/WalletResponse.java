package org.ecole.attestationscolaire.wallet.controller;

import java.math.BigDecimal;

public record WalletResponse(Long id, String titulaire, BigDecimal montant, String devise) {
}