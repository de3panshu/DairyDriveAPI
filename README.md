
# Dairy Drive API

**Dairy Drive API** is a Spring Boot application designed to help milk delivery personnel manage orders, deliveries, inventory, and transactions. It supports different pricing for the same product for various customers, making it easier for businesses to handle multiple clients and pricing models.

## Prerequisites
Before running the project, ensure you have the following installed on your machine:
- **Java JDK 8+**
- **Maven**
- **MySQL**
- **Git**

## Steps to Run the Project

### 1. Clone the repository
First, clone the repository to your local machine:

```bash
git clone https://github.com/de3panshu/DairyDriveAPI.git
cd DairyDriveAPI
```

### 2. Set up environment variables
Make sure to set the following environment variables before running the project:

- `DB_URL`: The URL for the database connection.
- `DB_UNAME`: Database username.
- `DB_PASS`: Database password.
- `APP_EMAIL_UNAME`: Email address used by the application to send notifications.
- `APP_EMAIL_PASS`: Password for the email account.

Example:

```bash
export DB_URL=jdbc:mysql://localhost:3306/dairy_drive
export DB_UNAME=root
export DB_PASS=your_password
export APP_EMAIL_UNAME=your_email@example.com
export APP_EMAIL_PASS=your_email_password
```

### 3. Install dependencies
Make sure you have Maven installed, then run the following command to install project dependencies:

``` bash
mvn clean install
```

### 4. Run the project
Once dependencies are installed and environment variables are set, you can start the project using:

``` bash
mvn spring-boot:run
```

### 5. Access the API
After running the project, the API will be accessible at:

```bash
http://localhost:8080
```
### 6. Database Configuration
The application connects to a MySQL database. Make sure you have a database running and configured with the correct URL, username, and password.

### 7. Email Configuration
To enable email functionality, ensure you have a valid email address and password configured in the environment variables for sending order notifications.

## API Endpoints

The API endpoints will be documented soon. **Stay tuned for updates!** 🚧



