# Imboni Ikimina – Backend API

**Student:** ICYEZA Vanessa
**Student ID:** 27901
**Project:** Imboni Ikimina
**Course:** INSY 8322 Web Technology and Internet – AUCA

Imboni Ikimina manages the savings and emergency loans of village savings groups in Rwanda.
Built with Spring Boot, Spring Data JPA and PostgreSQL.

## Entities
LoanProduct, SavingsGroup, Treasurer, Member, Loan, Repayment, and the enum LoanStatus (REQUESTED, APPROVED, DISBURSED, REJECTED).

## How to run
1. Create the database in PostgreSQL:
   ```sql
   CREATE DATABASE imboni_ikimina;
   ```
2. In `src/main/resources/application.properties`, set your PostgreSQL username and password.
3. Run the application:
   - Windows: `.\mvnw.cmd spring-boot:run`
   - Linux/Mac: `./mvnw spring-boot:run`
4. The API runs at `http://localhost:8080`. Tables are created automatically (`ddl-auto=update`).
5. Import `imboni-ikimina-midterm.postman_collection.json` into Postman. There is one request per requirement (A2, B1 … F2, BONUS-1, BONUS-2).

erDiagram
    SAVINGS_GROUP ||--o{ TREASURER : "savings_group_id (nullable)"
    TREASURER ||--o{ TREASURER_LOAN_PRODUCT : "treasurer_id"
    LOAN_PRODUCT ||--o{ TREASURER_LOAN_PRODUCT : "loan_product_id"
    MEMBER ||--o{ LOAN : "member_id"
    TREASURER ||--o{ LOAN : "treasurer_id"
    LOAN ||--o{ REPAYMENT : "loan_id"

    LOAN_PRODUCT {
        UUID id PK
        String name
    }
    SAVINGS_GROUP {
        UUID id PK
        int groupNumber
        String name
    }
    TREASURER {
        UUID id PK
        String firstName
        String lastName
        String phone
        UUID savings_group_id FK
    }
    TREASURER_LOAN_PRODUCT {
        UUID treasurer_id FK
        UUID loan_product_id FK
    }
    MEMBER {
        UUID id PK
        String firstName
        String lastName
        String memberNumber UK
        String phone
        String village
    }
    LOAN {
        UUID id PK
        LocalDate requestDate
        LoanStatus status "REQUESTED, APPROVED, DISBURSED, REJECTED"
        double amount
        String purpose
        UUID member_id FK
        UUID treasurer_id FK
    }
    REPAYMENT {
        UUID id PK
        String paymentChannel
        String note
        int amount
        LocalDate paidDate
        UUID loan_id FK
    } How can I put this in my read me and it come as atabble of the relationship
## Endpoints
| ID | Method | Endpoint |
|---|---|---|
| A2 | POST/GET/PUT/DELETE | `/api/{loan-products, savings-groups, treasurers, members, loans}/save, /all, /{id}, /update/{id}, /delete/{id}` |
| B1 | GET | `/api/members/by-last-name?lastName=` |
| B2 | GET | `/api/loans/by-status?status=` |
| B3 | GET | `/api/loans/between?start=&end=` |
| C1 | GET | `/api/treasurers/by-loan-product?name=` |
| C2 | GET | `/api/savings-groups/free` |
| C3 | GET | `/api/loan-products/unused` |
| C4 | GET | `/api/members/of-treasurer/{treasurerId}` |
| D1 | POST | `/api/loans/save` |
| D2 | PATCH | `/api/loans/{id}/status?status=` |
| D3 | PUT | `/api/treasurers/{treasurerId}/loan-products/{loanProductId}` |
| D4 | PATCH | `/api/treasurers/{treasurerId}/savings-group?groupNumber=` |
| E1 | POST | `/api/repayments/save?loanId=` |
| E2 | GET | `/api/repayments/member/{memberNumber}` |
| E3 | DELETE | `/api/repayments/delete/{id}` and `/api/loans/delete/{id}` |
| F1 | GET | `/api/loans/stats/by-status` |
| F2 | PATCH | `/api/loans/cancel-day?treasurerId=&date=` |
| Bonus | GET | `/api/dashboard/summary` |
| Bonus | GET | `/api/loans/page?page=0&size=5&sort=requestDate,desc` |
