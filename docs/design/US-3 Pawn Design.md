# Pawn Move Design (US-3)

### Changes

#### Piece
- Make abstract (coordinated with Knight design)
- Add `getCandidateMoves(Position position): List<Position>` - abstract
- Add `markMoved(): void` - non-abstract no-op; Pawn overrides, all others inherit no-op

#### Board
- Add `getValidMoves(Position position): List<Position>`
    - Calls `getCandidateMoves` on piece at position
    - Removes squares occupied by a same-color piece
    - Pawn-specific: if one-square-forward is occupied, remove both candidates; if only two-square-forward is occupied, remove that candidate only
- Add `movePiece(Position source, Position destination): void`
    - Throws `IllegalArgumentException` if source is empty or destination not in `getValidMoves(source)`
    - Replaces any piece at destination (capture)
    - Calls `piece.markMoved()` after placement
- Add `createPiece(PieceType type, Color color): Piece` - private factory for `initializeBoard()`; must produce `Pawn` when type is PAWN

### Additions

#### Pawn extends Piece

Fields:
- `hasMoved: boolean` (private) - initially false

Methods:
- `Pawn(Color color)` - calls `super(PieceType.PAWN, color)`
- `getCandidateMoves(Position position): List<Position>` - WHITE moves toward higher rows, BLACK toward lower rows; always includes one-square-forward if in bounds; includes two-square-forward only if `hasMoved` is false and in bounds; does not consider board occupancy
- `markMoved(): void` - overrides `Piece.markMoved()`, sets `hasMoved` to true
- `hasMoved(): boolean` - returns `hasMoved`