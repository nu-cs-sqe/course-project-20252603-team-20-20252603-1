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
| TC26 | after initializeBoard(), position (1,4)                                | piece is instance of Queen  | :white_check_mark: |
| TC27 | after initializeBoard(), position (1,2)                                | piece is instance of Knight | :white_check_mark: |
| TC28 | after initializeBoard(), position (2,1)                                | piece is instance of Pawn   | :white_check_mark: |
| TC46 | after initializeBoard(), position (1,5)                                | piece is instance of King   | :white_check_mark: |

### Method under test: `getValidMoves(Position pos)`

As these are unit tests, the only new boundary with adding `King` is using the actual `King` instance of `Piece`. `getValidMoves` now also applies check filtering via `filterMovesByCheckRule`. The check-filtering behavior introduces the following boundaries:

- Pin: a move that exposes the king is filtered; a move that keeps it covered is included
- King into check: king moving to an attacked square is filtered; king moving to a safe square is included
- check resolution: a non-resolving move is filtered; a resolving move (block, capture attacker, king escape) is included
- Fully pinned: when every move of a piece exposes the king, the result is `[]`

| ID   | State of the System                                                                                   | Expected output                                                       | Implemented?       |
|------|-------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|--------------------|
| TC29 | new `Board()`, `pos=(4,4)` (empty square)                                                             | throws `IllegalArgumentException`                                     | :white_check_mark: |
| TC30 | initialized board, `pos=(2,1)` WHITE pawn (unmoved), `(3,1)` and `(4,1)` empty                        | returns `[(3,1),(4,1)]`                                               | :white_check_mark: |
| TC31 | initialized board, `pos=(1,2)` WHITE knight                                                           | returns `[(3,3),(3,1)]` — candidate `(2,4)` filtered (own pawn there) | :white_check_mark: |
| TC32 | initialized board, `pos=(1,7)` WHITE knight                                                           | returns `[(3,6),(3,8)]` — candidate `(2,5)` filtered (own pawn there) | :white_check_mark: |
| TC33 | initialized board, `pos=(2,8)` WHITE pawn (unmoved), `(3,8)` and `(4,8)` empty                        | returns `[(3,8),(4,8)]`                                               | :white_check_mark: |
| TC34 | initialized board, WHITE pawn moved from `(2,1)` to `(3,1)` (hasMoved=true), `pos=(3,1)`              | returns `[(4,1)]` — one-step only after first move                    | :white_check_mark: |
| TC40 | WHITE Rook at (4,4), all other squares empty (via `placePieceAt`)                                     | returns 14 squares (full N/S/E/W rays)                                | :white_check_mark: |
| TC41 | WHITE Bishop at (4,4), WHITE Pawn at (6,6), all other squares empty                                   | NE ray stops at (5,5); (6,6) excluded (friendly stop)                 | :white_check_mark: |
| TC42 | WHITE Bishop at (4,4), BLACK Pawn at (6,6), all other squares empty                                   | NE ray includes (5,5) and (6,6) (capture); stops after (6,6)          | :white_check_mark: |
| TC43 | WHITE Knight at (4,4), BLACK Pawn at (6,5), all other squares empty                                   | (6,5) included (capture); all 7 other L-moves included                | :white_check_mark: |
| TC44 | WHITE Pawn (hasMoved=true) at (4,4), BLACK piece at (5,4)                                             | returns `[]` — forward blocked by enemy                               | :white_check_mark: |
| TC47 | initialized board, WHITE king, `pos=(1,5)`                                                            | returns `[]` — all surrounding squares occupied by own pieces         | :white_check_mark: |
| TC48 | initialized board, BLACK king, `pos=(8,5)`                                                            | returns `[]` — all surrounding squares occupied by own pieces         | :white_check_mark: |
| TC58 | WHITE king at `(1,5)`, WHITE rook at `(1,3)`, BLACK rook at `(1,1)`, no other pieces, `pos=(1,3)`     | `(1, 2)` included, `(2,3)` excluded                                   | :white_check_mark: |
| TC59 | WHITE king at `(5,5)`, BLACK rook at `(5,1)`, no other pieces, `pos=(5,5)` (king in check)            | does not include `(5,6)`, does include `(6,5)`                        | :white_check_mark: |
| TC60 | BLACK rook at `(5,1)`, WHITE king at `(5,8)`, WHITE knight at `(3,3)`, no other pieces, `pos=(3,3)`   | includes `(5,4)`, does not include `(1,2)`                            | :white_check_mark: |
| TC61 | BLACK rook at `(6,5)`, WHITE king at `(5,5)`, no other pieces, `pos=(5,5)`                            | includes `(6,5)`                                                      | :white_check_mark: |
| TC62 | WHITE king at `(1,5)`, WHITE knight at `(3,3)`, BLACK bishop at `(5,1)`, no other pieces, `pos=(3,3)` | returns `[]`                                                          | :white_check_mark: |
| TC72 | WHITE Pawn (hasMoved=false) at `(2,1)`, BLACK piece at `(3,1)`, `(4,1)` empty                         | returns `[]` — one-square blocked prevents two-square jump            | :white_check_mark: |
| TC73 | BLACK Pawn (hasMoved=false) at `(7,1)`, WHITE piece at `(6,1)`, `(5,1)` empty                         | returns `[]` — one-square blocked prevents two-square jump            | :white_check_mark: |
| TC82 | WHITE Pawn (`hasMoved=true`) at `(4,4)`, BLACK Pawn at `(5,5)`, no EP target                          | includes `(5,5)` — regular diagonal capture                           | :white_check_mark: |
| TC83 | WHITE Pawn (`hasMoved=true`) at `(4,4)`, BLACK Pawn at `(5,3)`, no EP target                          | includes `(5,3)` — regular diagonal capture (left)                    | :white_check_mark: |
| TC84 | WHITE Pawn (`hasMoved=true`) at `(4,4)`, WHITE Pawn at `(5,5)`, no EP target                          | does not include `(5,5)` — friendly piece                             | :white_check_mark: |
| TC85 | BLACK Pawn (`hasMoved=true`) at `(5,4)`, WHITE Pawn at `(4,5)`, no EP target                          | includes `(4,5)` — BLACK diagonal capture                             | :white_check_mark: |
| TC86 | WHITE Pawn (`hasMoved=true`) at `(5,4)`, BLACK Pawn at `(5,5)`, EP target=`(6,5)`                     | includes `(6,4)` and `(6,5)` — forward + en passant                   | :white_check_mark: |
| TC87 | WHITE Pawn (`hasMoved=true`) at `(5,4)`, BLACK Pawn at `(5,3)`, EP target=`(6,3)`                     | includes `(6,4)` and `(6,3)` — forward + en passant (left)            | :white_check_mark: |
| TC88 | WHITE Pawn (`hasMoved=true`) at `(5,1)`, BLACK Pawn at `(5,2)`, EP target=`(6,2)`                     | includes `(6,1)` and `(6,2)` — col LOW edge                           | :white_check_mark: |
| TC89 | WHITE Pawn (`hasMoved=true`) at `(5,8)`, BLACK Pawn at `(5,7)`, EP target=`(6,7)`                     | includes `(6,8)` and `(6,7)` — col HIGH edge                          | :white_check_mark: |
| TC90 | BLACK Pawn (`hasMoved=true`) at `(4,4)`, WHITE Pawn at `(4,5)`, EP target=`(3,5)`                     | includes `(3,4)` and `(3,5)` — BLACK en passant                       | :white_check_mark: |
| TC91 | WHITE Pawn (`hasMoved=true`) at `(5,4)`, EP target=`(6,5)`, `(6,5)` empty                             | includes `(6,5)` — EP capture to empty square                         | :white_check_mark: |
| TC94 | WHITE Pawn (`hasMoved=true`) at `(7,4)`, BLACK piece at `(8,5)`, no EP target                         | includes `(8,5)` — diagonal capture onto promotion rank               | :x:                |
| TC95 | BLACK Pawn (`hasMoved=true`) at `(2,4)`, WHITE piece at `(1,5)`, no EP target                         | includes `(1,5)` — diagonal capture onto promotion rank               | :x:                |

