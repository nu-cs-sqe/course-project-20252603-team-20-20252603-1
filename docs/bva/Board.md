# BVA: Board

### Method under test: `isEmpty(Position pos)`

| ID   | State of the System                     | Expected output | Implemented?       |
|------|-----------------------------------------|-----------------|--------------------|
| TC1  | new `Board()` created, Position `(0,1)` | `true`          | :white_check_mark: |
| TC2  | new `Board()` created, Position `(2,1)` | `true`          | :white_check_mark: |
| TC3  | new `Board()` created, Position `(7,1)` | `true`          | :white_check_mark: |
| TC4  | new `Board()` created, Position `(8,1)` | `true`          | :white_check_mark: |
| TC5  | new `Board()` created, Position `(1,2)` | `true`          | :white_check_mark: |
| TC6  | new `Board()` created, Position `(1,7)` | `true`          | :white_check_mark: |
| TC7  | new `Board()` created, Position `(1,8)` | `true`          | :white_check_mark: |
| TC8  | Before first move, Position `(1,1)`     | `false`         | :white_check_mark: |

The rest of the `false` cases are covered below with getPieceAt.

### Method under test: `getPieceAt(Position pos)`

| ID   | State of the System                 | Expected output                                                                                   | Implemented?       |
|------|-------------------------------------|---------------------------------------------------------------------------------------------------|--------------------|
| TC9  | Before first move, Position `(1,1)` | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC10 | Before first move, Position `(1,8)` | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC11 | Before first move, Position `(8,1)` | `Piece(ROOK, BLACK)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC12 | Before first move, Position `(1,2)` | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC13 | Before first move, Position `(1,7)` | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC14 | Before first move, Position `(2,1)` | `Piece(PAWN, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC15 | Before first move, Position `(7,1)` | `Piece(PAWN, BLACK)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC16 | Before first move, Position `(1,3)` | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC17 | Before first move, Position `(1,4)` | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false` at position                                         | :x:                |
| TC18 | Before first move, Position `(1,5)` | `Piece(KING, WHITE)`, `isEmpty()` is `false` at position                                          | :x:                |
| TC19 | new `Board()`, Position `(1,1)`     | `NoSuchElementException("Cannot get piece at empty position")`, `isEmpty()` is `true` at position | :white_check_mark: |

### Method under test: `initializeBoard()`

**TC26** — Parameterized over piece positions after `initializeBoard()`:

| Position (row, col) | Expected result                              |
|---------------------|----------------------------------------------|
| `(1,1)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` |
| `(1,2)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false`                       |
| `(1,3)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false`                       |
| `(1,4)`             | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false`                        |
| `(1,5)`             | `Piece(KING, WHITE)`, `isEmpty()` is `false`                         |
| `(1,6)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false`                       |
| `(1,7)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false`                       |
| `(1,8)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false`                         |
| `(2,1)`–`(2,8)`     | `Piece(PAWN, WHITE)`, `isEmpty()` is `false`                         |
| `(7,1)`–`(7,8)`     | `Piece(PAWN, BLACK)`, `isEmpty()` is `false`                         |
| `(8,1)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`                         |
| `(8,2)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false`                       |
| `(8,3)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false`                       |
| `(8,4)`             | `Piece(QUEEN, BLACK)`, `isEmpty()` is `false`                        |
| `(8,5)`             | `Piece(KING, BLACK)`, `isEmpty()` is `false`                         |
| `(8,6)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false`                       |
| `(8,7)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false`                       |
| `(8,8)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`                         |

| ID   | State of the System                                                    | Expected output  | Implemented?       |
|------|------------------------------------------------------------------------|------------------|--------------------|
| TC26 | after `initializeBoard()`, All piece positions (parameterized)         | See table above | :x:                |
| TC27 | after `initializeBoard()`, Position `(3,1)` (first empty row, min col) | `isEmpty()` is `true` | :white_check_mark: |
| TC28 | after `initializeBoard()`, Position `(3,8)` (first empty row, max col) | `isEmpty()` is `true` | :white_check_mark: |
| TC29 | after `initializeBoard()`, Position `(6,1)` (last empty row, min col)  | `isEmpty()` is `true` | :white_check_mark: |
| TC30 | after `initializeBoard()`, Position `(6,8)` (last empty row, max col)  | `isEmpty()` is `true` | :white_check_mark: |
| TC31 | after `initializeBoard()`, Position `(4,4)` (interior empty square)    | `isEmpty()` is `true` | :white_check_mark: |




