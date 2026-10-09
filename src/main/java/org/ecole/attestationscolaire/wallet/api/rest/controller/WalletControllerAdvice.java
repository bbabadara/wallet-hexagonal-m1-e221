package org.ecole.attestationscolaire.wallet.api.rest.controller;

import org.ecole.attestationscolaire.wallet.domain.exception.DeviseIncompatibleException;
import org.ecole.attestationscolaire.wallet.domain.exception.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.exception.SoldeInsuffisantException;
import org.ecole.attestationscolaire.wallet.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletControllerAdvice {
    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(WalletNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("WALLET_NOT_FOUND", e.getMessage()));
    }

    @ExceptionHandler(MontantInvalideException.class)
    public ResponseEntity<ErrorResponse> handleMontantInvalide(MontantInvalideException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("MONTANT_INVALIDE", e.getMessage()));
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    public ResponseEntity<ErrorResponse> handleSoldeInsuffisant(SoldeInsuffisantException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("SOLDE_INSUFFISANT", e.getMessage()));
    }

    @ExceptionHandler(DeviseIncompatibleException.class)
    public ResponseEntity<ErrorResponse> handleDeviseIncompatible(DeviseIncompatibleException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse("DEVISE_INCOMPATIBLE", e.getMessage()));
    }
}
