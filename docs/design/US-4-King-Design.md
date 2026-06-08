# King Move Design (US-4)

### Changes

#### Board
- Add `getValidMoves(Position position): List<Position>`
    - Calls `getCandidateMoves` on piece at position
    - Removes squares occupied by a same-color piece
    - Calls `piece.markMoved()` after placement
- Add `createPiece(PieceType type, Color color): Piece` - private factory for `initializeBoard()`; must produce `King` when type is KING

### Additions

#### King extends Piece

Fields:
- `hasMoved: boolean` (private) - initially false

Methods:
- `King(Color color)` - calls `super(PieceType.KING, color)`
- `getCandidateMoves(Position position): List<Position>` - Returns all adjacent positions
- `markMoved(): void` - overrides `Piece.markMoved()`, sets `hasMoved` to true
- `hasMoved(): boolean` - returns `hasMoved`