### Method under test: `movePiece(Position from, Position to)`

Validates source and destination, physically moves the piece, and calls `piece.markMoved()`. All validation throws `IllegalArgumentException`.

As these are unit tests, the only new boundary with adding `King` is using the actual `King` instance of `Piece`.

| ID   | State of the System                                                         | Expected output                                                                                                                 | Implemented?       |
| ---- | --------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- | ------------------ |
| TC35 | new `Board()`, `from=(2,1)` (empty)                                         | throws `IllegalArgumentException`                                                                                               | :white_check_mark: |
| TC36 | initialized board, `from=(2,1)` WHITE pawn, `to=(5,1)` (not in valid moves) | throws `IllegalArgumentException`                                                                                               | :white_check_mark: |
| TC37 | initialized board, `from=(2,1)` WHITE pawn, `to=(3,1)` (one step)           | `getPieceAt((3,1))` = WHITE PAWN; piece at `(3,1)` is instance of `Pawn`; `isEmpty((2,1))` = `true`; `pawn.hasMoved()` = `true` | :white_check_mark: |
| TC38 | initialized board, `from=(2,1)` WHITE pawn, `to=(4,1)` (two steps)          | `getPieceAt((4,1))` = WHITE PAWN; piece at `(4,1)` is instance of `Pawn`; `isEmpty((2,1))` = `true`; `pawn.hasMoved()` = `true` | :white_check_mark: |
| TC39 | initialized board, `from=(1,2)` WHITE knight, `to=(3,3)`                    | `getPieceAt((3,3))` = WHITE KNIGHT; piece at `(3,3)` is instance of `Knight`; `isEmpty((1,2))` = `true`                         | :white_check_mark: |
| TC45 | WHITE Knight at (4,4), BLACK Pawn at (6,5); knight moves to (6,5)           | Knight at (6,5); BLACK Pawn removed; (4,4) empty                                                                                | :white_check_mark: |
| TC49 | initialized board, `(2,6)` cleared, `from=(1,5)` WHITE king, `to=(2,6)`     | `getPieceAt((2,6))` = WHITE King; instance of `King`; `isEmpty((1,5))` = `true`; `king.hasMoved()` = `true`                     | :white_check_mark: |
| TC50 | initialized board, `(7,4)` cleared, `from=(8,5)` BLACK king, `to=(7,4)`     | `getPieceAt((7,4))` = BLACK King; instance of `King`; `isEmpty((8,5))` = `true`; `king.hasMoved()` = `true`                     | :white_check_mark: |
| TC92 | WHITE Pawn at `(5,4)`, BLACK Pawn at `(5,5)`, EP target=`(6,5)`: `movePiece((5,4),(6,5))` | WHITE Pawn at `(6,5)`; `isEmpty((5,4))`=`true`; `isEmpty((5,5))`=`true` — captured pawn removed | ☐ |
| TC93 | BLACK Pawn at `(4,5)`, WHITE Pawn at `(4,4)`, EP target=`(3,4)`: `movePiece((4,5),(3,4))` | BLACK Pawn at `(3,4)`; `isEmpty((4,5))`=`true`; `isEmpty((4,4))`=`true` — captured pawn removed | ☐ |


