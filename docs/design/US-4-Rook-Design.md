# Rook Class Design

## Additions

### `Rook extends Piece`

1. `Rook(Color color)` calls `super(PieceType.ROOK, color)`. 
2. `getCandidateMoves(Position position)` returns list of horizontal or vertical moves from `position` that are within the board dimensions.