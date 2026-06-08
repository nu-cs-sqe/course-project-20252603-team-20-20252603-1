# Methods to support Checks

## Board

### Additions

#### `private Board(Piece[][] snapshot)` — private copy constructor
Constructs a board from an existing snapshot. Used by `moveLeavesPlayerInCheck` to simulate a move without mutating the live board.

#### `private List<Position> getGeometricMoves(Position position)`
Returns candidate moves using only board geometric and piece movement rules. I.e. does not apply check filtering. 

#### `private Position locateKing(Color color)`
Locates and returns the position of color's king.

#### `private boolean isPositionAttacked(Position position)`
Returns true if any opponent piece can geometrically reach `position`, using `getGeometricMoves` to determine reachability. The opposing color is inferred from the piece at `position`.
Throws `IllegalArgumentException` if no piece is at `position`.

#### `private boolean moveLeavesPlayerInCheck(Position from, Position to, Color color)`
Creates a copy of the current board via the copy constructor, applies the move directly on the copy, then calls `copy.isInCheck(color)`.

#### `private List<Position> filterMovesByCheckRule(List<Position> candidates, Position from, Color color)`
Uses `moveLeavesPlayerInCheck` to filter candidates, removing any move that would leave `color`'s king in check.

#### `public boolean isInCheck(Color player)`
Uses `locateKing` and `isPositionAttacked` to return whether the player is in check.

### Changes

#### `getValidMoves()`
Now delegates to `getGeometricMoves` for raw move generation, then applies `filterMovesByCheckRule` before returning. Existing callers get check-legal moves transparently.

## Game

### Additions

#### `public boolean playerInCheck(Color player)`
Returns true if player is in check.

### Changes

#### `executeMove()`
After moving the piece, calls `board.isInCheck()` and stores in `this.playerInCheck`.
