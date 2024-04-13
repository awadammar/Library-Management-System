### Example Documentation:

#### Running the Application:

1. Clone the repository from GitHub:

   ```
   git clone https://github.com/awadammar/library-management-system.git
   ```

2. Navigate to the project directory:

   ```
   cd library-management-system
   ```

3. Build the application using Maven:

   ```
   mvn clean install
   ```

4. Run the application using Spring Boot:

   ```
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

#### API Documentation:

- **GET /api/books**: Retrieve a list of all books.
    - Method: GET
    - URL: `/api/books`
    - Response: List of books in JSON format.
    - Example:
      ```
      GET /api/books
      Response: [
          {
              "id": 1,
              "title": "Book 1",
              "author": "Author 1",
              ...
          },
          ...
      ]
      ```

- **GET /api/books/{id}**: Retrieve details of a specific book by ID.
    - Method: GET
    - URL: `/api/books/{id}`
    - Response: Details of the book with the specified ID in JSON format.
    - Example:
      ```
      GET /api/books/1
      Response: {
          "id": 1,
          "title": "Book 1",
          "author": "Author 1",
          ...
      }
      ```

- **POST /api/books**: Add a new book to the library.
    - Method: POST
    - URL: `/api/books`
    - Request Body: Book object in JSON format.
    - Response: Details of the added book in JSON format.
    - Example Request:
      ```
      POST /api/books
      Body: {
          "title": "New Book",
          "author": "New Author",
          ...
      }
      ```
    - Example Response:
      ```
      Response: {
          "id": 3,
          "title": "New Book",
          "author": "New Author",
          ...
      }
      ```

- **PUT /api/books/{id}**: Update an existing book's information.
    - Method: PUT
    - URL: `/api/books/{id}`
    - Request Body: Updated book object in JSON format.
    - Response: Details of the updated book in JSON format.
    - Example Request:
      ```
      PUT /api/books/3
      Body: {
          "title": "Updated Book",
          "author": "Updated Author",
          ...
      }
      ```
    - Example Response:
      ```
      Response: {
          "id": 3,
          "title": "Updated Book",
          "author": "Updated Author",
          ...
      }
      ```

- **DELETE /api/books/{id}**: Remove a book from the library.
    - Method: DELETE
    - URL: `/api/books/{id}`
    - Response: No content (HTTP status code 204).
    - Example Request:
      ```
      DELETE /api/books/3
      ```
    - Example Response:
      ```
      Response: No content
      ```

- **POST /api/borrowing-records/borrow**: Allow a patron to borrow a book.
    - Method: POST
    - URL: `/api/borrowing-records/borrow`
    - Request Parameters: `bookId` (ID of the book to borrow), `patronId` (ID of the patron borrowing the book)
    - Response: No content (HTTP status code 204).
    - Example Request:
      ```
      POST /api/borrowing-records/borrow?bookId=1&patronId=1
      ```
    - Example Response:
      ```
      Response: No content
      ```

- **POST /api/borrowing-records/return**: Record the return of a borrowed book by a patron.
    - Method: POST
    - URL: `/api/borrowing-records/return`
    - Request Parameters: `bookId` (ID of the book being returned), `patronId` (ID of the patron returning the book)
    - Response: No content (HTTP status code 204).
    - Example Request:
      ```
      POST /api/borrowing-records/return?bookId=1&patronId=1
      ```
    - Example Response:
      ```
      Response: No content
      ```

- **GET /api/patrons**: Retrieve a list of all patrons.
    - Method: GET
    - URL: `/api/patrons`
    - Response: List of patrons in JSON format.
    - Example:
      ```
      GET /api/patrons
      Response: [
          {
              "id": 1,
              "name": "Patron 1",
              ...
          },
          ...
      ]
      ```

- **GET /api/patrons/{id}**: Retrieve details of a specific patron by ID.
    - Method: GET
    - URL: `/api/patrons/{id}`
    - Response: Details of the patron with the specified ID in JSON format.
    - Example:
      ```
      GET /api/patrons/1
      Response: {
          "id": 1,
          "name": "Patron 1",
          ...
      }
      ```

- **POST /api/patrons**: Add a new patron to the system.
    - Method: POST
    - URL: `/api/patrons`
    - Request Body: Patron object in JSON format.
    - Response: Details of the added patron in JSON format.
    - Example Request:
      ```
      POST /api/patrons
      Body: {
          "name": "New Patron",
          ...
      }
      ```
    - Example Response:
      ```
      Response: {
          "id": 3,
          "name": "New Patron",
          ...
      }
      ```

- **PUT /api/patrons/{id}**: Update an existing patron's information.
    - Method: PUT
    - URL: `/api/patrons/{id}`
    - Request Body: Updated patron object in JSON format.
    - Response: Details of the updated patron in JSON format.
    - Example Request:
      ```
      PUT /api/patrons/3
      Body: {
          "name": "Updated Patron",
          ...
      }
      ```
    - Example Response:
      ```
      Response: {
          "id": 3,
          "name": "Updated Patron",
          ...
      }
      ```

- **DELETE /api/patrons/{id}**: Remove a patron from the system.
    - Method: DELETE
    - URL: `/api/patrons/{id}`
    - Response: No content (HTTP status code 204).
    - Example Request:
      ```
      DELETE /api/patrons/3
      ```
    - Example Response:
      ```
      Response: No content
      ```
#### Authentication:

- Basic Authentication:
    - Username: `admin`
    - Password: `veryStrongPassword`
