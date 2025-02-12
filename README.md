# Hostel Booking System

This project is a simple Hostel Booking System implemented in Java. It allows students to book rooms in a hostel and administrators to manage room bookings.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Student View**:

  - Login and create new user accounts.
  - Check available rooms.
  - Book rooms.

- **Admin View**:
  - Login as an admin.
  - Create new rooms.
  - Delete existing rooms.
  - Delete existing bookings.
  - Check room details.
  - Check booking details.
  - Update payment status.

## Technologies Used

- Java
- MySQL
- Maven

## Project Structure

```
src/main/java/com/hostelbookingsystem/
├── dao/
│   ├── BookingDAO.java
│   ├── RoomDAO.java
│   ├── UserDAO.java
├── model/
│   ├── Booking.java
│   ├── Room.java
│   ├── User.java
├── view/
│   ├── AdminView.java
│   ├── StudentView.java
├── Main.java
pom.xml
README.md
```

## Setup and Installation

1. **Clone the repository**:
   `sh
    git clone https://github.com/yourusername/hostelbookingsystem.git
    cd hostelbookingsystem
    `

2. **Setup MySQL Database**: - Create a database named `hostel_booking_system`. - Create the necessary tables (`users`, `rooms`, `bookings`).

3. **Update Database Configuration**: - Update the database connection details in the DAO classes (`BookingDAO.java`, `RoomDAO.java`, `UserDAO.java`).

4. **Build the project using Maven**:
   `sh
    mvn clean install
    `

## Usage

1. **Run the application**:
   `sh
    mvn exec:java
    `

2. **Follow the on-screen instructions** to navigate through the Student View or Admin View.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License.
