package org.ecole.attestationscolaire.wallet.api.rest.controller;

import org.ecole.attestationscolaire.wallet.api.rest.dto.CreateWalletRequest;
import org.ecole.attestationscolaire.wallet.api.rest.dto.OperationRequest;
import org.ecole.attestationscolaire.wallet.api.rest.dto.WalletResponse;
import org.ecole.attestationscolaire.wallet.domain.Wallet;
import org.ecole.attestationscolaire.wallet.domain.application.WalletService;
import org.ecole.attestationscolaire.wallet.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody CreateWalletRequest request) {
        Wallet wallet = walletService.createWallet(request.titulaire(), request.soldeInitial());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(wallet));
    }

    @GetMapping("/{id}")
    public WalletResponse get(@PathVariable Long id) {
        return walletService.getWallet(id)
                .map(this::toResponse)
                .orElseThrow(() -> new WalletNotFoundException("Wallet introuvable : " + id));
    }

    @PostMapping("/{id}/credits")
    public WalletResponse credit(@PathVariable Long id, @RequestBody OperationRequest request) {
        Wallet wallet = walletService.credit(id, request.montant());
        return toResponse(wallet);
    }

    @PostMapping("/{id}/debits")
    public WalletResponse debit(@PathVariable Long id, @RequestBody OperationRequest request) {
        Wallet wallet = walletService.debit(id, request.montant());
        return toResponse(wallet);
    }

    private WalletResponse toResponse(Wallet wallet) {
        return new WalletResponse(wallet.getId(), wallet.getTitulaire(), wallet.getSolde());
    }
}
