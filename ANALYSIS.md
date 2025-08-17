# Codebase Analysis

## 1. Project Purpose

The project is a 2D multiplayer game called "Planet Gravity Game." The core mechanic involves players orbiting planets to gain momentum and then launching themselves to other planets. The goal is to collide with other players, with the slower player being eliminated. It's a "last man standing" style game played in rounds.

## 2. Architecture Overview

The application is a **monolithic desktop game** built using Java's **Swing** library for the graphical user interface. It lacks a formal architectural pattern and exhibits characteristics of tightly-coupled, non-modular code, which is common in student or hobbyist projects.

The key architectural issues are:
*   **Lack of Separation of Concerns:** There is no clear distinction between game logic, rendering (view), and input handling (control). These responsibilities are mixed within the same classes.
*   **Misuse of Object-Oriented Principles:** Core game entities like `Player` and `Planet` incorrectly inherit from Swing's `JPanel` class. This suggests a misunderstanding of inheritance; these entities should be plain Java objects (POJOs) rather than UI components.
*   **Global State Management:** The application relies on `static` variables in `Main` and `Game` to manage game state (e.g., `gameSpeed`, `rounds`), which creates tight coupling and makes the code difficult to test and maintain.

## 3. Code Structure

The project's code is flat, with all `.java` source files located in the root directory.

*   **`Main.java`**: The entry point of the application. It sets up the main `JFrame` and contains the main game loop, which cycles through the `Menu`, `Game`, and `GameOver` states.
*   **`Game.java`**: The core of the game. It manages the game state, including players, planets, rounds, and collisions. It handles game logic, rendering, and input handling.
*   **`Menu.java`**: Implements the main menu of the game.
*   **`GameOver.java`**: Implements the "Game Over" screen.
*   **`Player.java`**: Represents a player in the game. It contains the player's state (location, velocity) and handles player physics, rendering, and input.
*   **`Planet.java`**: Represents a planet in the game. It contains the planet's state (location, radius) and physics properties.
*   **`MyVector.java`**: A utility class for 2D vector mathematics.
*   **`ResourceLoader.java`**: A utility class for loading resources like images and sounds.
*   **`Convert.java`**: A utility class for handling scaling and screen resolution.
*   **`res/`**: A directory containing game assets (images and sounds).

## 4. Key Components and Interactions

The application's flow is controlled by `Main.java`, which sequentially displays `Menu`, `Game`, and `GameOver` panels.

*   **`Main` -> `Menu`**: The `Menu` panel is displayed first, allowing the user to configure game settings.
*   **`Main` -> `Game`**: Once the menu is finished, `Main` creates a `Game` instance, passing it the settings from the `Menu`. The `Game` panel is then displayed, and the game loop within `Main` calls the `move()` and `repaint()` methods of the `Game` instance.
*   **`Game`**: This class is the central hub. It contains `ArrayLists` of `Player` and `Planet` objects. Its `move()` method iterates through the players, updating their positions and checking for collisions. Its `paint()` method iterates through the planets and players, calling their respective `paint()` methods.
*   **`Player` and `Planet`**: These classes are data holders that also contain their own rendering logic (`paint()` method). `Player` also contains its own input handling logic (`keyPressed()` method) and physics calculations. This is a major design flaw.
*   **`Game` -> `GameOver`**: When the game is over (e.g., one player remains), the `Game` panel signals `Main` to finish, and `Main` then displays the `GameOver` panel.

## 5. Dependencies

The project's primary dependency is the **Java Standard Library**, specifically:
*   **`javax.swing`**: Used for the entire graphical user interface (windows, panels, etc.).
*   **`java.awt`**: Used for graphics, events, and imaging.

There are no external (third-party) dependencies like Maven or Gradle packages. The project is self-contained.
