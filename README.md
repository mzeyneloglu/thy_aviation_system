## Aviation Route Planning System

Full-stack aviation route planning application with Spring Boot, PostgreSQL, and React.

### Database Configuration

#### PostgreSQL Connection Details

##### Application User (CRUD Operations)
```
Host: localhost
Port: 5432
Database: aviation_system
Username: aviation_app_user
Password: thy_password52
Connection Limit: 10
Permissions: SELECT, INSERT, UPDATE, DELETE
```

##### Read-Only User (Reports & Analytics)
```
Host: localhost
Port: 5432
Database: aviation_system
Username: aviation_readonly_user
Password: thy_read_only_password52
Connection Limit: 5
Permissions: SELECT only
```

