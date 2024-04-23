# Tourism Managament System
This project encompasses a Java application developed to manage tourism agency operations. The project consists of five main packages: DAO (Data Access Object), Business, Entity, Core, and View.

## About the project
The tourism agency project is a comprehensive application that offers a wide range of services, including hotel management, room reservations, customer information, and user management. This project involves adding hotels, managing rooms, making reservations, and updating customer information. Additionally, it allows the creation of users in different roles (admin and employee), enabling more effective management of business processes. As a result, the tourism agency project facilitates travel and accommodation processes, providing services in accordance with industry standards.


## Used Technologies

- __Java JDK 19__ :
  Development of the project has been carried out using the Java programming language. Java was chosen for its platform independence and powerful object-oriented programming features.

- __Swing (UI Library)__ :
 Java Swing framework has been utilized for developing the user interface (UI). Swing offers a wide range of components for Java-based desktop applications.

- __PostgreSQL__ :
 PostgreSQL has been chosen as the database management system. PostgreSQL provides a reliable, open-source, and scalable relational database management system. The project stores customer information, hotel, and reservation data in a PostgreSQL database.

- __JDBC (Java Database Connectivity)__ :
 JDBC, a standard API for accessing databases in Java applications, has been employed. Through this API, the project connects to the PostgreSQL database and performs operations.

- __IntelliJ IDEA__ :
IntelliJ IDEA has been used for the development and management of the project. IntelliJ IDEA is an integrated development environment that facilitates Java development and enhances productivity.


# Features

## Hotel Management
You can add new hotels.
View and edit existing hotels.
Update and delete hotel information.
## Room Management
You can add new rooms to a hotel.
View and edit existing rooms.
Update and delete room information.
## Reservation Management
Make reservations and view them.
Update and delete reservation information.
## User Management
Add new users and view existing ones.
Update and delete user information.
Assign "admin" and "employee" roles to users.

## Example Use Cases
Adding a New Hotel: Click the "Add Hotel" button on the main screen to add a new hotel. Visit the "Hotel List" tab to see the added hotels.

Adding a New Room: From the "Add Room" tab, you can add a new room to an existing hotel.

Making a Reservation: Choose a suitable hotel from the "Make Reservation" tab to make a reservation.

User Creation: Create a new user from the "Users" tab and assign either an "admin" or "employee" role to the user.

Reservation Update/Delete: In the "Reservations" tab, you can view, update, or delete existing reservations.

## Project Structure
The project is comprised of five main packages:

## Project File Directory
  ```sh 
TourismAgency/
|
├── business/       ---> Classes containing business logic operations.
│    └──
│ 
├── core/          ---> Fundamental helper classes and tools.
│    └── (...)
│
├── dao/            ---> Data Access Objects managing database operations.
│    └── (...)
│
├── entity/          ---> Entity classes representing database tables.
|     └── (...)
|
├── view/            ---> Classes containing User Interface (UI) components
|     └── (...)
|
├── App
|
└── README.md
   ````

## Requirements
- Java JDK 19
- PostgreSQL database
