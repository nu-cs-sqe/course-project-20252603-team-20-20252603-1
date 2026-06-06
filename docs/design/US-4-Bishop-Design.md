# Bishop Class Design

## Additions

### `Bishop extends Piece`

1. `Bishop(Color color)` calls `super(PieceType.BISHOP, color)`. 
2. `getCandidateMoves(Position position)` returns list of diagonal moves from `position` that are within the board dimensions
