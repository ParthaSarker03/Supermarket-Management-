# SuperMarket Management System

The **SuperMarket Management System** is a desktop-based application developed to simplify supermarket operations by digitalizing inventory, employee management, and customer purchasing. The system reduces manual work, improves transaction accuracy, and provides an efficient way to manage products and sales.

## Features

### For Employees
- **Secure Login:** Employees can log in using their unique ID and password to access the purchase system.
- **Customer Purchase Management:** Add products to customer orders, calculate total bills automatically, and complete purchases efficiently.
- **Billing System:** Generate customer purchase records and maintain transaction history.

### For Administrators
- **Admin Authentication:** Secure login system for administrators to manage the entire application.
- **Product Management:** Add, update, search, and delete products while maintaining product availability and pricing.
- **Employee Management:** Register, update, and remove employee accounts with role-based access.
- **Sales Dashboard:** View total income and monitor supermarket activities through an interactive dashboard.

## Technology Stack

- **Programming Language:** Java
- **Framework:** JavaFX
- **UI Design:** FXML, CSS
- **Database:** MySQL
- **IDE:** Apache NetBeans

## Project Structure

```text
src/
└── suparmarket/
    ├── SuparMarket.java
    ├── FXMLDocument.fxml
    ├── FXMLDocumentController.java
    ├── adminDashboard.fxml
    ├── adminDashboardController.java
    ├── employeeDashboard.fxml
    ├── employeeDashboardController.java
    ├── database.java
    ├── productData.java
    ├── employeeData.java
    ├── customerData.java
    ├── getData.java
    ├── loginDesign.css
    └── dashboardDesign.css
```

## Database

Database Name:

```text
market
```

Required Tables:

```text
login
employee
product
customer
customer_receipt
```

## Database Connection

The database connection is configured in `database.java`.

```java
Connection connect = DriverManager.getConnection(
    "jdbc:mysql://localhost/market",
    "root",
    ""
);
```

## Installation and Run

1. Clone the repository.

```bash
git clone https://github.com/your-username/SuperMarket-Management-System.git
```

2. Open the project using **Apache NetBeans**.

3. Create a MySQL database named:

```text
market
```

4. Create or import the required database tables.

5. Add **MySQL Connector/J** to the project libraries.

6. Run the main file:

```text
SuparMarket.java
```

## Team

Name: Partha Sarker<br>
Email: parthasarker10701@gmail.com<br>
Name: Bappy Chandra Devnath<br>
Email: bappy.cse.20210204074@aust.edu<br>
Name: Ramisa Ali Salwa<br>
Email: ramisa.cse.20210204076@aust.edu<br>
Name: Ibteshum khaled<br>
Email: Ibteshum.cse.20210204059@aust.edu

## Conclusion

The **SuperMarket Management System** streamlines daily supermarket operations by integrating employee management, inventory control, customer purchasing, and sales monitoring into a single desktop application. It improves efficiency, minimizes manual errors, and provides a user-friendly interface for both administrators and employees. The project demonstrates practical implementation of JavaFX, MySQL, and desktop application development concepts.

---

**Made with LOVE by the Team**
