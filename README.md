<div align="center">
  <h1>🚴‍♂️ CCH (Cyclo Club Horizon) API</h1>
  <p>API REST pour la gestion des compétitions cyclistes</p>
</div>

## 🌟 À propos

Application Java pour la gestion des courses contre la montre de cyclisme, utilisant Spring MVC et déployée sur Tomcat.
Cette API REST permet la gestion des compétitions cyclistes individuelles, où chaque coureur concourt pour réaliser le
meilleur temps sur un parcours donné.

## 👨‍💻 Développeur

<div align="center">
  <img src="https://avatars.githubusercontent.com/nabilettihadi" width="100px" style="border-radius:50%"/>
  <h3>Nabil Ettihadi</h3>
  <p>Full-Stack Developer</p>

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://linkedin.com/in/nabil-ettihadi)
[![Email](https://img.shields.io/badge/Email-Contact-red)](mailto:nettihadi@gmail.com)
</div>

## 📋 Table des matières

- [À propos](#-à-propos)
- [Développeur](#-développeur)
- [Technologies](#️-technologies)
- [Fonctionnalités](#-fonctionnalités)
- [API Endpoints](#-api-endpoints)
- [Installation](#-installation)
- [Tests](#-tests)
- [Architecture](#-architecture)
- [Contribution](#-contribution)

## 🛠️ Technologies

![Java](https://img.shields.io/badge/Java-22-orange)
![Spring MVC](https://img.shields.io/badge/Spring%20MVC-6.1-green)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.3-lightgreen)
![Hibernate](https://img.shields.io/badge/Hibernate-6.4-yellow)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue)
![HikariCP](https://img.shields.io/badge/HikariCP-6.0-purple)
![ModelMapper](https://img.shields.io/badge/ModelMapper-3.1-red)
![JUnit](https://img.shields.io/badge/JUnit-5-brightgreen)
![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-9.1-blue)
![Lombok](https://img.shields.io/badge/Lombok-Latest-blueviolet)

## 📝 Fonctionnalités

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

## 🔌 API Endpoints

### Compétitions

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

## 🚀 Installation

### Prérequis

- JDK 22
- Maven 3.11+
- PostgreSQL
- Tomcat 10+
- IDE (IntelliJ IDEA recommandé)

### Configuration

bash

git clone https://github.com/nabilettihadi/CCH.git

cd CCH

mvn clean install

## 🧪 Tests

Exemple de test unitaire :

java:src/test/java/com/cch/cyclingmanager/CompetitionServiceTest.java
java:src/test/java/com/cch/cyclingmanager/CyclistServiceTest.java
java:src/test/java/com/cch/cyclingmanager/GeneralResultServiceTest.java
java:src/test/java/com/cch/cyclingmanager/PhaseServiceTest.java
java:src/test/java/com/cch/cyclingmanager/ResultServiceTest.java
java:src/test/java/com/cch/cyclingmanager/TeamServiceTest.java

## 🏗 Architecture

### Structure du Projet

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

## 🤝 Contribution

1. Fork le projet
2. Créer une branche (`git checkout -b feature/AmazingFeature`)
3. Commit (`git commit -m 'Add some AmazingFeature'`)
4. Push (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request