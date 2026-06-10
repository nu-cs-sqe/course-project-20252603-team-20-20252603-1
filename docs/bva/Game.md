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
| TC18 | game started, WHITE knight at (4,4), BLACK pawn at (6,5); WHITE moves knight to (6,5) | BLACK pawn gone, knight at (6,5), `getCurrentTurn()` = BLACK | :white_check_mark: |

### Method under test: `getValidMoves(Position pos)`

`Game` adds game state and turn logic to `board.getValidMoves`.

| ID   | State of the System                                            | Expected output                   | Implemented?       |
|------|----------------------------------------------------------------|-----------------------------------|:-------------------|
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

### Method under test: `isPromotionPending()`

| ID   | State of the System                                                     | Expected output | Implemented?       |
|------|-------------------------------------------------------------------------|-----------------|:-------------------|
| TC24 | game started, no moves made                                             | `false`         | :white_check_mark: |
| TC25 | WHITE pawn at `(7,4)`, `(8,4)` empty, `executeMove((7,4),(8,4))` called | `true`          | :white_check_mark: |
| TC26 | after TC25, `executePromotion(QUEEN)` called                            | `false`         | :white_check_mark: |

### Method under test: `executePromotion(PieceType pieceType)`

| ID   | State of the System                                                             | Expected output                                                                            | Implemented?       |
|------|---------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|:-------------------|
| TC27 | no promotion pending, `executePromotion(QUEEN)` called                          | throws `IllegalStateException`                                                             | :white_check_mark: |
| TC28 | WHITE pawn moved to `(8,4)`, `promotionPending`=true, `executePromotion(QUEEN)` | `getPieceAt((8,4))` = QUEEN(WHITE); `isPromotionPending()`=false; `getCurrentTurn()`=BLACK | :white_check_mark: |
| TC29 | same setup as TC28, `executePromotion(ROOK)`                                    | `getPieceAt((8,4))` = ROOK(WHITE); `getCurrentTurn()`=BLACK                                | :white_check_mark: |
| TC30 | same setup as TC28, `executePromotion(BISHOP)`                                  | `getPieceAt((8,4))` = BISHOP(WHITE); `getCurrentTurn()`=BLACK                              | :white_check_mark: |
| TC31 | same setup as TC28, `executePromotion(KNIGHT)`                                  | `getPieceAt((8,4))` = KNIGHT(WHITE); `getCurrentTurn()`=BLACK                              | :white_check_mark: |
| TC32 | same setup as TC28, `executePromotion(PAWN)`                                    | throws `IllegalArgumentException`                                                          | :white_check_mark: |
| TC33 | same setup as TC28, `executePromotion(KING)`                                    | throws `IllegalArgumentException`                                                          | :white_check_mark: |
| TC34 | BLACK pawn moved to `(1,4)`, `promotionPending`=true, `executePromotion(QUEEN)` | `getPieceAt((1,4))` = QUEEN(BLACK); `getCurrentTurn()`=WHITE                               | :white_check_mark: |

### Method under test: `executeMove` - promotion extension

| ID   | State of the System                                                                                                          | Expected output                                                               | Implemented?       |
|------|------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|:-------------------|
| TC35 | `promotionPending`=true, `executeMove` called                                                                                | throws `IllegalStateException` - must resolve promotion first                 | :white_check_mark: |
| TC36 | WHITE pawn at `(7,4)`, `(8,4)` empty, `executeMove((7,4),(8,4))`                                                             | `isPromotionPending()`=true; `getCurrentTurn()`=WHITE (turn not yet switched) | :white_check_mark: |
| TC40 | game started, WHITE pawn at `(7,4)`, BLACK piece at `(8,5)`, `executeMove((7,4),(8,5))` (diagonal capture to promotion rank) | `isPromotionPending()`=true; `getCurrentTurn()`=WHITE                         | :x:                |

### Method under test: `executeMove` - en passant extension

| ID   | State of the System                                                                           | Expected output                                                                        | Implemented?       |
|------|-----------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|--------------------|
| TC37 | game started, WHITE Pawn at `(2,4)`, BLACK Pawn placed at `(4,5)`, `executeMove((2,4),(4,4))` | `getCurrentTurn()`=BLACK; `getValidMoves((4,5))` includes `(3,4)` (EP available)       | :white_check_mark: |
| TC38 | after TC37 (BLACK's turn), `executeMove((4,5),(3,4))` (en passant capture)                    | BLACK Pawn at `(3,4)`; `isEmpty((4,4))`=`true`; `getCurrentTurn()`=WHITE               | :x:                |
| TC39 | after TC37, BLACK makes a non-EP move instead; WHITE Pawn remains at `(4,4)`                  | `getValidMoves` for adjacent BLACK Pawn no longer includes `(3,4)` (EP target expired) | :x:                |
