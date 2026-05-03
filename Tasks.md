Question 1  
Your task is to build a Spring Boot application to manage information for any two entities of your choice (e.g., Books and Authors, Students and Courses). You are required to implement three operations: creating, reading, and updating data for these entities.

**Operations:**

1. **Populate Database:**  
   * Once the entities are identified, create the required tables in the database using JPA and populate all the tables with sample data (10 rows in each table).  
2. **Create Operation:**  
   * Implement a form in a JSP page to add entities.  
   * Create a controller method to handle the form submission and save the new entities.  
   * Handle exceptions in case of any integrity violations.  
3. **Read Operation:**  
   * Implement a view in JSP that displays a list of all entities.  
   * Create a controller method that fetches data from the service layer and binds it to the JSP view.  
   * Implement a custom query method in the repository layer that performs an inner join between both entities and returns the result.  
4. **Update Operation:**  
   * Implement functionality to update the details of existing entities.  
   * Create a controller method to handle the update request and save the updated data to the database.

**Requirements:**

* **Entity Classes:**  
  * Define the entities, their attributes, and relationships using JPA annotations like @Entity, @Id, @OneToMany, etc., to define relationships and other constraints.  
* **Repository Layer:**  
  * Create repository interfaces for the entities, extending JpaRepository.  
  * Include custom query methods as mentioned.  
* **Service Layer:**  
  * Implement service classes to handle business logic.  
  * Ensure proper integration with the repository layer.  
* **Controller Layer:**  
  * Implement Spring MVC controllers for handling HTTP requests related to the entities.  
  * Ensure proper data binding between the model and the view using JSTL or Expression Language (EL).  
* **View Layer:**  
  * Create JSP pages to display the form for adding entities, listing entities, and updating existing entity details.  
  * Apply CSS styles to make the JSP pages visually appealing.  
* **Testing:**  
  * Write unit tests for your repository and service methods using frameworks like JUnit and Mockito.  
  * Test the application by performing create, read, and update operations via the web interface.

