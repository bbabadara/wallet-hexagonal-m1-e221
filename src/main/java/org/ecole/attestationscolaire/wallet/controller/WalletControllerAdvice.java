package org.ecole.attestationscolaire.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ecole.attestationscolaire.wallet.domain.DeviseIncompatibleException;
import org.ecole.attestationscolaire.wallet.domain.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.SoldeInsuffisantException;
import org.ecole.attestationscolaire.wallet.domain.WalletNotFoundException;

@RestControllerAdvice
public class WalletControllerAdvice {

    @ExceptionHandler(WalletNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse walletNotFound(WalletNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(MontantInvalideException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse montantInvalide(MontantInvalideException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse soldeInsuffisant(SoldeInsuffisantException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(DeviseIncompatibleException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse deviseIncompatible(DeviseIncompatibleException e) {
        return new ErrorResponse(e.getMessage());
    }
}