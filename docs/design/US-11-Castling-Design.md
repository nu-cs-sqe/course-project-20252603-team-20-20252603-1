# Castling (US-11)

### Changes

#### Rook
- Add `hasMoved: boolean` (private) - initially false
- Add `hasMoved(): boolean` - returns `hasMoved`
- Override `markMoved(): void` - sets `hasMoved` to true

#### Board
- Add constants `KINGSIDE_ROOK_COL`, `KINGSIDE_KING_DEST_COL`, `KINGSIDE_ROOK_DEST_COL`, `QUEENSIDE_KING_DEST_COL`, `QUEENSIDE_ROOK_DEST_COL` for castling column values
- Add private `getAttackSquares(Position position): List<Position>` - returns squares attacked by the piece at `position`; uses `getSlidingValidMoves` for sliding pieces, `getCaptureMoves` for pawns, and `getCandidateMoves` for kings and knights; avoids calling `getGeometricMoves` on kings to prevent infinite recursion with castling move generation
- Add private `isSquareAttackedByEnemy(Position target, Color friendlyColor): boolean` - returns true if any enemy piece's `getAttackSquares` contains `target`
- Add private `canCastleKingside(int row, Color color): boolean` - checks rook at `(row, KINGSIDE_ROOK_COL)` exists and has not moved, `(row, 6)` and `(row, 7)` are empty, and `(row, 6)` is not attacked by an enemy
- Add private `canCastleQueenside(int row, Color color): boolean` - checks rook at `(row, 1)` exists and has not moved, `(row, 2)`, `(row, 3)`, and `(row, 4)` are empty, and `(row, 4)` is not attacked by an enemy
- Add private `getCastlingMoves(Position from, Piece king): List<Position>` - returns empty if piece is not a `King` instance, king has moved, or king's current square is attacked by an enemy; otherwise delegates to `canCastleKingside` and `canCastleQueenside` and returns valid destination squares
- Add private `isCastlingMove(Position from, Position to): boolean` - true iff piece at `from` is a KING and `to` is exactly two columns away on the same row
- Add private `executeCastle(Position kingFrom, Position kingTo)` - moves the rook from its corner to the square the king crossed; direction determined by `kingTo` column relative to `kingFrom`; does not call `markMoved()` on the rook
- Modify `getNonSlidingValidMoves`: after collecting candidate king moves, call `getCastlingMoves` and add results
- Modify `movePiece`: detect castling via `isCastlingMove` before moving; after moving the king, call `markMoved()` on the rook then `executeCastle` to reposition it
- Modify `moveLeavesPlayerInCheck`: when `isCastlingMove` is true, also call `executeCastle` on the simulated board before checking for check
