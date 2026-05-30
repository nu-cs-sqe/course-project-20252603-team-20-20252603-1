# Queen Class Design

## Overview

The queen combines rook and bishop movement, sliding any number of squares in all eight directions. This introduces sliding piece behavior, unlike the knight, whose candidate moves can be filtered independently by color, a sliding piece cannot jump over pieces, so path-blocking must be resolved during move validation. To support this, `Piece` gains a `getSlidingDirections()` hook, and`Board.getValidMoves` gains logic that uses it.

## Changes

### Piece

1. Add `getSlidingDirections(): int[][]` - non-abstract, returns an empty array by default. Sliding pieces (Queen, Rook, Bishop) override to return their directional vectors.

### Board

1. Extend `getValidMoves(Position position): List<Position>` - for pieces whose `getSlidingDirections()` is non-empty, replace the flat same-color filter with ray-casting: trace each direction from origin until the board boundary, stopping at the first occupied square(inclusive for an enemy piece, exclusive for a friendly piece). For pieces with an empty sliding directions array (Knight, Pawn) the existing behavior is unchanged.
2. Update `createPiece(PieceType type, Color color): Piece` - add `QUEEN` case to produce a `Queen` instance.

## Additions

### `Queen extends Piece`

1. `Queen(Color color)` - calls `super(PieceType.QUEEN, color)`.
2. `getSlidingDirections(): int[][]` - returns all eight direction vectors: `{{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}}`.
3. `getCandidateMoves(Position position): List<Position>` - returns all in-bounds squares reachable along the eight sliding directions, as if the board were empty (does not consider occupancy).