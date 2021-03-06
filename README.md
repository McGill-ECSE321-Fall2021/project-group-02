## Project Overview
The goal of this project is to design a multi-tier software solution to implement a library management system and develop a release pipeline to automate the software delivery process. We are developing a website that will facilitate basic library processes for both patrons and employees of the library, such as item management (returning/borrowing books, archiving books, managing inventory, etc.), managing employee schedules, and registering new library members.

This system will be implemented by a team of 6 individuals and involved the use of requirements engineering, software development and design, system validation, and automatic software delivery.

## Running the Project
The backend of this project can be run locally by cloning this GitHub repository using the URL: https://github.com/McGill-ECSE321-Fall2021/project-group-02.git. The `LibrarySystem-Backend` repository can then be run as a Spring Boot App. The application will be hosted at http://localhost:8080/ and can be tested using the Postman application. Once the backend is running, the frontend (webpage) of the project can be run with all service functionalities by navigating to the `LibrarySystem-Frontend` and running `npm run dev` in the terminal. An initial head librarian has already been created with username: robert1 and password: fabulous in order to provide access to all functionalities. Also note that when a 'validation token' is requested, the input required is a head librarian ID.

The backend of the app is deployed here: https://librarysystem-backend-7f21.herokuapp.com/.

The frontend of the app is deployed here: https://librarysystem-frontend-7f21.herokuapp.com/. However, it will not work due to npm not finding the module 'chalk' when deploying despite the fact that 'chalk' is in our node-modules folder.

## About Us
There are six team members in Group 02: 
| Name | Team Role | Major | Year |
| --- | --- | --- | --- |
| Sami Ait Ouahmane | Software Developer | Software Engineering | U2 | 
| Julie Chen | Documentation Lead | Bioengineering | U3 |
| Hyunbum (Steven) Cho | Software Developer | Software Engineering | U3 |
| Vy-Kha Huynh | Testing Lead  | Software Engineering | U2 |
| Niilo Vuokila | Software Developer | Computer Engineering | U3 |
| John Park | Software Developer | Software Engineering | U3 |

