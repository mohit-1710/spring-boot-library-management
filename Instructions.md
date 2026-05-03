# Instructions

## Overview

In this project, learners will develop a Spring Boot application featuring two entities of their choice (e.g., Books and Authors, Students and Courses) and implement key CRUD operations—Create, Read, and Update. The project will involve defining entity classes with appropriate relationships, setting up repository layers with custom queries, implementing business logic within service layers, and creating controller methods to handle user requests. Additionally, learners will design JSP pages to interact with users and bind data to the views, ensuring the application is user-friendly and visually appealing. Testing the application is also a critical part of this project, as learners will write unit tests for the repository and service layers to ensure the robustness of the application. 

**Grading Overview**

This project will be graded based on the following key criteria, which reflect the essential aspects of a well-structured, functional, and well-documented Spring Boot application. Learners should use these criteria as a guide to ensure their project meets all expectations:

* **Correct Implementation of Entities and Relationships (10%)**

The entities chosen should be well-defined with appropriate attributes and relationships using JPA annotations. The design should reflect a clear understanding of entity relationships (e.g., One-to-Many, Many-to-One). Ensure that the entities and their relationships are accurately mapped to the database.

* **Functionality of CRUD Operations (30%)**

Each of the required CRUD operations—Create, Read, and Update—must be correctly implemented. The application should allow users to add new records, display a list of records, and update existing records. The custom query method to perform an inner join between the entities should be implemented and correctly integrated into the application.

* **Integration and Use of Spring Boot Components (30%)**

The project must demonstrate proper use of Spring Boot’s components, including the repository, service, and controller layers. The code should be well-organized, with clear separation of concerns between layers. The controller methods should effectively manage HTTP requests and responses, integrating the service layer and binding data to the views.

* **User Interface (10%)**

The JSP pages should be user-friendly and visually appealing, with appropriate CSS styling. Forms should be intuitive, and the data displayed should be clear and well-organized. The overall user experience will be evaluated based on how easy it is to navigate and use the application.

* **Testing and Validation (10%)**

The application must include unit tests for the repository and service layers, ensuring the correctness of the business logic and data operations. Proper exception handling should be implemented, particularly for data integrity violations during the Create and Update operations. The completeness and accuracy of testing will be assessed.

* **Documentation and Presentation (10%)**

The PDF document accompanying the project should clearly explain the approach taken, including entity relationship design, implementation details, and any challenges faced. Screenshots should be included to demonstrate the application's functionality. The documentation should be clear, concise, and well-organized, providing a comprehensive overview of the project.

