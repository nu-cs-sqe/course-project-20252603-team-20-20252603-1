# Knight Class Design

## Overview

At this point, it makes sense to refactor `Piece` to be an abstract base class that pawns, knights, bishops, etc. inherit from. Each subclass will implement a method to output candidate moves (i.e. diagonal positions for a bishop or L-shapes for the knight). The `Board` class can then implement `getValidMoves(Position)` to filter candidate moves based off the board state. 

## Changes

### Piece

1. Make abstract
2. Add `getCandidateMoves(Position position) -> List<Position>` - abstract method that returns reachable squares from `position` for that piece.

### Board
1. Add `getValidMoves(Position position) -> List<Position>` - filter `getCandidateMoves(Position position)` based on board state.
2. Add `movePiece(Position source, Position destination) -> void` - moves piece from source to destination. Throws `IllegalArgumentException` if `source` is empty or `destination` is not in `getValidMoves(source)`. 
3. Add private factory `createPiece(PieceType type, Color color) -> Piece` - creates `Piece` subclass instances to be used in `initializeBoard()`.

## Additions

### `Knight extends Piece`

1. `Knight(Color color)` calls `super(PieceType.KNIGHT, color)`. 
2. `getCandidateMoves(Position position)` returns list of L-shaped moves from `position` that are within the board dimensions
