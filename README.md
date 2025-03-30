# Electronics Store Management System 🛒

A web application for managing electronics store networks with sales analytics and data management capabilities. Built as a pet project to demonstrate Spring Boot, microservices architecture, and PostgreSQL integration.

*Sample Application Architecture*

## 🔨 Tech Stack
- **Backend**: 
  - Java 11, Spring Boot 2.7, Spring Data JPA, Hibernate
  - Microservices architecture (separate modules for core entities)
  - API Documentation: Swagger (springdoc-openapi)
- **Frontend**: 
  - Minimal HTML/CSS interface for testing
- **Database**: PostgreSQL with Liquibase migrations
- **Extras**: 
  - CSV/ZIP data import
  - Custom SQL queries for analytics

## 🌟 Key Features
### Data Management
- CRUD operations for:
  - Employees (with position/store assignments)
  - Electronics (with archival status checks)
  - Purchases (with date/price filtering)
- Reference tables:
  - Positions (e.g., "Junior Sales Associate")
  - Electronics types ("Smartwatches", "Smartphones")
  - Store locations

### Sales Analytics
```sql
-- Top employees query example
SELECT e.id, COUNT(p.id) as sales 
FROM purchases p 
JOIN employees e ON p.employee_id = e.id 
WHERE p.date > NOW() - INTERVAL '1 YEAR'
GROUP BY e.id 
ORDER BY sales DESC LIMIT 5;
```

## 🚀 Getting Started

```bash
git clone https://github.com/yourusername/electronics-store-system.git
```

Configure PostgreSQL in application.yml

Run with Docker:
```bash
docker-compose up --build
```
Access interfaces:
```
Swagger UI: http://localhost:8080/swagger-ui.html

Web UI: http://localhost:8080/ui
```
## 📊 Usage Examples
1. Adding Employee

- Add Employee
2. Sales Report
```http
GET /api/reports/top-employees?year=2023
```

```json

{
"employee": "John Doe",
"position": "Sales Associate",
"totalSales": 142
}
```

