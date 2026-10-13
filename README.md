# PuzzleAI-Collection

[![Java](https://img.shields.io/badge/language-Java-brightgreen.svg)](https://www.oracle.com/java/)
[![Issues](https://img.shields.io/github/issues/kakurokan/PuzzleAI-Collection)](https://github.com/kakurokan/PuzzleAI-Collection/issues)
[![Stars](https://img.shields.io/github/stars/kakurokan/PuzzleAI-Collection?style=social)](https://github.com/kakurokan/PuzzleAI-Collection)
[![License](https://img.shields.io/badge/license-Unspecified-lightgrey.svg)](#license)

A small collection of puzzle solvers implemented in Java, focused on state representation and heuristic search strategies. The flagship example is a solver for the "ball sort" puzzle using a Best-First search strategy and compact state modelling.

---

## Table of Contents

- [Demo](#demo)
- [Features](#features)
- [Repository Structure](#repository-structure)
- [Requirements](#requirements)
- [Installation & Build](#installation--build)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Samples](#samples)
- [Contributing](#contributing)
- [Screenshots](#screenshots)
- [License](#license)
- [Author](#author)

## Demo

Below is a quick example of input/output (see `samples/` for full files):

Input (stdin):

```
3 3
R B E
B B E
R R E
```

Expected output (program prints the number of moves in the solution):

```
5
```

This repository includes test cases that exercise the solver against sample puzzles and expected traces.

## Features

- Simple, dependency-free Java implementation (plain `src/*.java`).
- State representation for the ball-sort puzzle (`BallSortLayout`).
- A Best-First search implementation (`BestFirst`) to find solutions.
- Unit tests demonstrating expected behaviour (JUnit 5).

## Repository Structure

- `src/` — Java source files (Main entrypoint, solver components, state models).
- `test/` — JUnit tests.
- `samples/` — sample input/output files to try the program.
- `README.md` — this file.

Key source files:
- `src/Main.java` — reads input, runs the solver, prints result.
- `src/BallSortLayout.java` — state model for the ball-sort puzzle and legal moves.
- `src/BestFirst.java` — search algorithm implementation.
- `src/Board.java`, `src/Ilayout.java` — supporting abstractions.

## Requirements

- Java 8 (1.8) or newer.
- (Optional) JUnit 5 for running the provided tests.

## Installation & Build

This project uses plain Java source files and can be compiled with the JDK toolchain directly. Below are several suggested ways to compile and package the project.

1) Quick compile (produce `.class` files in `out/`):

```bash
# from repository root
javac -d out src/*.java
```

2) Create an executable JAR (optional):

```bash
# compile
javac -d out src/*.java
# create jar
jar --create --file puzzleai-collection.jar -C out .
```

3) Using Maven or Gradle (recommended if you want dependency management / CI):

- Create a basic `pom.xml` or `build.gradle` and add JUnit as a test dependency; keep `src/` as your sources.

## Usage

Run the program reading from stdin. Example (using `out/` from the quick-compile step):

```bash
# compile first
javac -d out src/*.java
# run and provide input via heredoc
java -cp out Main <<EOF
3 3
R B E
B B E
R R E
EOF
```

Or pipe from a sample file:

```bash
cat samples/sample1.in | java -cp out Main
```

Expected behaviour: if a solution exists, the program prints a single integer — the number of moves required to reach the solved configuration. If no solution can be found, the program prints `no solution found`.

## Running Tests

The test suite uses JUnit 5. Two options to run tests:

A) Using a standalone JUnit Console Launcher JAR:

1. Download the JUnit platform console standalone JAR (example):
   - https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.4/junit-platform-console-standalone-1.9.4.jar

2. Compile sources and tests and run the console:

```bash
# compile project
javac -d out -cp junit-platform-console-standalone-1.9.4.jar src/*.java test/*.java
# run tests
java -jar junit-platform-console-standalone-1.9.4.jar --class-path out --scan-class-path
```

B) Using Maven (recommended for CI):

- Create a `pom.xml` and configure the `maven-surefire-plugin` and the JUnit Jupiter dependency, then run `mvn test`.

## Samples

The `samples/` folder contains example input (`*.in`) and expected output (`*.out`) files. Use these to validate the program manually or to create automated test fixtures.

## Contributing

Contributions are welcome. A suggested contribution workflow:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feat/your-feature`.
3. Add tests for new behaviour.
4. Run the test suite locally.
5. Open a pull request describing the change.

Guidelines:
- Prefer clear, small commits with meaningful messages.
- Include or update tests for any behaviour changes.
- Document public API or CLI changes in the README.

## Screenshots

Add screenshots to `assets/` and reference them here. Example markdown for an image (replace with actual path after adding files):

```markdown
![Solver step example](assets/screenshot-1.png)
```

Note: the repository currently does not include images; you can create an `assets/` directory and add screenshots, then update this README to show them.

## Badge & CI suggestions

- Add a GitHub Actions workflow to run `javac` and `mvn test` or the JUnit console launcher. This enables a build badge that can be embedded at the top of this README.
- Add a `LICENSE` file (MIT/Apache-2.0) to show license metadata via badges.

## License

This repository does not include an explicit license file. If you want to make the code reusable by others, consider adding a `LICENSE` (for example MIT or Apache-2.0) and update the badge above.

## Author

Maintained by kakurokan (https://github.com/kakurokan).

---

If you'd like, I can also:
- add a GitHub Actions workflow to run the tests on every push (CI),
- create an `assets/` folder and add placeholder screenshots,
- add a sample `pom.xml` for Maven build + test integration.
