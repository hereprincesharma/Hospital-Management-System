# Hospital Management System (HMS)

## 1. Project Overview

**Project Name:** Hospital Management System\
**Project Type:** College Mini Project / Web Application\
**Frontend:** HTML, CSS, JavaScript\
**Backend:** Java\
**Database:** MySQL\
**Database Connectivity:** JDBC\
**Version Control:** Git + GitHub

### Objective

A web-based system to manage patients, doctors, appointments, medicines,
prescriptions and billing. Development will follow: **Frontend → Backend
→ Database → Integration → Testing → Documentation**.

------------------------------------------------------------------------

## 2. Architecture

The project uses a simple 3-layer architecture:

``` text
USER
  |
  v
FRONTEND
HTML + CSS + JavaScript
  |
  | HTTP / Request
  v
BACKEND
Java
Controller -> Service -> DAO
  |
  | JDBC
  v
MYSQL DATABASE
```

### Responsibilities

**Frontend** - Pages, forms and dashboard UI - Basic client-side
validation - Sends requests to backend

**Backend** - Handles requests - Business logic and validation -
Database communication through JDBC

**Database** - Permanently stores hospital data

------------------------------------------------------------------------

## 3. UI Theme

Use a **modern, clean, professional healthcare theme**.

### Design system

-   Primary: Medical Blue
-   Secondary: Light Blue
-   Background: White / Very Light Gray
-   Text: Dark Navy / Dark Gray
-   Success: Green
-   Warning: Orange
-   Error: Red
-   White cards with soft shadows
-   Medium rounded corners
-   Consistent buttons and spacing
-   Simple readable fonts
-   Responsive layout

### Avoid

-   Too many colors
-   Excessive animations
-   Random fonts
-   Different button styles on different pages
-   Unnecessary gradients

The goal is a professional hospital dashboard, not a basic HTML
assignment.

------------------------------------------------------------------------

# 4. Frontend Structure

``` text
frontend/
|
|-- index.html
|-- login.html
|-- dashboard.html
|-- patients.html
|-- doctors.html
|-- appointments.html
|-- medicines.html
|-- billing.html
|
|-- css/
|   `-- style.css
|
|-- js/
|   `-- script.js
|
`-- assets/
    |-- images/
    `-- icons/
```

## File Responsibilities

### index.html

Public landing page: - Hospital name/logo - Introduction - Services -
Departments - Facilities - Contact information - Login button

### login.html

Login UI: - Username/email - Password - Remember me - Forgot password -
Login button - Back to home

Initially this is only UI. Real authentication comes later.

### dashboard.html

Main administration dashboard: - Sidebar - Header - Total patients -
Total doctors - Today's appointments - Pending appointments - Recent
appointments - Quick actions

### patients.html

Patient management: - View/add/edit/delete patient - Search patient -
Patient details

Suggested fields:
`Patient ID, Name, Age, Gender, Phone, Email, Address, Blood Group, Registration Date`

### doctors.html

Doctor management: - View/add/edit/delete doctor - Search doctor -
Doctor details

Suggested fields:
`Doctor ID, Name, Specialization, Department, Phone, Email, Availability`

### appointments.html

Appointment management: - Book appointment - View/update/cancel
appointment - Search/filter

Suggested fields:
`Appointment ID, Patient, Doctor, Date, Time, Department, Status`

Statuses: `Scheduled, Completed, Cancelled, Pending`

### medicines.html

Medicine management: - Add/view/update/delete - Search - Quantity
tracking

Suggested fields:
`Medicine ID, Medicine Name, Category, Quantity, Price, Expiry Date`

### billing.html

Billing: - Create/view bill - Calculate total - Payment status

Suggested fields:
`Bill ID, Patient, Doctor, Consultation Fee, Medicine Charges, Other Charges, Total Amount, Payment Status`

------------------------------------------------------------------------

# 5. CSS

``` text
frontend/css/style.css
```

Keep common styling here: - Buttons - Forms - Cards - Tables - Sidebar -
Navbar - Alerts - Modals - Responsive layout

Use one consistent design system across all pages.

------------------------------------------------------------------------

# 6. JavaScript

``` text
frontend/js/script.js
```

Initially handles: - Form validation - Search/filter - Show/hide
password - Sidebar/menu interactions - Modal interactions - Basic UI
behavior

Later it will communicate with the Java backend.

``` text
HTML Form
   |
   v
JavaScript
   |
   v
Java Backend
   |
   v
MySQL
```

------------------------------------------------------------------------

# 7. Backend Structure

``` text
backend/
|
|-- pom.xml
|
`-- src/
    `-- main/
        `-- java/
            `-- com/
                `-- hospital/
                    |-- controller/
                    |-- model/
                    |-- dao/
                    |-- service/
                    `-- Main.java
```

### model/

Java classes representing data:

``` text
Patient.java
Doctor.java
Appointment.java
Medicine.java
Bill.java
User.java
```

### dao/

**DAO = Data Access Object**

Contains database operations:

``` text
PatientDAO.java
DoctorDAO.java
AppointmentDAO.java
MedicineDAO.java
BillDAO.java
```

CRUD: `Create, Read, Update, Delete`

### service/

Business logic:

``` text
PatientService.java
DoctorService.java
AppointmentService.java
BillingService.java
```

Flow:

``` text
Controller -> Service -> DAO -> MySQL
```

### controller/

Handles frontend requests and returns responses.

### Main.java

Backend application entry point, depending on final Java setup.

------------------------------------------------------------------------

# 8. Database

