<h2>Module 2 Reflection</h2>

<h3>1. Code Quality Issues & Fixes</h3>
<p>
  During this exercise, I fixed several code quality issues identified by SonarCloud and Scorecard. These included unused import statements, missing test coverage in the <code>ProductServiceImpl</code> class, and the absence of proper test method naming. 
  To address them, I removed unnecessary imports, added meaningful unit tests for uncovered service and repository logic, and ensured better naming conventions for test methods to improve clarity.
</p>

<h3>2. CI/CD Workflow Evaluation</h3>
<p>
  The CI/CD implementation meets the definition of Continuous Integration and Continuous Deployment. The workflow automatically runs unit tests, performs code quality checks using Scorecard and SonarCloud, and deploys the application to Koyeb via Docker. 
  Every push to the <code>main</code> branch triggers this pipeline, ensuring that all changes are tested and deployed consistently. 
  This setup improves delivery speed and reduces the risk of introducing errors into production.
</p>

<h2>Module 3 Reflection</h2>

<h3>1. Principles Applied</h3>
<p>
  I applied multiple SOLID principles in this project:
  <ul>
    <li><strong>Single Responsibility Principle (SRP):</strong> I separated concerns between the controller, service, and repository layers. For example, <code>CarRepository</code> now only manages data access, while <code>CarServiceImpl</code> handles business logic.</li>
    <li><strong>Open/Closed Principle (OCP):</strong> The service interface <code>CarService</code> allows new implementations without modifying the existing logic. This makes it extensible for future features like filtering cars.</li>
    <li><strong>Dependency Inversion Principle (DIP):</strong> The controller and service layers depend on abstractions like <code>CarService</code> instead of concrete implementations, which improves flexibility and testing.</li>
  </ul>
</p>

<h3>2. Advantages of Applying SOLID</h3>
<p>
  Applying SOLID improved code maintainability, scalability, and readability. For instance, by enforcing DIP, I can mock <code>CarService</code> easily during unit testing without depending on the actual repository. SRP also made it easier to modify business logic in the service layer without accidentally impacting data access logic. Additionally, OCP ensures the application is future-proof as I can add new features without touching tested, existing logic.
</p>

<h3>3. Disadvantages of Not Applying SOLID</h3>
<p>
  Without SOLID, the codebase would be tightly coupled and harder to test or refactor. For example, if <code>CarController</code> directly manipulated repository logic, testing it would require access to database logic instead of mocking the service. Without SRP, making changes in one class (e.g., adding a new validation rule) might introduce bugs in unrelated functionality. It also increases the risk of merge conflicts and slows down the team’s development pace.
</p>