## Contributions
| Name | Team Role | Deliverable 1 | Deliverable 2 | Deliverable 3 | Deliverable 4 |
| --- | --- | --- | --- | --- | --- |
| Sami Ait Ouahmane | Project Manager, Software Developer | 25h | 22h | 30h | 15h |
| Julie Chen | Documentation Lead | 12h | 20h | 30h | 10h |
| Hyunbum Cho | Software Developer | 12h | 20h | 25h | 10h |
| Vy-Kha Huynh | Testing Lead  | 12h | 20h | 20h | 12h |
| Niilo Vuokila | Software Developer | 20h | 20h | 23h | 12h |
| John Park | Software Developer | 12h | 18h | 25h | 12h |
 
 ### Deliverable 1
 The project report for deliverable 1 can be found [here.](https://github.com/McGill-ECSE321-Fall2021/project-group-02/wiki/Project-Report---Deliverable-1)
 
 | Name | Contributions | Hours |
| --- | --- | --- |
| Sami Ait Ouahmane | Requirements, Spring and Heroku Setup, Wiki, Domain model, and detailed the return items use case. | 25h |
| Julie Chen | Requirements, wiki and project report updates, success spectrum, and detailed use case for the borrow items use case. GitHub issues assignment and management. | 12h |
| Hyunbum Cho | Domain model, deatiled use case, issues for use cases | 12h |
| Vy-Kha Huynh | Set up PersistenceLayerTesting, Domain model, and detailed use cases  | 12h |
| Niilo Vuokila | Create Diagrams for models, Write JPA annotations and detailed use cases | 20h |
| John Park | Set up PersistenceLayerTesting, Domain model, and detailed use cases | 12h |

 ### Deliverable 2
 The project report for deliverable 2 can be found [here.](https://github.com/McGill-ECSE321-Fall2021/project-group-02/wiki/Project-Report-Deliverable-2)
 
 | Name | Contributions | Hours |
| --- | --- | --- |
| Sami Ait Ouahmane | Implementation of business method "borrowItem" and other service methods, exposed them to RestAPI, wrote tests for them, and updated the Software Quality Assurance Plan and Report | 22h |
| Julie Chen | Implementation of business method "returnItem" and other related service methods ("setDamagedItem", "discardItem"), exposed them to RestAPI, wrote unit tests for them, and outlined the test coverage in the Software Quality Assurance Plan and Report. Created and updated the project report for deliverable 2 and maintained other wiki-related tasks for deliverable 2. GitHub issues assignment and management. | 20h |
| Hyunbum Cho | Implementation of busisness method for creating online account, unit tests and RestAPI. | 20h |
| Vy-Kha Huynh | Implementation of business method "createLibrarian", "createHeadLibrarian", "deleteLibrarian" and other service methods ("getAllLibrarians", "getLibrariansByFirstAndLastName", "getLibrarianByFirstName" , "getLibrarianByLastName", "getLibrarianByID"), expoed them to RestAPI, wrote unit tests for them, and updated Software Quality Assurance Plan and Report | 20h |
| Niilo Vuokila | Implemented controller methods and unit tests for "view library contents", Fixed deliverable 1 database (JPA) and persistence layer unit test issues. Integration tests for "discard item" and "return items" use cases. | 20h |
| John Park | Implementation of business method "archiveItem" and other service methods, exposed them to RestAPI, wrote unit tests for them, and updated the Software Quality Assurance Plan and Report | 18h |

### Deliverable 3
The project report for deliverable 3 can be found [here.](https://github.com/McGill-ECSE321-Fall2021/project-group-02/wiki/Project-Report-Deliverable-3)
| Name | Contributions | Hours |
| --- | --- | --- |
| Sami Ait Ouahmane | Creation of the UI for the home page and the login/signup page. Implementation of the corresponding javascript pages and service methods to log in and sign out. Calling of the borrowItem method for the 'Manage Items' page. Creation of the architecture model(s) | 30h |
| Julie Chen | Creation of the UI 'Manage Items' page and implementation of the corresponding JavaScript for the returnItem, archiveItem, damageItem, createItem, and deleteItem service methods, as well as the creation of additional service and controller methods required for functionality. Management of deliverable 3 documentation (project report and architecture model documentation). Creation of the presentation slides. | 30h |
| Hyunbum Cho | Creation of UI for user profile page and borrowed items page and related backend controller methods that were missing. | 25h |
| Vy-Kha Huynh | Creation of the ManageLibrarianPage UI, implementation and connection between backend and frontend, all service method related to firing and hiring librarians, some service emthod to get info about the user such as id and type | 20h |
| Niilo Vuokila | Creation of UI for 'View Library Contents', implementation of corresponding JavaScript. Modification of DTOs, service, and controller methods in backend. | 23h |
| John Park | Creation of UI for 'Manage Library Schedule', implementation of corresponding JavaScript. Modification of DTOs, service, and controller methods in backend. | 25h |

### Deliverable 4
The project report for deliverable 4 can be found [here.](https://github.com/McGill-ECSE321-Fall2021/project-group-02/wiki/Project-Report-Deliverable-4)
| Name | Contributions | Hours |
| --- | --- | --- |
| Sami Ait Ouahmane | Implementation of the login, sign up (existing/nonexisting), and sign out backend services. Creation of the introduction page. | 15h |
| Julie Chen | Creation of the view library contents page for the Android application. Management of deliverable 4 documentation (project report and user documentation) | 10h |
| Hyunbum Cho | Creation of the user profile page for the android application. | 10h |
| Vy-Kha Huynh | Creation of the view borrowed items page | 12h |
| Niilo Vuokila | Creation of the home page and intro page activity and template files. Added template and activity files for each required page and configured the branch. Helped other team members as needed after finishing Home Page and template files. | 12h |
| John Park | Drafted a first layout for the view borrowed items page and view library contents page. Implemented some of the methods needed for view library contents page. | 12h |
