# BookLibrary

A RESTful Spring Boot application that will represent a simple "Library" system with two main entities - User and Book. A user can have many books and a book can only belong to 1 user. A book can be free (allowed to be taken by other users) or taken (not allowed to be taken by other users)

REST application that provides REST API.

Run BookLibrary locally

Databse configuration

	1.Create mySQL database book_library Predefined username - root Predefined password - root
Change in properties if required

spring.datasource.url=jdbc:mysql://localhost:3306/demolibrary - database name spring.datasource.username=root - username spring.datasource.password=pass - password spring.datasource.driver-class-name=com.mysql.jdbc.Driver - driver spring.jpa.database=mysql - database type spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect - dialect

	2.git clone https://github.com/vnSasa/BookLibrary.git

	3.cd booklibrary

	4.mvn spring-boot:run

	5.Access CustomerService using next REST requests:

GET: localhost:9191/api/v1/users/ - get all users
GET: localhost:9191/api/v1/users/1 - get user with id 1
GET: localhost:9191/api/v1/books/ - get all books
GET: localhost:9191/api/v1/books/1 - get book wih id 1

POST: localhost:9191/api/v1/users/ Request body: {"id":1,"name":"Dominic"} - create a user with id 1 and name Dominic
POST: localhost:9191/api/v1/books/ Request body: {"id":1,"name":"The Lord of the Rings"} - create a book with id and name The Lord of the Rings

PUT: localhost:9191/api/v1/users/ Request body: {"id":1,"name":"Kevin"} - update a user with id 1
PUT: localhost:9191/api/v1/books/ Request body: {"id":1,"name":"Jane Eyre"} - update a book with id 1

PUT: localhost:9191/api/v1/books/1/take/2 - Take a book with id 1(a book will become taken by user with id 2)
PUT: localhost:9191/api/v1/books/1/return - Return book (a book will be free for another user to take)

DELETE: localhost:9191/api/v1/users/2 - delete user with id 2
DELETE: localhost:9191/api/v1/bookss/2 - delete book with id 2
