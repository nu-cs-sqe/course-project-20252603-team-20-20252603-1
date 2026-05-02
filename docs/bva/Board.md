# BVA: Board

### Method under test: `isEmpty(Position pos)`

| ID   | State of the System                     | Expected output | Implemented?       |
|------|-----------------------------------------|-----------------|--------------------|
| TC1  | new `Board()` created, Position `(0,0)` | `true`          | :white_check_mark: |
| TC2  | new `Board()` created, Position `(1,0)` | `true`          | :white_check_mark: |
| TC3  | new `Board()` created, Position `(6,0)` | `true`          | :white_check_mark: |
| TC4  | new `Board()` created, Position `(7,0)` | `true`          | :white_check_mark: |
| TC5  | new `Board()` created, Position `(0,1)` | `true`          | :white_check_mark: |
| TC6  | new `Board()` created, Position `(0,6)` | `true`          | :white_check_mark: |
| TC7  | new `Board()` created, Position `(0,7)` | `true`          | :white_check_mark: |
| TC8  | Before first move, Position `(0,0)`     | `false`         | :white_check_mark: |

The rest of the `false` cases are covered below with getPieceAt.

### Method under test: `getPieceAt(Position pos)`

| ID   | State of the System                     | Expected output                                                                                   | Implemented?       |
|------|-----------------------------------------|---------------------------------------------------------------------------------------------------|--------------------|
| TC9  | Before first move, Position `(0,0)`     | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC10 | Before first move, Position `(0,7)`     | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC11 | Before first move, Position `(7,0)`     | `Piece(ROOK, BLACK)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC12 | Before first move, Position `(0,1)`     | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :x:                |
| TC13 | Before first move, Position `(0,6)`     | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :x:                |
| TC14 | Before first move, Position `(1,0)`     | `Piece(PAWN, WHITE)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC15 | Before first move, Position `(6,0)`     | `Piece(PAWN, BLACK)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC16 | Before first move, Position `(0,2)`     | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false` at position                                        | :x:                |
| TC17 | Before first move, Position `(0,3)`     | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false` at position                                         | :x:                |
| TC18 | Before first move, Position `(0,4)`     | `Piece(KING, WHITE)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC19 | new `Board()`, Position `(0,0)`         | `NoSuchElementException("Cannot get piece at empty position")`, `isEmpty()` is `true` at position | :white_check_mark: |

### Method under test: `initializeBoard()`

**TC26** — Parameterized over piece positions after `initializeBoard()`:

| Position (row, col) | Expected result                              |
|---------------------|----------------------------------------------|
| `(0,0)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` |
| `(0,1)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false`                       |
| `(0,2)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false`                       |
| `(0,3)`             | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false`                        |
| `(0,4)`             | `Piece(KING, WHITE)`, `isEmpty()` is `false`                         |
| `(0,5)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false`                       |
| `(0,6)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false`                       |
| `(0,7)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false`                         |
| `(1,0)`–`(1,7)`     | `Piece(PAWN, WHITE)`, `isEmpty()` is `false`                         |
| `(6,0)`–`(6,7)`     | `Piece(PAWN, BLACK)`, `isEmpty()` is `false`                         |
| `(7,0)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`                         |
| `(7,1)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false`                       |
| `(7,2)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false`                       |
| `(7,3)`             | `Piece(QUEEN, BLACK)`, `isEmpty()` is `false`                        |
| `(7,4)`             | `Piece(KING, BLACK)`, `isEmpty()` is `false`                         |
| `(7,5)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false`                       |
| `(7,6)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false`                       |
| `(7,7)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`                         |

| ID   | State of the System                                                    | Expected output  | Implemented?       |
|------|------------------------------------------------------------------------|------------------|--------------------|
| TC26 | after `initializeBoard()`, All piece positions (parameterized)         | See table above | :x:                |
| TC27 | after `initializeBoard()`, Position `(2,0)` (first empty row, min col) | `isEmpty()` is `true` | :white_check_mark: |
| TC28 | after `initializeBoard()`, Position `(2,7)` (first empty row, max col) | `isEmpty()` is `true` | :white_check_mark: |
| TC29 | after `initializeBoard()`, Position `(5,0)` (last empty row, min col)  | `isEmpty()` is `true` | :white_check_mark: |
| TC30 | after `initializeBoard()`, Position `(5,7)` (last empty row, max col)  | `isEmpty()` is `true` | :white_check_mark: |
| TC31 | after `initializeBoard()`, Position `(3,3)` (interior empty square)    | `isEmpty()` is `true` | :white_check_mark: |




