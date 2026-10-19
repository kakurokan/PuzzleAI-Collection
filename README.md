# eight-puzzle-solver

[![Java](https://img.shields.io/badge/Java-25-brightgreen.svg)](https://openjdk.org/projects/jdk/25/)
[![Issues](https://img.shields.io/github/issues/kakurokan/eight-puzzle-solver)](https://github.com/kakurokan/eight-puzzle-solver/issues)
[![Stars](https://img.shields.io/github/stars/kakurokan/eight-puzzle-solver?style=social)](https://github.com/kakurokan/eight-puzzle-solver)

Puzzle solvers in plain Java, built around a common state interface (`Ilayout`) and two search strategies:

- **Best-First (uniform cost)** — `search.BestFirst`, expands states ordered by path cost `g`.
- **A\*** — `search.AStarSearch`, expands states ordered by `f = g + h`.

Two puzzles are implemented:

- **8-puzzle** — `puzzles.Board` (3×3 board, blank tile is `0`).
- **Ball sort** — `puzzles.BallSortLayout` (tubes of coloured balls; the goal is every tube empty or holding a single colour). This is the puzzle solved by the command-line entry point.

---

## Repository structure

```
src/
  app/Main.java               entry point: reads a ball-sort puzzle from stdin, solves it with A*
  core/Ilayout.java           state interface: children(), isGoal(), getK(), getHeuristic()
  puzzles/Board.java          8-puzzle state
  puzzles/BallSortLayout.java ball-sort state and heuristic
  search/BestFirst.java       uniform-cost search
  search/AStarSearch.java     A* search
test/
  AssertionExamplesTest.java
  test/puzzles/BoardTests.java
  test/puzzles/BallSortTests.java
samples/                      8-puzzle examples (see "Samples" below)
```

## Requirements

- **JDK 25** or newer. `Main` uses an instance-style `static void main` without `public` (JEP 512), and the code uses text blocks and pattern-matching `instanceof`.
- **JUnit 5** to run the tests.

## Build

Sources are organised in packages, so compile every file under `src/`:

```bash
javac -d out $(find src -name "*.java")
```

## Usage

`app.Main` reads a ball-sort puzzle from stdin and prints the minimum number of moves.

Input format:

- First line: `<height> <tubes>` — tube capacity and number of tubes.
- Then `height` lines, top row first, each with `tubes` space-separated cells. A cell is a colour letter, or `E` for empty.

Example:

```bash
java -cp out app.Main <<EOF
3 3
R B E
B B E
R R E
EOF
```

Output:

```
5
```

If the puzzle has no solution, the program prints `no solution found`.

## Running tests

With the [JUnit Platform Console Standalone](https://central.sonatype.com/artifact/org.junit.platform/junit-platform-console-standalone) JAR:

```bash
JUNIT=junit-platform-console-standalone-1.14.0.jar
javac -d out -cp "$JUNIT" $(find src test -name "*.java")
java -jar "$JUNIT" execute --class-path out --scan-class-path
```

Alternatively, open the project in IntelliJ IDEA and run the `test/` folder.

## Samples

The files in `samples/` are **8-puzzle** examples, not ball-sort input, so they cannot be piped into `app.Main`. Each `.in` has two lines — the initial board and the goal board, as 9 digits with `0` for the blank — and the matching `.out` shows the solution path followed by the number of moves.

```
023145678
123405678
```

To solve an 8-puzzle, call the search directly:

```java
Iterator<BestFirst.State> path =
        new BestFirst().solve(new Board("023145678"), new Board("123405678"));
```

## Author

Maintained by [kakurokan](https://github.com/kakurokan).