``` text
database/
`-- hospital.sql
```

Planned tables:

``` text
users
patients
doctors
departments
appointments
medicines
prescriptions
bills
```

Basic relationships:

``` text
Department
  |-- Doctors
  `-- Appointments

Patient
  |-- Appointments
  |-- Prescriptions
  `-- Bills

Doctor
  |-- Appointments
  `-- Prescriptions
```

The exact schema will be finalized before integration.

------------------------------------------------------------------------

# 9. User Roles

## Admin

-   Manage doctors
-   Manage patients
-   Manage departments
-   Manage appointments
-   Manage medicines
-   Manage billing
-   Manage users

## Doctor

-   View appointments
-   View assigned patients
-   Add diagnosis
-   Add prescription
-   View patient information

## Patient

-   View profile
-   View doctors
-   Book appointment
-   View appointments
-   View prescriptions
-   View bills

Start with a simple login UI. Real role-based authentication comes
later.

------------------------------------------------------------------------

# 10. Development Phases

## Phase 1 --- Planning

-   Requirements
-   Modules
-   UI theme
-   Database design
-   Folder structure

## Phase 2 --- Frontend

``` text
1. Landing Page
2. Login
3. Dashboard
4. Patients
5. Doctors
6. Appointments
7. Medicines
8. Billing
```

## Phase 3 --- Backend

``` text
1. Java setup
2. Maven/dependencies
3. Database connection
4. Models
5. DAO
6. Services
7. Controllers
8. Authentication
```

## Phase 4 --- Database

``` text
1. Create database
2. Create tables
3. Relationships
4. Test data
5. CRUD testing
```

## Phase 5 --- Integration

``` text
Frontend -> Java Backend -> MySQL
```

## Phase 6 --- Testing

Test login, CRUD operations, appointments, medicines, billing,
validation and error handling.

## Phase 7 --- Documentation

Prepare report, screenshots, architecture diagram, database diagram,
features, testing results and GitHub README.

------------------------------------------------------------------------

# 11. Git / GitHub Team Workflow

**Do not let everyone directly work on `main`.**

Use feature branches:

``` text
main
 |
 |-- feature/login
 |-- feature/dashboard
 |-- feature/patients
 |-- feature/doctors
 |-- feature/appointments
 |-- feature/medicines
 `-- feature/billing
```

### Typical workflow

``` bash
git pull origin main
git checkout -b feature/patients
```

Work on the feature, then:

``` bash
git add .
git commit -m "Add patient management UI"
git push -u origin feature/patients
```

Create a Pull Request on GitHub and merge after review.

------------------------------------------------------------------------

# 12. Suggested Team Division

### Member 1 --- Frontend / UI

-   Landing page
-   Login
-   Common CSS
-   Responsive design

### Member 2 --- Patients & Doctors

-   Patient page
-   Doctor page
-   Related forms

### Member 3 --- Appointments & Medicines

-   Appointment page
-   Medicine page
-   Search/filter UI

### Member 4 --- Backend & Database

-   Java setup
-   JDBC
-   MySQL
-   DAO/service implementation

Change the division according to team size and skills.

------------------------------------------------------------------------

# 13. Commit Convention

Good commit messages:

``` text
Add hospital landing page
Add login page UI
Create dashboard layout
Add patient management UI
Add doctor management UI
Add appointment module
Create MySQL database schema
Add JDBC connection
Implement patient CRUD
Implement doctor CRUD
Fix appointment validation
Update project documentation
```

Avoid:

``` text
update
changes
final
final2
new
abc
test
```

------------------------------------------------------------------------

# 14. Development Rules

1.  Keep `main` stable.
2.  Create a feature branch for major features.
3.  Pull the latest `main` before starting work.
4.  Do not overwrite another member's work.
5.  Use meaningful commit messages.
6.  Test before creating a Pull Request.
7.  Keep frontend, backend and database responsibilities separate.
8.  Never commit passwords, API keys or other secrets.
9.  Keep reusable CSS and JavaScript in common files.
10. Avoid unnecessary features just to increase project size.

------------------------------------------------------------------------

# 15. Final Folder Structure

``` text
Hospital-Management-System/
|
|-- README.md
|-- .gitignore
|
|-- frontend/
|   |-- index.html
|   |-- login.html
|   |-- dashboard.html
|   |-- patients.html
|   |-- doctors.html
|   |-- appointments.html
|   |-- medicines.html
|   |-- billing.html
|   |
|   |-- css/
|   |   `-- style.css
|   |
|   |-- js/
|   |   `-- script.js
|   |
|   `-- assets/
|       |-- images/
|       `-- icons/
|
|-- backend/
|   |-- pom.xml
|   `-- src/
|       `-- main/
|           `-- java/
|               `-- com/
|                   `-- hospital/
|                       |-- controller/
|                       |-- model/
|                       |-- dao/
|                       |-- service/
|                       `-- Main.java
|
|-- database/
|   `-- hospital.sql
|
`-- docs/
    `-- project-documentation.md
```

------------------------------------------------------------------------

# 16. Current Status

``` text
[✓] GitHub repository
[✓] Local project connected
[✓] Basic folder structure
[✓] Frontend started
[✓] index.html
[✓] login.html
[✓] Common CSS started

[ ] Dashboard
[ ] Patient module
[ ] Doctor module
[ ] Appointment module
[ ] Medicine module
[ ] Billing module
[ ] Java backend
[ ] MySQL database
[ ] JDBC connection
[ ] Frontend-backend integration
[ ] Testing
[ ] Final documentation
```

## Team Goal

Build the project step-by-step:

**Frontend → Backend → Database → Integration → Testing →
Documentation**

Every major feature should be committed and pushed to GitHub so the
complete development history is maintained.
