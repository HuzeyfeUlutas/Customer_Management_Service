# Customer Management Service

This project is a Spring Boot application designed to manage customer information. It provides an API for performing various CRUD (Create, Read, Update, Delete) operations on customer data.

## Features

- Manage customer information including addition, update, deletion, and retrieval.
- JSON format for data exchange.
- PostgreSQL database integration.

## Requirements

- Java 17 or higher
- Maven
- Docker (for containerization)
- Docker Compose (for managing multi-container applications)
- PostgreSQL (for the database)

## Setup and Getting Started

### Using Docker Compose

To run the project with Docker Compose, follow these steps:

1. **Clone the Repository**

   ```bash
   git clone https://github.com/HuzeyfeUlutas/Customer_Management_Service.git
   cd Customer_Management_Service
2. **Build and Start the Containers
   docker-compose up --build
   This command builds the Docker images (if needed) and starts the PostgreSQL and Spring Boot application containers.

3. Access the Application
   Once the containers are running, you can access the application at 'http://localhost:8080/swagger-ui/index.html'

