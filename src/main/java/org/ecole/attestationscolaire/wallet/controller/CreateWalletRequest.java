package org.ecole.attestationscolaire.wallet.controller;

import java.math.BigDecimal;

public record CreateWalletRequest(String titulaire, BigDecimal montant, String devise) {
}