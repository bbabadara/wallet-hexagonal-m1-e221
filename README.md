# Wallet Hexagonal - API Portefeuille Virtuel

Niveau 1 (fondations) : Entité Wallet avec debit() et invariant (solde >= 0, SoldeInsuffisantException), historique des transactions via TransactionHistoryRepository (port + adapter JPA), tests du domaine sans base de données (WalletTest).

Niveau 2 (intermédiaire) : Option A - API REST propre avec DTOs uniquement dans le controller, gestion d'erreurs avec @ControllerAdvice (404 WalletNotFound, 400 MontantInvalide, 409 SoldeInsuffisant, 422 DeviseIncompatible).

Niveau 3 (avancé) : Option D - Value Object Money (montant + devise), refus explicite si devise différente sans conversion.

Branche principale : main | Branches : feature/niveau-1, feature/niveau-2, feature/niveau-3
Démarrage : ./mvnw spring-boot:run
Tests : ./mvnw test -Dtest=WalletTest
