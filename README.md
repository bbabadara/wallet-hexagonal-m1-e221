# Wallet Hexagonal — API Portefeuille Virtuel

Niveau 1 (fondations) : entité `Wallet` avec invariant de solde (`SoldeInsuffisantException`), historique des transactions (`TransactionHistoryRepository` Port + adapter JPA) et tests du domaine sans base de données.

Niveau 2 : Option A — API REST propre (DTOs uniquement dans le controller, `@ControllerAdvice` : 404 wallet introuvable, 400 montant invalide, 409 solde insuffisant, 422 devise incompatible).

Niveau 3 : Option D — Value Object `Money` (montant + devise), une devise différente de celle du wallet est refusée sans conversion explicite.

Branche : `feature/wallet` — Lancement : `./mvnw spring-boot:run` (endpoints `POST /wallets`, `GET /wallets/{id}`, `POST /wallets/{id}/credits`, `POST /wallets/{id}/debits`) — Tests : `./mvnw test`.