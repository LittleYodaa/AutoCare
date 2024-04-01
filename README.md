

## Contents

* [About The Project](#about-the-project)
* [Built with](#built-with)
* [Getting Started](#getting-started)
* [Exploring AutoCare](#exploring-autocare)
* [Design Patterns](#design-patterns)
* [Roadmap](#roadmap)
* [Contact](#Contact)
* [License](#license)

## About the project

The project was created for every driver to keep the track of car exploatation and services. 

The application allows you to create a database of users, cars
and their management over time.

Selected functionalities:

* sending email notification about car exploatation event,
* possibility of adding a technical inspection, services, insurance and payment rate event,
* update car mileage based on expected annual mileage,


## Built with

* Java 17
* Apache Maven
* MySQL
* Hibernate
* H2
* Lombok
* Spring Web
* Spring Data
* Swagger
* Git

## Getting Started

#### With Maven:

To get a local copy up and running follow these simple steps.

1. Clone the repository
   ```sh
   git clone https://github.com/LittleYodaa/AutoCare.git
   ```
2. Build project with maven
   ```sh
   mvn spring-boot:run
   ```

After launching the application, Swagger documentation available at the link http://localhost:8080/swagger-ui/index.html

## Exploring AutoCare

CRUD operation and available endpoints

### Car

| Method | Url            | Description                                                                                        |                                                                                                                                                                                         |
|--------|----------------|----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /cars/{id} | Get car by id.                                                                                 |                                                                                                                                                                                                                       |
| POST   | /cars      | Save new car.                                                                                  |  |
| PUT    | /cars/{id} | Changing a car with an id placed in the path variable to a car placed in the request body. |                |
| PATCH  | /cars/{id} | Update car information with id placed in variable path using information in request body.      |                                                                                                                                                                    |
| DELETE | /cars/{id} | Delete car from databases.                                     |                                                                                                                                                                                                                       |

### User

| Method | Url                                                  | Description                                                                                                              |                                                                                                                                                                                                                    |
|--------|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /users/{id}                            | Get user by id.                                |                                                                                                                                                                                                                                                  |
| GET    | /users/{id}/cars                        | Get all user cars.                                                                                            |                                                                                                            |
| POST   | /users                                 | Save new user by request body.                                                        |  |
| PUT    | /users/{id}                            | Changing user with an id placed in the path variable to user placed in the request body. |    |                                                               |
| DELETE | /users/{id}                            | Deletes lesson user with id in path variable with all assigned cars.            |                                                |                                                                                                                                                                                                                            |    


### Cost

| Method | Url            | Description                                                                                        |                                                                                                                                                                                         |
|--------|----------------|----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST    | /cost | Add new cost to car.                                                                                 |                                                                                                                                                                                                                       |
| GET   | /cost/{id}      | Get all car usage costs.                                                                                  |  |
| GET    | /cost/pdf/{id} | Get all car usage costs in pdf file. |                |

## Design Patterns


#### Builder

> Builder design pattern was used to create objects using Lombok.

## RoadMap

- [ ] Send reports with a car exploatation costs in pdf file.

## Contact

📫 patryk.kawula1@gmail.com

## License

Distributed under the Mit License. [Check here][license-url] for more information.

[license-url]: https://github.com/LittleYodaa/AutoCare/blob/master/LICENSE
