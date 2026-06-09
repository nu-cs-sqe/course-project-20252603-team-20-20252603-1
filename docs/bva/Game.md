# BVA: Game

### Method under test: `Game()`

| ID   | State of the System      | Expected output                   | Implemented?       |
| ---- | ------------------------ | --------------------------------- | ------------------ |
| TC1  | new `Game(board)` called | board is non-null                 | :white_check_mark: |
| TC2  | new `Game(null)` called  | throws `IllegalArgumentException` | :white_check_mark: |
| TC14 | new `Game()` called      | board is non-null                 | :white_check_mark: |


### Method under test: `startGame()`

| ID  | State of the System  | Expected output                                            | Implemented?       |
| --- | -------------------- | ---------------------------------------------------------- | ------------------ |
| TC3 | new `Game()` created | `getPieceAt(Position(1,1)` returns `Piece(ROOK, WHITE)`    | :white_check_mark: |
| TC4 | new `Game()` created | `getPieceAt(Position(4,4)` throws `NoSuchElementException` | :white_check_mark: |
| TC5 | new `Game()` created | `getCurrentTurn()` returns `WHITE`                         | :white_check_mark: |

### Method under test: `getCurrentTurn()`

| ID  | State of the System      | Expected output                | Implemented?       |
| --- | ------------------------ | ------------------------------ | ------------------ |
| TC6 | `startGame()` called     | returns `WHITE`                | :white_check_mark: |
| TC7 | `startGame()` not called | throws `IllegalStateException` | :white_check_mark: |

### Method under test: `getPieceAt(Position pos)`

| ID   | State of the System                             | Expected output                  | Implemented?       |
| ---- | ----------------------------------------------- | -------------------------------- | ------------------ |
| TC8  | `startGame()` not called, input `Position(1,1)` | throws `IllegalStateException`   | :white_check_mark: |
| TC9  | `startGame()` called, input `Position(1,1)`     | returns `Piece(ROOK, WHITE)`     | :white_check_mark: |
| TC10 | `startGame()` called, input `Position(4,4)`     | throws  `NoSuchElementException` | :white_check_mark: |

Since BVA has been done on `getPieceAt` under the `Board` class, we are just verifying that delegating to `Board` works as expected.

TC1 can be combined with any test case.
TC3 and TC9 can be combined.
TC4 and TC10 can be combined.
TC5 and TC6 can be combined.

### Method under test: `executeMove(Position from, Position to)`

`Game` adds turn logic on top of `board.movePiece`.

| ID   | State of the System                                                                   | Expected output                                              | Implemented?       |
| ---- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------ | :----------------- |
| TC14 | `startGame()` not called                                                              | throws `IllegalStateException`                               | :white_check_mark: |
| TC15 | game started, WHITE's turn, `from=(7,1)` (BLACK pawn)                                 | throws `IllegalArgumentException`                            | :white_check_mark: |
| TC16 | game started, WHITE's turn, `from=(2,1)` WHITE pawn, `to=(3,1)`                       | `getCurrentTurn()` = BLACK                                   | :white_check_mark: |
| TC17 | game started, after WHITE's move, BLACK's turn, `from=(7,1)` BLACK pawn, `to=(6,1)`   | `getCurrentTurn()` = WHITE                                   | :white_check_mark: |
| TC18 | game started, WHITE knight at (4,4), BLACK pawn at (6,5); WHITE moves knight to (6,5) | BLACK pawn gone, knight at (6,5), `getCurrentTurn()` = BLACK | :x:                |

### Method under test: `getValidMoves(Position pos)`

`Game` adds game state and turn logic to `board.getValidMoves`.

| ID   | State of the System                                            | Expected output                   | Implemented?       |
| ---- | -------------------------------------------------------------- | --------------------------------- | :----------------- |
| TC11 | `startGame()` not called                                       | throws `IllegalStateException`    | :white_check_mark: |
| TC12 | game started, WHITE's turn, `pos=(7,1)` (BLACK pawn)           | throws `IllegalArgumentException` | :white_check_mark: |
| TC13 | game started, WHITE's turn, `pos=(2,1)` WHITE pawn, path clear | returns `[(3,1),(4,1)]`           | :white_check_mark: |

### Method under test: `getBoardSnapshot()`

`Game` adds game state logic to `board.getSnapshot()`
| ID   | State of the System      | Expected output                      | Implemented?       |
| ---- | ------------------------ | ------------------------------------ | :----------------- |
| TC15 | `startGame()` not called | throws `IllegalStateException`       | :white_check_mark: |
| TC16 | `startGame()` called     | same output as `board.getSnapshot()` | :white_check_mark: |

### Method under test: `playerInCheck(Color player)`

Input boundaries:
- `gameInProgress`: `false`, `true`
- `player`: `WHITE`, `BLACK`
- Board state: player not in check, player in check

Output boundaries: `throws IllegalStateException`, `false`, `true`

| ID   | State of the System                                                                              | Expected output                | Implemented?       |
| ---- | ------------------------------------------------------------------------------------------------ | ------------------------------ | ------------------ |
| TC19 | `startGame()` not called, `player=WHITE`                                                         | throws `IllegalStateException` | :white_check_mark: |
| TC20 | `startGame()` called, initial board, `player=WHITE`                                              | `false`                        | :white_check_mark: |
| TC21 | `startGame()` called, initial board, `player=BLACK`                                              | `false`                        | :white_check_mark: |
| TC22 | game started, WHITE king on same rank as attacking BLACK rook, no pieces between, `player=WHITE` | `true`                         | :white_check_mark: |
| TC23 | game started, BLACK king on same rank as attacking WHITE rook, no pieces between, `player=BLACK` | `true`                         | :white_check_mark: |