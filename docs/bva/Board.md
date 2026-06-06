# BVA: Board

### Method under test: `isEmpty(Position pos)`

| ID  | State of the System                     | Expected output | Implemented?       |
| --- | --------------------------------------- | --------------- | ------------------ |
| TC1 | new `Board()` created, Position `(1,1)` | `true`          | :white_check_mark: |
| TC2 | new `Board()` created, Position `(2,1)` | `true`          | :white_check_mark: |
| TC3 | new `Board()` created, Position `(7,1)` | `true`          | :white_check_mark: |
| TC4 | new `Board()` created, Position `(8,1)` | `true`          | :white_check_mark: |
| TC5 | new `Board()` created, Position `(1,2)` | `true`          | :white_check_mark: |
| TC6 | new `Board()` created, Position `(1,7)` | `true`          | :white_check_mark: |
| TC7 | new `Board()` created, Position `(1,8)` | `true`          | :white_check_mark: |
| TC8 | Before first move, Position `(1,1)`     | `false`         | :white_check_mark: |

The rest of the `false` cases are covered below with getPieceAt.

### Method under test: `getPieceAt(Position pos)`

| ID   | State of the System                 | Expected output                                                                                   | Implemented?       |
| ---- | ----------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------ |
| TC9  | Before first move, Position `(1,1)` | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC10 | Before first move, Position `(1,8)` | `Piece(ROOK, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC11 | Before first move, Position `(8,1)` | `Piece(ROOK, BLACK)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC12 | Before first move, Position `(1,2)` | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC13 | Before first move, Position `(1,7)` | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC14 | Before first move, Position `(2,1)` | `Piece(PAWN, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC15 | Before first move, Position `(7,1)` | `Piece(PAWN, BLACK)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC16 | Before first move, Position `(1,3)` | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false` at position                                        | :white_check_mark: |
| TC17 | Before first move, Position `(1,4)` | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false` at position                                         | :white_check_mark: |
| TC18 | Before first move, Position `(1,5)` | `Piece(KING, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC19 | new `Board()`, Position `(1,1)`     | `NoSuchElementException("Cannot get piece at empty position")`, `isEmpty()` is `true` at position | :white_check_mark: |

### Method under test: `initializeBoard()`

**TC20** — Parameterized over piece positions after `initializeBoard()`:

| Position (row, col) | Expected result                                |
| ------------------- | ---------------------------------------------- |
| `(1,1)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false`   |
| `(1,2)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` |
| `(1,3)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false` |
| `(1,4)`             | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false`  |
| `(1,5)`             | `Piece(KING, WHITE)`, `isEmpty()` is `false`   |
| `(1,6)`             | `Piece(BISHOP, WHITE)`, `isEmpty()` is `false` |
| `(1,7)`             | `Piece(KNIGHT, WHITE)`, `isEmpty()` is `false` |
| `(1,8)`             | `Piece(ROOK, WHITE)`, `isEmpty()` is `false`   |
| `(2,1)`–`(2,8)`     | `Piece(PAWN, WHITE)`, `isEmpty()` is `false`   |
| `(7,1)`–`(7,8)`     | `Piece(PAWN, BLACK)`, `isEmpty()` is `false`   |
| `(8,1)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`   |
| `(8,2)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false` |
| `(8,3)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false` |
| `(8,4)`             | `Piece(QUEEN, BLACK)`, `isEmpty()` is `false`  |
| `(8,5)`             | `Piece(KING, BLACK)`, `isEmpty()` is `false`   |
| `(8,6)`             | `Piece(BISHOP, BLACK)`, `isEmpty()` is `false` |
| `(8,7)`             | `Piece(KNIGHT, BLACK)`, `isEmpty()` is `false` |
| `(8,8)`             | `Piece(ROOK, BLACK)`, `isEmpty()` is `false`   |

