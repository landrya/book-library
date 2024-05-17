# Book Catalog Microservice

Welcome to the Book Catalog Management Microservice. This microservice is developed in Java 21, uses Maven for dependency management, and follows the hexagonal architecture to ensure a clear separation between business logic and technical concerns.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
    - [Add a Book](#add-a-book)
    - [Delete a Book](#delete-a-book)
    - [Edit a Book](#edit-a-book)
    - [List Books](#list-books)
    - [Get Book Details](#get-book-details)
- [Architecture](#architecture)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

- Java 21
- Maven 3.x

## Installation

1. Clone the repository:

    ```bash
    git clone git@github.com:landrya/book-library.git
    cd catalog
    ```

2. Compile and install Maven dependencies:

    ```bash
    mvn clean install
    ```

3. Start the application:

    ```bash
    mvn spring-boot:run
    ```
   
You can also run the application from your IDE.

## Usage

The service exposes a RESTful API to manage the book catalog. Examples of API usage are detailed below.

## API

The API exposes the following endpoints to manage the book catalog:

### Add a Book

- **URL**: `/catalog/book`
- **Method**: `POST`
- **Description**: Add a new book to the catalog
- **Request Body**:

    ```json
    {
      "title": "Book Title",
      "isbn": "1519407386",
      "authors": ["Author"],
      "description": "Here is an example of book description",
      "image": {
        "frontUrl": "http://example.com/front.jpg",
        "backUrl": "http://example.com/back.jpg"
      },
      "tags": ["Fiction", "Science"],
      "prices": {
        "USD": "29.99"
      }
    }
    ```

- **Response**:

    ```json
    {
      "id": "1"
    }
    ```

### Delete a Book

- **URL**: `/catalog/book/{id}`
- **Method**: `DELETE`
- **Description**: Delete a book from the catalog using its ID

- **Response**: `204 No Content`

### Edit a Book

- **URL**: `/catalog/book/{id}`
- **Method**: `PUT`
- **Description**: Update the information of an existing book
- **Request Body**:

    ```json
    {
      "title": "New Title",
      "isbn": "1519407386",
      "authors": ["New Author"],
      "description": "New description",
      "image": {
        "frontUrl": "http://example.com/new_front.jpg",
        "backUrl": "http://example.com/new_back.jpg"
      },
      "tags": ["Non-fiction", "History"],
      "prices": {
        "USD": "35.99"
      }
    }
    ```

- **Response**: `204 No Content`

### List Books

- **URL**: `/catalog/book`
- **Method**: `GET`
- **Description**: List all books in the catalog

- **Response**:

    ```json
    {
      "books": [
        {
          "isbn": "1519407386",
          "title": "Book Title",
          "authors": ["Author"]
        },
        {
          "isbn": "1519407387",
          "title": "Another Title",
          "authors": ["Another Author"]
        }
      ]
    }
    ```

### Get Book Details

- **URL**: `/catalog/book/{id}`
- **Method**: `GET`
- **Description**: Get the details of a specific book using its ID

- **Response**:

    ```json
    {
      "title": "Book Title",
      "isbn": "1519407386",
      "authors": ["Author"],
      "description": "Here is an example of book description",
      "image": {
        "frontUrl": "http://example.com/front.jpg",
        "backUrl": "http://example.com/back.jpg"
      },
      "tags": ["Fiction", "Science"],
      "prices": {
        "USD": "29.99"
      }
    }
    ```

## Architecture

The microservice is structured according to the hexagonal architecture, also known as Ports and Adapters. This ensures a separation of business logic from technical concerns.

So we have 3 maven modules: 
- **Domain**: contains business entities, services, and port interfaces.
- **Application**: contains application services and use cases.
- **Infrastructure**: contains adapter implementations, REST controllers, and configurations.

There is no framework in the domain module. It will help us not to depend on technical details, and also enable us to change dependency injection framwork if we want.

## Tests

To run unit and integration tests, use the following command:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please follow the steps below to contribute:

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/amazing-feature`).
3. Commit your changes (`git commit -m 'Add some amazing feature'`).
4. Push to the branch (`git push origin feature/amazing-feature`).
5. Open a Pull Request.

---

Thank you for using the Book Catalog Management Microservice! For any questions or suggestions, feel free to open an issue.