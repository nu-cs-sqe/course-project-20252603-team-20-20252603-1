# BVA: Game

### Method under test: `Game()`

| ID  | State of the System      |          Expected output          |    Implemented?    |
|-----|--------------------------|-----------------------------------|--------------------|
| TC1 | new `Game(board)` called | board is non-null                 | :white_check_mark: |
| TC2 | new `Game(null)` called  | throws `IllegalArgumentException` | :white_check_mark: |


### Method under test: `startGame()`

| ID   | State of the System  |                        Expected output                      |    Implemented?    |
|------|----------------------|-------------------------------------------------------------|--------------------|
| TC3  | new `Game()` created | `getPieceAt(Position(1,1)` returns `Piece(ROOK, WHITE)`     | :white_check_mark: |
| TC4  | new `Game()` created | `getPieceAt(Position(4,4)` throws `NoSuchElementException`  | :white_check_mark: |
| TC5  | new `Game()` created | `getCurrentTurn()` returns `WHITE`                          | :white_check_mark: |

### Method under test: `getCurrentTurn()`

| ID   | State of the System      |          Expected output       |    Implemented?    |
|------|--------------------------|--------------------------------|--------------------|
| TC6  | `startGame()` called     | returns `WHITE`                | :white_check_mark: |
| TC7  | `startGame()` not called | throws `IllegalStateException` | :white_check_mark: |

### Method under test: `getPieceAt(Position pos)`

| ID    | State of the System                            |         Expected output          |    Implemented?    |
|-------|------------------------------------------------|----------------------------------|--------------------|
| TC8   | `startGame()` not called, input `Position(1,1)`| throws `IllegalStateException`   | :white_check_mark: |
| TC9   | `startGame()` called, input `Position(1,1)`    | returns `Piece(ROOK, WHITE)`     | :white_check_mark: |
| TC10  | `startGame()` called, input `Position(4,4)`    | throws  `NoSuchElementException` | :white_check_mark: |

Since BVA has been done on `getPieceAt` under the `Board` class, we are just verifying that delegating to `Board` works as expected.

TC1 can be combined with any test case.
TC3 and TC9 can be combined.
TC4 and TC10 can be combined.
TC5 and TC6 can be combined.

### Method under test: `getValidMoves(Position source)`

| ID   | State of the System                       | Expected output                               | Implemented? |
|------|-------------------------------------------|-----------------------------------------------|--------------|
| TC11 | `startGame()` not called                  | Throws `IllegalStateException`                | :x:          |
| TC12 | `startGame()` called, WHITE Rook at (1,1) | Returns `[]` — all rays blocked by own pieces | :x:          |
| TC13 | `startGame()` called, empty square (4,4)  | Throws `NoSuchElementException`               | :x:          |

### Method under test: `makeMove(Position source, Position destination)`

| ID   | State of the System                                                                     | Expected output                                                                   | Implemented? |
|------|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|--------------|
| TC14 | `startGame()` not called                                                                | Throws `IllegalStateException`                                                    | :x:          |
| TC15 | `startGame()` called, source has BLACK piece, `currentTurn` is WHITE                    | Throws `IllegalArgumentException`                                                 | :x:          |
| TC16 | `startGame()` called, WHITE Pawn moves (2,1) → (3,1)                                    | Pawn at (3,1), (2,1) empty, `getCurrentTurn()` returns `BLACK`                    | :x:          |
| TC17 | After TC16 state, BLACK Pawn moves (7,1) → (6,1)                                        | Pawn at (6,1), (7,1) empty, `getCurrentTurn()` returns `WHITE`                    | :x:          |
| TC18 | Board arranged: WHITE Knight at (4,4), BLACK Pawn at (6,5); WHITE knight moves to (6,5) | Knight at (6,5), BLACK Pawn gone, (4,4) empty, `getCurrentTurn()` returns `BLACK` | :x:          |
| TC19 | `startGame()` called, WHITE Pawn at (2,1), attempts move to (4,2)                       | Throws `IllegalArgumentException`                                                 | :x:          |