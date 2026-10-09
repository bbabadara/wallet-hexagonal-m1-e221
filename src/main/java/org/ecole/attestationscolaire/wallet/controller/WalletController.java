package org.ecole.attestationscolaire.wallet.controller;

import org.ecole.attestationscolaire.wallet.domain.Devise;
import org.ecole.attestationscolaire.wallet.domain.Money;
import org.ecole.attestationscolaire.wallet.domain.MontantInvalideException;
import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.application.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletResponse creerWallet(@RequestBody CreateWalletRequest request) {
        Wallet wallet = walletService.creerWallet(
                request.titulaire(), toMoney(request.montant(), request.devise()));
        return toResponse(wallet);
    }

    @GetMapping("/{id}")
    public WalletResponse getWallet(@PathVariable Long id) {
        return toResponse(walletService.getWallet(id));
    }

    @PostMapping("/{id}/credits")
    public WalletResponse crediter(@PathVariable Long id, @RequestBody OperationRequest request) {
        return toResponse(walletService.crediter(id, toMoney(request.montant(), request.devise())));
    }

    @PostMapping("/{id}/debits")
    public WalletResponse debiter(@PathVariable Long id, @RequestBody OperationRequest request) {
        return toResponse(walletService.debiter(id, toMoney(request.montant(), request.devise())));
    }

    private Money toMoney(BigDecimal montant, String devise) {
        try {
            return new Money(montant, Devise.valueOf(devise));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new MontantInvalideException("Montant ou devise invalide : " + montant + " " + devise);
        }
    }

    private WalletResponse toResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getTitulaire(),
                wallet.getSolde().getAmount(),
                wallet.getSolde().getDevise().name());
    }
}