| ID   | State of the System                                                    | Expected output             | Implemented?       |
| ---- | ---------------------------------------------------------------------- | --------------------------- | ------------------ |
| TC20 | after `initializeBoard()`, All piece positions (parameterized)         | See table above             | :white_check_mark: |
| TC21 | after `initializeBoard()`, Position `(3,1)` (first empty row, min col) | `isEmpty()` is `true`       | :white_check_mark: |
| TC22 | after `initializeBoard()`, Position `(3,8)` (first empty row, max col) | `isEmpty()` is `true`       | :white_check_mark: |
| TC23 | after `initializeBoard()`, Position `(6,1)` (last empty row, min col)  | `isEmpty()` is `true`       | :white_check_mark: |
| TC24 | after `initializeBoard()`, Position `(6,8)` (last empty row, max col)  | `isEmpty()` is `true`       | :white_check_mark: |
| TC25 | after `initializeBoard()`, Position `(4,4)` (interior empty square)    | `isEmpty()` is `true`       | :white_check_mark: |
| TC26 | after initializeBoard(), position (1,4)                                | piece is instance of Queen  | :x:                |
| TC27 | after initializeBoard(), position (1,2)                                | piece is instance of Knight | :white_check_mark: |
| TC28 | after initializeBoard(), position (2,1)                                | piece is instance of Pawn   | :white_check_mark: |





### Method under test: `getValidMoves(Position pos)`

| ID   | State of the System                                                                      | Expected output                                                       | Implemented? |
| ---- | ---------------------------------------------------------------------------------------- | --------------------------------------------------------------------- | :----------- |
| TC29 | new `Board()`, `pos=(4,4)` (empty square)                                                | throws `IllegalArgumentException`                                     | :x:          |
| TC30 | initialized board, `pos=(2,1)` WHITE pawn (unmoved), `(3,1)` and `(4,1)` empty           | returns `[(3,1),(4,1)]`                                               | :x:          |
| TC31 | initialized board, `pos=(1,2)` WHITE knight                                              | returns `[(3,3),(3,1)]` — candidate `(2,4)` filtered (own pawn there) | :x:          |
| TC32 | initialized board, `pos=(1,7)` WHITE knight                                              | returns `[(3,6),(3,8)]` — candidate `(2,5)` filtered (own pawn there) | :x:          |
| TC33 | initialized board, `pos=(2,8)` WHITE pawn (unmoved), `(3,8)` and `(4,8)` empty           | returns `[(3,8),(4,8)]`                                               | :x:          |
| TC34 | initialized board, WHITE pawn moved from `(2,1)` to `(3,1)` (hasMoved=true), `pos=(3,1)` | returns `[(4,1)]` — one-step only after first move                    | :x:          |

### Method under test: `movePiece(Position from, Position to)`

Validates source and destination, physically moves the piece, and calls `piece.markMoved()`. All validation throws `IllegalArgumentException`.

| ID   | State of the System                                                         | Expected output                                                                                                                 | Implemented? |
| ---- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- | :----------- |
| TC35 | new `Board()`, `from=(2,1)` (empty)                                         | throws `IllegalArgumentException`                                                                                               | :x:          |
| TC36 | initialized board, `from=(2,1)` WHITE pawn, `to=(5,1)` (not in valid moves) | throws `IllegalArgumentException`                                                                                               | :x:          |
| TC37 | initialized board, `from=(2,1)` WHITE pawn, `to=(3,1)` (one step)           | `getPieceAt((3,1))` = WHITE PAWN; piece at `(3,1)` is instance of `Pawn`; `isEmpty((2,1))` = `true`; `pawn.hasMoved()` = `true` | :x:          |
| TC38 | initialized board, `from=(2,1)` WHITE pawn, `to=(4,1)` (two steps)          | `getPieceAt((4,1))` = WHITE PAWN; piece at `(4,1)` is instance of `Pawn`; `isEmpty((2,1))` = `true`; `pawn.hasMoved()` = `true` | :x:          |
| TC39 | initialized board, `from=(1,2)` WHITE knight, `to=(3,3)`                    | `getPieceAt((3,3))` = WHITE KNIGHT; piece at `(3,3)` is instance of `Knight`; `isEmpty((1,2))` = `true`                         | :x:          |
