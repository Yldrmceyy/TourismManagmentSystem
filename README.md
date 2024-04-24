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

__*DAO (Data Access Object) package:*__  <br> This package handles database operations. It contains code that directly interacts with the database and abstracts data access. This way, database operations are isolated from other layers, making changes easier.

__*Business package:*__  <br>    This is the layer where business logic operations are performed. Data retrieved from the database is processed, business rules are applied, and results are generated. For example, operations like customer reservations, tour planning, etc., are carried out in this layer.

__*Entity package:*__ <br>    It contains data models that map to database tables. These models are used to represent data in the database. For instance, classes representing a customer or a tour reservation can be found in this package.

__*Core package:*__   <br>   This package includes general utility functions and code intended for general use across different layers of the application.

__*View package:*__    <br> This package includes code related to the user interface (UI). It provides data presentation to the user and manages user interaction with the application. For example, screens where users can view tour reservations or make new tour reservations can be found in this package.






# Features

## Hotel Management
- Create, view, edit, and delete hotel entries.
- Manage hotel information including updates and deletions.
 
## Room Management
- Add, view, edit, and delete room entries within hotels.
- Control room details such as availability, pricing, and features.

## Reservation Management
- Make, view, update, and cancel reservations seamlessly.
- Efficiently manage reservation information and status updates.

## User Management
- Add, view, update, and delete user profiles.
- Assign roles such as "admin" and "employee" to users for access control and privileges.



## Example Use Cases
### Adding a New Hotel:
**1.** Navigate to the main screen and click on the "Add Hotel" button.<br>
**2.** Fill in the required details for the new hotel.<br>
**3.** Save the information, and the new hotel will be added to the system.<br>
**4.** Visit the "Hotel List" tab to view the newly added hotels.<br>

### Adding a New Room: 
**1.** Access the "Add Room" tab within the application.<br>
**2.** Select the desired hotel to add the room to.<br>
**3.** Enter the relevant details for the new room, such as room type, availability, and pricing.<br>
**4.** Save the changes, and the new room will be added to the selected hotel.<br>


### Making a Reservation: 
**1.** Go to the "Make Reservation" tab in the application.<br>
**2.** Choose the hotel where you want to make the reservation.<br>
**3.** Select the desired room type and specify the reservation details such as check-in and check-out dates.<br>
**4.** Confirm the reservation, and it will be successfully made in the system.<br>
<br>
### User Creation: 
**1.** Access the "Users" tab within the application.<br>
**2.** Click on the option to create a new user profile.<br>
**3.** Enter the user's details and assign an appropriate role, either "admin" or "employee".<br>
**4.** Save the changes, and the new user will be added to the system with the assigned role.<br>
<br>

### Reservation Update/Delete: 
**1.** Navigate to the "Reservations" tab to view existing reservations.<br>
**2.** Select the reservation you want to update or delete.<br>
**3.** Make the necessary modifications to the reservation details or choose to delete it entirely.<br>
**4.** Save the changes, and the reservation will be updated or removed from the system.<br> 








## Project Promotional Video
[https://www.youtube.com/watch?v=MCWyehN0A_E](https://youtu.be/EmSsMbuue9A)



## Requirements
- Java JDK 19
- PostgreSQL database