### Method under test: `public boolean isInCheck(Color player)`

Input boundaries:
- `Color player`: WHITE, BLACK
- Board state — no attacker, attacker present
- Sliding attacker unobstructed, sliding attacker blocked by a piece
- Non-sliding attacker
- Pinned attacker: cannot legally move, but geometrically covers the king's square

Output boundary: `false`, `true`

| ID   | State of the System                                                                                         | Expected output                | Implemented?       |
|------|-------------------------------------------------------------------------------------------------------------|--------------------------------|--------------------|
| TC51 | initialized board, `isInCheck(WHITE)`                                                                       | `false`                        | :white_check_mark: |
| TC52 | initialized board, `isInCheck(BLACK)`                                                                       | `false`                        | :white_check_mark: |
| TC53 | WHITE king at `(5,8)`, BLACK rook at `(5,1)`, no other pieces                                               | `true`                         | :white_check_mark: |
| TC54 | WHITE king at `(5,5)`, BLACK rook at `(5,1)`, WHITE pawn at `(5,3)`, no other pieces                        | `false`                        | :white_check_mark: |
| TC55 | WHITE king at `(5,5)`, BLACK knight at `(3,4)`, no other pieces                                             | `true`                         | :white_check_mark: |
| TC56 | BLACK king at `(5,5)`, WHITE queen at `(5,1)`, no other pieces                                              | `true`                         | :white_check_mark: |
| TC57 | WHITE rook at `(1,1)`, BLACK rook at `(4,1)`, BLACK king at `(8,1)`, WHITE king at `(4,5)`, no other pieces | `true`                         | :white_check_mark: |
| TC74 | no WHITE king on board, `isInCheck(WHITE)` called                                                           | throws `IllegalStateException` | :white_check_mark: |

