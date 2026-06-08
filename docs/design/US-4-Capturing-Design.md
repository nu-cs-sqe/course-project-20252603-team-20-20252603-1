# Capturing Design

## Overview

When a piece moves to a square occupied by an opponent piece, it captures that piece, removing it from the board. Valid moves must exclude friendly squares and include enemy squares, with sliding pieces stopping their ray at any occupied square. This implements getValidMoves and movePiece from prior designs, enforces turn order in Game, and wires up the selection and highlight flow in the UI. Pawn diagonal capture is out of scope and handled in a separate sub-issue.

## Changes

### Piece

1. Add `getCandidateMoves(Position position) -> List<Position>` - non-abstract, returns empty list. Subclass overrides take precedence; allows Board to call the method on any Piece reference.
2. Add `markMoved() -> void` - non-abstract, no-op by default. Pawn already overrides this.

### Bishop, Rook, Queen

1. Add `getSlidingDirections() -> int[][]` - returns the piece's direction vectors, overriding the Piece default of an empty array. Used by Board.getValidMoves to distinguish sliding from non-sliding pieces.

### Board

1. Update `createPiece` and `initializeBoard` so pieces stored on the board are proper subclass instances with working getCandidateMoves and getSlidingDirections.
2. Add `placePieceAt(Position position, Piece piece) -> void` - package-private, sets the array cell directly for test setup.
3. Implement `getValidMoves(Position source) -> List<Position>` - for sliding pieces, ray-cast each direction: include empty squares, include the first enemy square and stop, stop before the first friendly square. For Knight, filter getCandidateMoves removing friendly destinations. For Pawn, forward squares are blocked by any piece (friendly or enemy); no diagonal capture logic.
4. Implement `movePiece(Position source, Position destination) -> void` - validates destination is in getValidMoves(source), moves piece to destination overwriting any captured piece, clears source, calls markMoved.

### Game

1. Add `getValidMoves(Position source) -> List<Position>` - guards gameInProgress, delegates to board.
2. Add `makeMove(Position source, Position destination) -> void` - guards gameInProgress, rejects source pieces not belonging to currentTurn, calls board.movePiece, toggles currentTurn.

### BoardController

1. Replace Board field with Game. Constructor creates Board, creates Game, calls startGame.
2. Add selectedPosition and validMoves fields.
3. Implement handleSquareClick: select a friendly piece and compute validMoves on first click; call game.makeMove and deselect on a valid destination click; switch selection on click of another friendly piece; deselect otherwise.
4. Add getValidMoves getter for BoardView.

### BoardView

1. Add a highlight color and drawValidMoves method that shades each square in boardController.getValidMoves. Capturable and reachable empty squares use the same color.
2. Fix coordinate bug in BoardMouseListener: new Position(selectedCol, selectedRow) should be new Position(selectedRow + 1, selectedCol + 1).