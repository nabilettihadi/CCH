# CCH (Cyclo Club Horizon) - API de Gestion des Compétitions Cyclistes

## Description

Application Java pour la gestion des courses contre la montre de cyclisme, utilisant Spring MVC et déployée sur Tomcat.
Cette API REST permet la gestion des compétitions cyclistes individuelles, où chaque coureur concourt pour réaliser le
meilleur temps sur un parcours donné.

## Technologies

- Java 22
- Spring MVC 6.1
- Spring Data JPA 3.3
- Hibernate ORM 6.4
- PostgreSQL
- HikariCP 6.0
- ModelMapper 3.1
- JUnit 5 & Mockito
- Jakarta EE 9.1
- Lombok

## Fonctionnalités

### 1. Gestion des Compétitions

- CRUD complet des compétitions avec validation
- Gestion des dates de début et fin
- Localisation des compétitions
- Gestion des phases

## Fonctionnalités

### 1. Gestion des Équipes (Teams)

- CRUD complet des équipes
- Association avec les cyclistes
- Recherche par pays

### 2. Gestion des Coureurs (Cyclists)

- Enregistrement des informations (nom, prénom, date de naissance, nationalité)
- Association avec une équipe
- Tri par nom, nationalité ou équipe

### 3. Gestion des Compétitions

- CRUD complet des compétitions avec validation
- Gestion des étapes
- Filtrage par date ou lieu

### 4. Gestion des Résultats

- Résultats par étape
- Classement général
- Calcul automatique des classements

### 5. Rapports

- Génération de rapports de compétition
- Historique des performances des coureurs

## API Endpoints

### Competitions

- `GET /api/v1/competitions` : Récupérer toutes les compétitions
- `GET /api/v1/competitions/{id}` : Récupérer une compétition par ID
- `POST /api/v1/competitions` : Créer une nouvelle compétition
- `PUT /api/v1/competitions/{id}` : Mettre à jour une compétition
- `DELETE /api/v1/competitions/{id}` : Supprimer une compétition
- `GET /api/v1/competitions/location/{location}` : Rechercher par lieu
- `GET /api/v1/competitions/date-range?startDate=&endDate=` : Rechercher par période

### Cyclists

- `GET /api/v1/cyclists` : Récupérer tous les cyclistes
- `GET /api/v1/cyclists/{id}` : Récupérer un cycliste par ID
- `POST /api/v1/cyclists` : Créer un nouveau cycliste
- `PUT /api/v1/cyclists/{id}` : Mettre à jour un cycliste
- `DELETE /api/v1/cyclists/{id}` : Supprimer un cycliste

### Teams

- `GET /api/v1/teams` : Récupérer toutes les équipes
- `GET /api/v1/teams/{id}` : Récupérer une équipe par ID
- `POST /api/v1/teams` : Créer une nouvelle équipe
- `PUT /api/v1/teams/{id}` : Mettre à jour une équipe
- `DELETE /api/v1/teams/{id}` : Supprimer une équipe

### Phases (Stages)

- `GET /api/v1/stages` : Récupérer toutes les phases
- `GET /api/v1/stages/{id}` : Récupérer une phase par ID
- `POST /api/v1/stages` : Créer une nouvelle phase
- `PUT /api/v1/stages/{id}` : Mettre à jour une phase
- `DELETE /api/v1/stages/{id}` : Supprimer une phase

### Results

- `GET /api/v1/results` : Récupérer tous les résultats
- `GET /api/v1/results/{phaseId}/{cyclistId}` : Récupérer un résultat spécifique
- `POST /api/v1/results` : Créer un nouveau résultat
- `PUT /api/v1/results/{phaseId}/{cyclistId}` : Mettre à jour un résultat
- `DELETE /api/v1/results/{phaseId}/{cyclistId}` : Supprimer un résultat

### General Results

- `GET /api/v1/general-results` : Récupérer tous les résultats généraux
- `GET /api/v1/general-results/{competitionId}/{cyclistId}` : Récupérer un résultat général spécifique
- `POST /api/v1/general-results` : Créer un nouveau résultat général
- `PUT /api/v1/general-results/{competitionId}/{cyclistId}` : Mettre à jour un résultat général
- `DELETE /api/v1/general-results/{competitionId}/{cyclistId}` : Supprimer un résultat général

## Installation

### Prérequis

- JDK 22
- Maven 3.11+
- PostgreSQL
- Tomcat 10+

### Configuration

bash

git clone https://github.com/nabilettihadi/CCH.git

cd CCH

mvn clean install

## Architecture

- Controllers : Gestion des requêtes HTTP et validation
- Services : Logique métier et transactions
- Repositories : Accès aux données via JPA
- DTOs : Transfert des données entre couches
- Entities : Modèles de données JPA

## Validation des données

- Jakarta Validation pour les DTOs et entités
- Validation personnalisée dans les services
- Gestion des erreurs avec ResponseEntity

## Sécurité

- Validation des entrées
- Gestion des transactions
- Protection contre les injections SQL via JPA

## Contribution

1. Fork le projet
2. Créez votre branche (`git checkout -b feature/nouvelle-fonctionnalite`)
3. Committez vos changements
4. Push vers la branche
5. Créez une Pull Request