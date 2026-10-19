# AffiliateLink — Affiliate Marketing Platform

A modular affiliate marketing platform built with Java, Spring Boot, and PostgreSQL.

## 📌 Project Overview

AffiliateLink is a platform designed to connect advertisers with publishers. Advertisers can manage their promotional offers, while publishers can discover opportunities and generate affiliate links to track their performance.

This project is developed as part of **Sprint 3 — Brief 2**.

## 🎯 Objectives

- Manage publisher and advertiser profiles.
- Manage advertising offers.
- Handle affiliate relationships and tracking links.
- Apply validation and business rules.
- Build a REST API using a modular architecture.
- Ensure data integrity and consistent error handling.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **PostgreSQL**
- **Flyway Migration**
- **Maven**
- **Docker & Docker Compose**
- **Spring Modulith**

## 🏗️ Architecture

The project follows a **domain-oriented package structure** rather than a package-by-layer structure.

```text
ma.youcode.affiliatelink
├── shared
│   ├── error
│   └── exception
├── profile
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── offer
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
└── affiliate
    ├── controller
    ├── dto
    ├── entity
    ├── repository
    └── service
```

Each domain contains its own controllers, DTOs, entities, repositories, and services.

## 📦 Modules

### 1. Profile

Responsible for managing platform profiles.

- **Publisher:** first name, last name, niche, bio, and country.
- **Advertiser:** company name, sector, and website.
- Unique email addresses across all profiles.
- Profile status management.
- Input validation and standardized API errors.

Supported profile statuses:

`PENDING` → `ACTIVE` → `SUSPENDED`

A suspended profile can be reactivated. Profiles are suspended rather than deleted.

### 2. Offer

Responsible for managing advertising offers created by advertisers.

### 3. Affiliate

Responsible for managing publisher–offer relationships and affiliate tracking links.

## 🌐 Profile API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/publishers` | Create a publisher |
| GET | `/api/publishers` | List publishers |
| GET | `/api/publishers/{id}` | Get a publisher by ID |
| POST | `/api/advertisers` | Create an advertiser |
| GET | `/api/advertisers` | List advertisers |
| GET | `/api/advertisers/{id}` | Get an advertiser by ID |
| PATCH | `/api/admin/profiles/{id}/status` | Update profile status |

## ✅ Development Guidelines

- Use constructor-based dependency injection.
- Use Java records for request and response DTOs.
- Never expose JPA entities directly through the API.
- Apply Bean Validation to incoming requests.
- Keep business rules inside the domain/service layer.
- Use centralized exception handling.
- Manage database schema changes with Flyway.
- Use PostgreSQL for persistence.
- Organize the application as a modular monolith.

## 🚀 Getting Started

### Prerequisites

- JDK 17
- Maven
- Docker Desktop
- Git

### Clone the repository

```bash
git clone https://github.com/mouadziyani/affiliatelink.git
cd affiliatelink
```

### Run the application

Once the database configuration and Docker Compose setup are ready:

```bash
docker compose up -d
```

Then start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

> Make sure the required dependencies and application configuration are in place before running the application.

## 🌿 Git Workflow

- `main` — stable version.
- `develop` — integration branch.
- `feature/profile` — profile module development.
- `feature/offer` — offer module development.
- `feature/affiliate` — affiliate module development.

Feature branches are merged into `develop` after implementation and testing.

## 📍 Project Status

**Current focus:** Profile module — publishers, advertisers, DTOs, validation, and REST API.

Further modules will be implemented progressively.

---

Developed by **Mouad Ziyani**  
Sprint 3 — Brief 2