### Method under test: `promotePawn(Position position, PieceType pieceType)`

| ID   | State of the System                                            | Expected output                                                    | Implemented?       |
|------|----------------------------------------------------------------|--------------------------------------------------------------------|:-------------------|
| TC63 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), QUEEN)`             | `getPieceAt((8,4))` = QUEEN, WHITE; piece is instance of `Queen`   | :white_check_mark: |
| TC64 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), ROOK)`              | `getPieceAt((8,4))` = ROOK, WHITE; piece is instance of `Rook`     | :white_check_mark: |
| TC65 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), BISHOP)`            | `getPieceAt((8,4))` = BISHOP, WHITE; piece is instance of `Bishop` | :white_check_mark: |
| TC66 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), KNIGHT)`            | `getPieceAt((8,4))` = KNIGHT, WHITE; piece is instance of `Knight` | :white_check_mark: |
| TC67 | BLACK PAWN at `(1,4)`, `promotePawn((1,4), QUEEN)`             | `getPieceAt((1,4))` = QUEEN, BLACK; piece is instance of `Queen`   | :white_check_mark: |
| TC68 | WHITE PAWN at `(4,4)` (mid-board), `promotePawn((4,4), QUEEN)` | throws `IllegalArgumentException` - not at promotion rank          | :white_check_mark: |
| TC69 | WHITE KNIGHT at `(8,4)`, `promotePawn((8,4), QUEEN)`           | throws `IllegalArgumentException` - not a pawn                     | :white_check_mark: |
| TC70 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), PAWN)`              | throws `IllegalArgumentException` - cannot promote to PAWN         | :white_check_mark: |
| TC71 | WHITE PAWN at `(8,4)`, `promotePawn((8,4), KING)`              | throws `IllegalArgumentException` - cannot promote to KING         | :white_check_mark: |
| TC75 | empty position `(8,4)`, `promotePawn((8,4), QUEEN)`            | throws `IllegalArgumentException` - no piece at position           | :white_check_mark: |

### Method under test: `getEnPassantTarget()`

| ID   | State of the System                                                            | Expected output      | Implemented?       |
|------|--------------------------------------------------------------------------------|----------------------|--------------------|
| TC76 | new `Board()`                                                                  | `Optional.empty()`   | :white_check_mark: |
| TC77 | WHITE Pawn at `(2,4)`, `movePiece((2,4),(4,4))` (double advance)               | `Optional.of((3,4))` | :white_check_mark: |
| TC78 | BLACK Pawn at `(7,3)`, `movePiece((7,3),(5,3))` (double advance)               | `Optional.of((6,3))` | :white_check_mark: |
| TC79 | WHITE Pawn (`hasMoved=true`) at `(3,4)`, `movePiece((3,4),(4,4))` (one square) | `Optional.empty()`   | :white_check_mark: |
| TC80 | initialized board, WHITE knight `movePiece((1,2),(3,3))`                       | `Optional.empty()`   | :white_check_mark: |
| TC81 | after TC77, BLACK Pawn `movePiece((7,1),(6,1))` (next move clears target)      | `Optional.empty()`   | :white_check_mark: |
