[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23625087)
![Gradle Build](https://github.com/nu-cs-sqe/course-project-20252603-team-20-20252603-1/actions/workflows/main.yml/badge.svg)
# Chess 

## Contributors
- Annabelle Sole 
- Kavi Subramanyan
- Domingo Contreras 

## Dependencies
- JDK 11
- JUnit 5.10
- Gradle 8.10
- Spotbugs 6.5.4
- Checkstyle 9.3
- JaCoCo 0.8.x
- Pitest 1.15.0
- Cucumber 7.20.1

## Acknowledgements
1. Spotbugs build scripts
   1. https://plugins.gradle.org/plugin/com.github.spotbugs
   2. https://github.com/spotbugs/spotbugs-gradle-plugin
   3. Lab 5 `build.gradle.kts`
2. Checkstyle
   1. Lab 5 `build.gradle.kts`
   2. [XSL sheet](https://github.com/checkstyle/contribution/blob/master/xsl/checkstyle-noframes-severity-sorted.xsl)
   3. [Google config](https://github.com/checkstyle/checkstyle/blob/checkstyle-9.3/src/main/resources/google_checks.xml)
3. JaCoCo
   1. https://docs.gradle.org/current/userguide/jacoco_plugin.html
   2. Lab 5 `build.gradle.kts`
4. Pitest
   1. https://gradle-pitest-plugin.solidsoft.info/
   2. Lab 5 `build.gradle.kts`
5. GUI Starter code
   1. https://github.com/nu-cs-sqe/example-chess-startercode-with-java-swing-20252603-annabellesole2026
6. Cucumber Build
   1. Lab 6 `build.gradle.kts`

## Exceptions

### JaCoCo - Missing Coverage

- `ChessClock.java` line 27, `Game.java` line 31, `Piece.java` line 25, `Position.java` (finalize body): each class contains an empty `@Override protected final void finalize() throws Throwable {}` to suppress the JVM finalizer. These lines contain no logic and are only invoked by the garbage collector, not by test code.
- `ChessClock.java` lines 41-42 (Swing Timer lambda and `timer.start()`): the `javax.swing.Timer` fires asynchronously after a 1000 ms delay. Tests call `tick()` directly and never let the timer actually fire. Covering these lines would require real-time waits or mocking `javax.swing.Timer`, introducing non-determinism without meaningful verification.

### Pitest - Surviving Mutants

- `Board.java` `removeBlockedPawnTwoSquareMove`, `Pawn.java` line 35 (`2 * direction` to `2 / direction`): `direction` is always +1 or -1, so integer multiplication and division produce identical results. These are equivalent mutants.
- `Board.java` `movePiece` and `executeCastle` (`to.getCol() > from.getCol()` to `>=`): the king's source and destination columns are never equal in a valid castling move (5 to 3 or 7), so both operators produce the same boolean. Equivalent mutant.
- `Board.java` `getCastlingMoves` early returns (`return moves` to `Collections.emptyList()`): both return empty lists. Callers never mutate the returned collection, so the difference is unobservable. Equivalent mutant.
- `Board.java` `moveLeavesPlayerInCheck` (removed `executeCastle` call): repositioning the rook during castling simulation does not change whether the king's destination square is attacked. In any valid castling setup, no piece attacking the king's destination is blocked only by the rook's new position. Equivalent mutant.
- `King.java` lines 38-39, `Knight.java` lines 22-23 (addition to subtraction in direction arithmetic): the DIRECTIONS arrays contain symmetric pairs. Negating one direction produces the same complete set of candidate squares. Equivalent mutants.
- `Piece.java` lines 36 and 47 (`new ArrayList<>()` to `Collections.emptyList()`): base-class default implementations return empty collections. All concrete subclasses override these methods, and no caller mutates the returned empty list. Equivalent mutants.
- `ChessClock.java` lines 41-42 (see JaCoCo note above): the timer lambda and `timer.start()` survive because tests never observe the timer firing.
