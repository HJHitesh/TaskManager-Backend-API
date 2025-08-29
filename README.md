# Cloud-Native Task Manager – Spring Boot Backend

## Project Overview

The **Spring Boot backend** for the Cloud-Native Task Manager enables management of tasks through REST APIs, supporting creation, retrieval, updating, and deletion. The backend is containerized using **Docker** and deployed on **AWS EC2**.

**Key Skills Demonstrated:**

* Java 17 / Spring Boot
* REST API development
* Exception handling & validation
* Swagger API documentation
* Docker containerization
* MySQL database integration

## Project Structure

```
com/example/taskapi/
├── TaskapiApplication.java
├── config/
│   ├── CorsConfig.java
│   └── SwaggerConfig.java
├── controller/
│   └── TaskController.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Status.java
│   ├── Task.java
├── service/
│   └── TaskService.java
└── repository/
    └── TaskRepository.java
```

## Features

* Full CRUD operations on tasks via REST APIs
* Input validation and global exception handling
* Swagger UI documentation for easy API testing
* MySQL database integration
* Dockerized backend for seamless deployment

## API Endpoints

| Method | Endpoint      | Description         |
| ------ | ------------- | ------------------- |
| POST   | `/tasks`      | Create a new task   |
| GET    | `/tasks`      | Retrieve all tasks  |
| GET    | `/tasks/{id}` | Retrieve task by ID |
| PUT    | `/tasks/{id}` | Update a task by ID |
| DELETE | `/tasks/{id}` | Delete a task by ID |

## Swagger API Testing

Access the Swagger UI for interactive API testing:

```
http://<EC2-PUBLIC-IP>:8082/swagger-ui.html
```

* Test API requests directly from the browser
* Explore request and response models for all endpoints

## Running Locally

1. Clone the repository:

```bash
git clone https://github.com/HJHitesh/TaskManager-Backend.git
cd TaskManager-Backend
```

2. Configure `application.properties` with your MySQL database settings:

```
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
server.port=8082
```

3. Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

4. Access the APIs:

```
http://localhost:8082/tasks
```

## Docker Setup

1. Build the Docker image:

```bash
docker build -t taskapi-backend .
```

2. Run the container:

```bash
docker run -d -p 8082:8082 --name taskapi-backend taskapi-backend
```

3. Access the API:

```
http://localhost:8082/tasks
```

## Architecture

```
             ┌────────────────────┐
             │ Angular Frontend   │
             │   (AWS S3)         │
             └─────────┬──────────┘
                       │ HTTP/HTTPS
                       ▼
             ┌────────────────────┐
             │ EC2 Instance       │
             │ Spring Boot Docker │
             │ Backend API        │
             └─────────┬──────────┘
                       │ JDBC
                       ▼
             ┌────────────────────┐
             │ MySQL Database      │
             │ (RDS or EC2-based) │
             └────────────────────┘
```

### Flow

1. Users interact with the **Angular frontend** hosted on AWS S3.
2. Frontend sends HTTP requests to the **Spring Boot backend API** on EC2.
3. Backend performs CRUD operations on the **MySQL database**.
4. Responses are returned to the frontend for display.

## Screenshots

### Swagger UI

![Swagger UI](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/SWAGGER.png)

### CREATE Task List

![CREATE](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/CREATE.png)

### UPDATE Task List

![CREATE](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/UPDATE.png)

### EDIT Task List

![CREATE](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/EDIT.png)

### FILTER Task List

![CREATE](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/FILTER.png)

### LIST Task List

![CREATE](https://github.com/HJHitesh/TaskManager-Backend-API/blob/master/LIST.png)


