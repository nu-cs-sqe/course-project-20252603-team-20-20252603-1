# Castling (US-11)

### Changes

#### Rook
- Add `hasMoved: boolean` (private) - initially false
- Add `hasMoved(): boolean` - returns `hasMoved`
- Override `markMoved(): void` - sets `hasMoved` to true

#### Board
- Add private `getCastlingMoves(Position from, Piece king): List<Position>` - for each side (kingside rook col 8, queenside rook col 1): checks king has not moved, corner rook exists and has not moved, all squares between king and rook are empty, king is not currently in check, and the square the king passes through is not attacked by an opponent; adds `(row, 7)` for valid kingside and `(row, 3)` for valid queenside
- Add private `isCastlingMove(Position from, Position to): boolean` - true iff piece at `from` is a KING and `to` is exactly two columns away on the same row
- Add private `executeCastle(Position kingFrom, Position kingTo)` - moves the rook from its corner to the square the king crossed; direction determined by `kingTo` column relative to `kingFrom`
- Modify `getNonSlidingValidMoves`: after collecting candidate king moves, call `getCastlingMoves` and add results
- Modify `movePiece`: detect castling via `isCastlingMove` before moving; after moving the king, call `executeCastle` to reposition the rook and call `markMoved()` on the rook
- Modify `moveLeavesPlayerInCheck`: when `isCastlingMove` is true, also move the rook in the simulated board before checking for check