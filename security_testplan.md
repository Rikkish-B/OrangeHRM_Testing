# Security Testing Plan - OrangeHRM

## Vulnerability: Insecure Direct Object Reference (IDOR)

### Objective
To check if a non-admin user can access or modify other employee details by manipulating the employee ID in the URL.

### Scope
- Application under test: OrangeHRM
- Module: Employee Details

### Steps
1. Login as a **normal employee** (non-admin user).
2. Navigate to an employee details page (example: `/viewEmployee/123`).
3. Intercept the request using **Burp Suite**.
4. Modify the employee ID (`123 → 124`) and forward the request.
5. Observe if the system allows access to another employee’s data.

### Expected Result
- Access should be **restricted**.  
- Only admin users should view/edit other employees’ details.  

### Actual Result
- (Fill this after testing → e.g., *Access Denied* or *Access Granted*).  

### Status
- (Write *Pass* if secured, *Fail* if IDOR exists).
