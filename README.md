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
5. adede `imboni-ikimina-midterm.postman_collection.json` my postman There is one request per requirement (A2, B1 … F2, BONUS-1, BONUS-2).

### Relationships

| Parent | Relationship | Child | Foreign Key |
|---|---|---|---|
| SAVINGS_GROUP | one-to-many | TREASURER | `savings_group_id` (nullable) |
| TREASURER | one-to-many | TREASURER_LOAN_PRODUCT | `treasurer_id` |
| LOAN_PRODUCT | one-to-many | TREASURER_LOAN_PRODUCT | `loan_product_id` |
| MEMBER | one-to-many | LOAN | `member_id` |
| TREASURER | one-to-many | LOAN | `treasurer_id` |
| LOAN | one-to-many | REPAYMENT | `loan_id` |

### Entities

**LOAN_PRODUCT**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| name | String | |

**SAVINGS_GROUP**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| groupNumber | int | |
| name | String | |

**TREASURER**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| firstName | String | |
| lastName | String | |
| phone | String | |
| savings_group_id | UUID | FK (nullable) |

**TREASURER_LOAN_PRODUCT** (join table)

| Column | Type | Key |
|---|---|---|
| treasurer_id | UUID | FK |
| loan_product_id | UUID | FK |

**MEMBER**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| firstName | String | |
| lastName | String | |
| memberNumber | String | UK |
| phone | String | |
| village | String | |

**LOAN**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| requestDate | LocalDate | |
| status | LoanStatus | REQUESTED, APPROVED, DISBURSED, REJECTED |
| amount | double | |
| purpose | String | |
| member_id | UUID | FK |
| treasurer_id | UUID | FK |

**REPAYMENT**

| Column | Type | Key |
|---|---|---|
| id | UUID | PK |
| paymentChannel | String | |
| note | String | |
| amount | int | |
| paidDate | LocalDate | |
| loan_id | UUID | FK |

Note: TREASURER and LOAN_PRODUCT have a many-to-many relationship through the TREASURER_LOAN_PRODUCT join table.
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
