# BVA: Board

### Method under test: `isEmpty(Position pos)`

| ID   | State of the System                     | Expected output | Implemented?       |
|------|-----------------------------------------|-----------------|--------------------|
| TC1  | new `Board()` created, Position `(1,1)` | `true`          | :white_check_mark: |
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
| TC17 | Before first move, Position `(1,4)` | `Piece(QUEEN, WHITE)`, `isEmpty()` is `false` at position                                         | :white_check_mark: |
| TC18 | Before first move, Position `(1,5)` | `Piece(KING, WHITE)`, `isEmpty()` is `false` at position                                          | :white_check_mark: |
| TC19 | new `Board()`, Position `(1,1)`     | `NoSuchElementException("Cannot get piece at empty position")`, `isEmpty()` is `true` at position | :white_check_mark: |

### Method under test: `initializeBoard()`

**TC20** — Parameterized over piece positions after `initializeBoard()`:

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

| ID   | State of the System                                                    | Expected output             | Implemented?       |
|------|------------------------------------------------------------------------|-----------------------------|--------------------|
| TC20 | after `initializeBoard()`, All piece positions (parameterized)         | See table above             | :white_check_mark: |
| TC21 | after `initializeBoard()`, Position `(3,1)` (first empty row, min col) | `isEmpty()` is `true`       | :white_check_mark: |
| TC22 | after `initializeBoard()`, Position `(3,8)` (first empty row, max col) | `isEmpty()` is `true`       | :white_check_mark: |
| TC23 | after `initializeBoard()`, Position `(6,1)` (last empty row, min col)  | `isEmpty()` is `true`       | :white_check_mark: |
| TC24 | after `initializeBoard()`, Position `(6,8)` (last empty row, max col)  | `isEmpty()` is `true`       | :white_check_mark: |
| TC25 | after `initializeBoard()`, Position `(4,4)` (interior empty square)    | `isEmpty()` is `true`       | :white_check_mark: |
| TC26 | after initializeBoard(), position (1,4)                                | piece is instance of Queen  | :x:                |
| TC27 | after initializeBoard(), position (1,2)                                | piece is instance of Knight | :x:                |
| TC28 | after initializeBoard(), position (2,1)                                | piece is instance of Pawn   | :x:                |

### Method under test: `getValidMoves(Position source)`

**Sliding piece (ray-casting) tests** — board state built via `placePieceAt()`:

| ID   | State of the System                                                 | Expected output                                                                           | Implemented? |
|------|---------------------------------------------------------------------|-------------------------------------------------------------------------------------------|--------------|
| TC29 | Initialized board, WHITE Bishop at (1,3)                            | Returns `[]` — all four diagonal rays immediately blocked by own pawns at (2,2) and (2,4) | :x:          |
| TC30 | WHITE Rook at (4,4), all other squares empty                        | Returns 14 squares (full N/S/E/W rays)                                                    | :x:          |
| TC31 | WHITE Bishop at (4,4), WHITE Pawn at (6,6), all other squares empty | NE ray contains (5,5), stops before (6,6); other 3 rays fully open                        | :x:          |
| TC32 | WHITE Bishop at (4,4), BLACK Pawn at (6,6), all other squares empty | NE ray contains (5,5) and (6,6) (capture), stops after (6,6); other 3 rays fully open     | :x:          |
| TC33 | WHITE Rook at (4,4), WHITE Pawn at (4,7), all other squares empty   | East ray: (4,5), (4,6) included; (4,7) excluded (friendly stop); other 3 rays fully open  | :x:          |
| TC34 | WHITE Rook at (4,4), BLACK Pawn at (4,7), all other squares empty   | East ray: (4,5), (4,6), (4,7) included (capture); (4,8) excluded; other 3 rays fully open | :x:          |

**Non-sliding piece (Knight) tests**:

| ID   | State of the System                            | Expected output                                              | Implemented? |
|------|------------------------------------------------|--------------------------------------------------------------|--------------|
| TC35 | WHITE Knight at (4,4), all other squares empty | Returns all 8 L-shaped destinations                          | :x:          |
| TC36 | WHITE Knight at (4,4), WHITE Pawn at (6,5)     | (6,5) excluded (friendly); all 7 other L-moves included      | :x:          |
| TC37 | WHITE Knight at (4,4), BLACK Pawn at (6,5)     | (6,5) included (enemy capture); all 7 other L-moves included | :x:          |

**Pawn tests** (WHITE unless noted):

| ID   | State of the System                                                      | Expected output                                       | Implemented? |
|------|--------------------------------------------------------------------------|-------------------------------------------------------|--------------|
| TC38 | WHITE Pawn (`hasMoved=true`) at (4,4), all other squares empty           | Returns `[(5,4)]`                                     | :x:          |
| TC39 | WHITE Pawn (`hasMoved=false`) at (3,4), all other squares empty          | Returns `[(4,4), (5,4)]`                              | :x:          |
| TC40 | WHITE Pawn (`hasMoved=true`) at (4,4), any piece at (5,4)                | Returns `[]` — forward blocked by any piece           | :x:          |
| TC41 | WHITE Pawn (`hasMoved=true`) at (4,4), BLACK piece at (5,3), (5,4) empty | Returns `[(5,4), (5,3)]` — forward + diagonal capture | :x:          |
| TC42 | WHITE Pawn (`hasMoved=true`) at (4,4), (5,3) is empty                    | Returns `[(5,4)]` — diagonal excluded when empty      | :x:          |
| TC43 | WHITE Pawn (`hasMoved=true`) at (4,4), WHITE piece at (5,3)              | Returns `[(5,4)]` — diagonal excluded for friendly    | :x:          |

### Method under test: `movePiece(Position source, Position destination)`

| ID   | State of the System                                                  | Expected output                                  | Implemented? |
|------|----------------------------------------------------------------------|--------------------------------------------------|--------------|
| TC44 | WHITE Knight at (4,4), (5,6) empty, (5,6) in valid moves             | Knight at (5,6); (4,4) is empty                  | :x:          |
| TC45 | WHITE Knight at (4,4), BLACK Pawn at (5,6), (5,6) in valid moves     | Knight at (5,6); BLACK Pawn removed; (4,4) empty | :x:          |
| TC46 | Source (4,4) is empty                                                | Throws `NoSuchElementException`                  | :x:          |
| TC47 | WHITE Knight at (4,4), WHITE Pawn at (5,6) (friendly at destination) | Throws `IllegalArgumentException`                | :x:          |
| TC48 | WHITE Pawn (`hasMoved=false`) at (2,1), moves to (3,1)               | Pawn at (3,1); `pawn.hasMoved()` returns `true`  | :x:          |




