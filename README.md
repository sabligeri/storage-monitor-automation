# 🧪 Storage Monitor Automation

Automated end-to-end test suite for the [Storage Monitor App](https://github.com/sabligeri/storage-monitor-app), built with Selenium, JUnit 5, and Maven. This project validates core functionalities such as user authentication, navigation, storage management, product creation, and production workflows.

---

## 🚀 Features

- 🔐 **Authentication**: Register/Login with valid/invalid credentials
- 🧭 **Navigation**: Validate navbar visibility and link functionality
- 🏠 **Storage Module**: CRUD operations on storages and items
- 📦 **Products Module**: Product creation and listing (WIP)
- 🏭 **Production Module**: Simulate product production (WIP)
- 📄 **CSV-Driven Testing**: Flexible parameterized test input
- ✅ **Access Control**: Redirect tests for unauthorized paths
- ♻️ **Reusable Utilities**: Centralized base setup and page components

---

## 🏗 Tech Stack

| **Layer**              | **Technology**                                                                                                                                                                                                                                                                    |
| ---------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Test Runner**        | [![JUnit 5](https://img.shields.io/badge/-JUnit%205-25A162?logo=java\&logoColor=white)](https://junit.org/junit5/)                                                                                                                                                                |
| **Browser Automation** | [![Selenium](https://img.shields.io/badge/-Selenium-43B02A?logo=selenium\&logoColor=white)](https://www.selenium.dev) |
| **Build Tool**         | [![Maven](https://img.shields.io/badge/-Maven-C71A36?logo=apachemaven\&logoColor=white)](https://maven.apache.org)                                                                                                                                                                |
| **Data Input**         | [![OpenCSV](https://img.shields.io/badge/-OpenCSV-FF6600?logo=csv\&logoColor=white)](http://opencsv.sourceforge.net)                                                                                                                                                              |
| **Language**           | [![Java](https://img.shields.io/badge/-Java%2021-007396?logo=java\&logoColor=white)](https://www.oracle.com/java/)                                                                                                                                                                |


---

## 📁 Project Structure

```

📦storage-monitor-automation
┣ 📂src
┃ ┣ 📂test
┃ ┃ ┣ 📂java.automation.base         # Base test and POM classes
┃ ┃ ┣ 📂java.automation.page         # Page Object Model (POM) components
┃ ┃ ┣ 📂java.automation.test         # JUnit tests
┃ ┃ ┗ 📂resources                    # Test data (CSV files)
┣ 📄pom.xml                          # Maven configuration

```

---

## ⚙️ Installation

1. **Clone the main app (SUT)**  
   Follow the instructions in the [`storage-monitor-app`](https://github.com/sabligeri/storage-monitor-app) README to clone and start the app using Docker.

2. **Clone this automation repo**
   ```bash
   git clone https://github.com/sabligeri/storage-monitor-automation.git
   cd storage-monitor-automation


3. **Run tests using Maven**

   ```bash
   mvn clean test
   ```

---

## 🧪 Test Coverage

### 🔑 Authentication Tests

* ✅ Register with valid and invalid credentials
* ✅ Login with correct and incorrect data
* ✅ Assert redirect to home on success

### 🧭 Navigation

* ✅ Navbar appears post-login
* ✅ Verify navigation to `Storages`, `Products`, `Production`, and `Home`

### 🏠 Storages Module

* ✅ View user-specific storages
* ✅ Create, delete, and open storages
* ✅ Add, delete, and update items within a storage
* ✅ Create new item types

### 📦 Products Module (WIP)

* 🔄 List and create products using selected storages/items
* ❌ Validate failure on missing data

### 🏭 Production Module (WIP)

* 🔄 Simulate successful/failed production runs based on inventory

---

## 📚 CSV Data Sources

| File                      | Purpose                               |
| ------------------------- | ------------------------------------- |
| `registration.csv`        | Valid/invalid registration inputs     |
| `password-validation.csv` | Password policy edge cases            |
| `path-access.csv`         | Auth redirect logic                   |
| `create-item.csv`         | Item creation scenarios               |
| `create-product.csv`      | Product creation test data            |
| `create-storage.csv`      | Storage names for parameterized tests |


## 📄 License

This project is licensed under the MIT License.

