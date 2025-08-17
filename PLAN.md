# Refactoring and Improvement Plan

This document outlines the step-by-step plan to refactor the Planet Gravity Game codebase. The primary goal is to apply clean code principles and improve the separation of concerns to make the project more modular, testable, and maintainable. Each major task is designed to be completed as an independent pull request.

---

### **Phase 1: Foundational Refactoring**

This phase focuses on fixing the most critical architectural flaws without changing the game's functionality.

*   **Task 1: Separate Game Entities from Swing Components**
    *   **Goal:** Remove the `extends JPanel` from `Player` and `Planet`. These classes should be plain Java objects (POJOs) that only hold data and game-specific logic.
    *   **Sub-task 1.1:** Modify `Player.java` to no longer extend `JPanel`.
    *   **Sub-task 1.2:** Modify `Planet.java` to no longer extend `JPanel`.
    *   **Sub-task 1.3:** Move the rendering logic from `Player.paint()` and `Planet.paint()` into the `Game.paint()` method. The `Game` panel will be responsible for drawing all entities.

*   **Task 2: Centralize Input Handling**
    *   **Goal:** Decouple input handling from the `Player` class. The `Game` class should be the single source of input listening.
    *   **Sub-task 2.1:** Remove `implements KeyListener` from `Player.java`.
    *   **Sub-task 2.2:** The `Game.java` class will remain the `KeyListener`.
    *   **Sub-task 2.3:** In `Game.keyPressed()`, determine which player the input is for and call a method on the appropriate `Player` object (e.g., `player.accelerate()`, `player.jump()`).

*   **Task 3: Create a Project Structure**
    *   **Goal:** Organize the source code into a standard Java project directory structure.
    *   **Sub-task 3.1:** Create a `src` directory for all `.java` files.
    *   **Sub-task 3.2:** Create packages within `src` to separate concerns (e.g., `com.planetgravity.game`, `com.planetgravity.ui`, `com.planetgravity.entities`, `com.planetgravity.core`).
    *   **Sub-task 3.3:** Move the classes into their respective packages and update all `import` statements.

---

### **Phase 2: Architectural Improvements**

This phase introduces better design patterns to improve the game's architecture.

*   **Task 4: Implement a Game State Machine**
    *   **Goal:** Replace the `while(true)` loop and manual panel switching in `Main.java` with a proper game state machine.
    *   **Sub-task 4.1:** Create a `GameState` abstract class or interface with `update()` and `render()` methods.
    *   **Sub-task 4.2:** Create concrete state classes: `MenuState`, `PlayingState`, `GameOverState`.
    *   **Sub-task 4.3:** Create a `GameStateManager` to handle switching between states.
    *   **Sub-task 4.4:** Refactor `Main.java` to use the `GameStateManager`.

*   **Task 5: Separate Rendering Logic**
    *   **Goal:** Create a dedicated rendering system to further separate game logic from drawing.
    *   **Sub-task 5.1:** Create a `Renderer` class.
    *   **Sub-task 5.2:** Move all drawing logic from `Game.paint()` into the `Renderer` class. The `Renderer` will take the list of game objects and draw them.

*   **Task 6: Encapsulate Game State**
    *   **Goal:** Remove `static` variables for game state and encapsulate them in a dedicated class.
    *   **Sub-task 6.1:** Create a `GameContext` or `World` class to hold the current list of players, planets, round number, etc.
    *   **Sub-task 6.2:** Refactor the code to pass this context object to the systems that need it, instead of using global static variables.

---

### **Phase 3: Code Quality and Cleanup**

This phase focuses on improving the overall code quality.

*   **Task 7: Refactor Hardcoded Values**
    *   **Goal:** Remove hardcoded values and replace them with constants or a configuration system.
    *   **Sub-task 7.1:** Move player key bindings from `Game.setPlayerKeys()` to a configuration file or a `Constants` class.
    *   **Sub-task 7.2:** Refactor the planet physics properties from the `if` statements in the `Planet` constructor into a more data-driven design.

*   **Task 8: Introduce Logging**
    *   **Goal:** Replace all `System.out.println()` calls with a proper logging framework (e.g., SLF4J with Logback).

*   **Task 9: Add Unit Tests**
    *   **Goal:** Introduce a testing framework (e.g., JUnit) and write initial tests for critical components.
    *   **Sub-task 9.1:** Write unit tests for the `MyVector` class.
    *   **Sub-task 9.2:** Write tests for the physics calculations in the `Player` class (this will be much easier after the refactoring).

---

### **Phase 4: Future Enhancements (Optional)**

*   **Task 10: Build System**
    *   **Goal:** Introduce a build system like Maven or Gradle to manage dependencies and automate the build process.
*   **Task 11: Add a proper main menu**
    *   **Goal:** Refactor the main menu to be more user friendly and visually appealing.
*   **Task 12: Add a level editor**
    *   **Goal:** Add a level editor to allow users to create and save their own levels.
