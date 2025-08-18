# Testing Guide

This document provides instructions on how to run the tests for this project.

## Prerequisites

*   Java Development Kit (JDK) 8 or higher.
*   Gradle is used for building the project and running tests. The Gradle wrapper is included in the repository, so you don't need to install Gradle separately.

## How to Run Tests

1.  **Open a terminal or command prompt.**
2.  **Navigate to the root directory of the project.**
3.  **Run the following command:**

    ```bash
    ./gradlew test
    ```

    On Windows, you might need to use:

    ```bash
    gradlew.bat test
    ```

This command will compile the source code and the tests, and then run the tests. The test results will be displayed in the console. A more detailed report can be found in `build/reports/tests/test/index.html`.

## Testing Strategy

The current testing strategy is to focus on unit testing the core logic of the application. The `MyVector` class was chosen as the first class to be tested because it is a self-contained utility class with no dependencies on the GUI (Swing). This makes it easy to write simple and effective unit tests.

As the project is refactored to better separate concerns, more classes will become testable and more tests will be added.
