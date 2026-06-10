# En Passant

### Changes

#### Piece
- Add `getCaptureMoves(Position position): List<Position>` - non-abstract, returns empty list by default; Pawn overrides

#### Pawn
- Add `getCaptureMoves(Position position): List<Position>` - returns at-most two diagonal forward squares (in the pawn's direction, col+/-1) that are in bounds; does not consider board occupancy

#### Board
- Add `enPassantTarget: Optional<Position>` field - initially empty; holds the square a double-advancing pawn "passed through"
- Add `getEnPassantTarget(): Optional<Position>` - package-visible accessor for tests
- Add private `addPawnCaptureMoves(Position position, Piece piece, List<Position> validMoves)` - calls `piece.getCaptureMoves(position)`; adds each diagonal square if it holds an enemy piece (regular diagonal capture) OR equals `enPassantTarget` (en passant)
- Add private `isEnPassantCapture(Piece piece, Position from, Position to): boolean` - true iff piece is PAWN, `enPassantTarget` is present, `to` equals `enPassantTarget`, and `to` is currently empty
- Add private `updateEnPassantTarget(Piece piece, Position from, Position to)` - sets `enPassantTarget` to the passed-through square if a pawn moved two rows; otherwise clears it
- Modify `getNonSlidingValidMoves`: call `addPawnCaptureMoves` when `isPawn` is true
- Modify `movePiece`: detect en passant capture before the move; after the physical move, if en passant, remove captured pawn at `(from.getRow(), to.getCol())`; call `updateEnPassantTarget` at end
- Modify `moveLeavesPlayerInCheck`: if `isEnPassantCapture`, also remove the captured pawn from the simulated board (prevents ghost pawn from masking check)