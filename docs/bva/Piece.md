# BVA: Piece

### Method under test: `Piece(PieceType type, Color color)`
|     | State of the System      | Expected output                 | Implemented?       |
|-----|--------------------------|---------------------------------|--------------------|
| TC1 | type=KING, color=WHITE   | Piece created successfully      | :white_check_mark: |
| TC2 | type=QUEEN, color=BLACK  | Piece created successfully      | :white_check_mark: |
| TC3 | type=ROOK, color=WHITE   | Piece created successfully      | :white_check_mark: |
| TC4 | type=BISHOP, color=BLACK | Piece created successfully      | :white_check_mark: |
| TC5 | type=KNIGHT, color=WHITE | Piece created successfully      | :white_check_mark: |
| TC6 | type=PAWN, color=BLACK   | Piece created successfully      | :white_check_mark: |
| TC7 | type=null, color=WHITE   | IllegalArgumentException thrown | :white_check_mark: |
| TC8 | type=KING, color=null    | IllegalArgumentException thrown | :white_check_mark: |

### Method under test: `getColor()`
|      | State of the System               | Expected output      | Implemented? |
|------|-----------------------------------|----------------------|--------------|
| TC9  | Piece created with color=WHITE    | Returns Color.WHITE  | :x:          |
| TC10 | Piece created with color=BLACK    | Returns Color.BLACK  | :x:          |

### Method under test: `getPieceType()`
|      | State of the System               | Expected output          | Implemented? |
|------|-----------------------------------|--------------------------|--------------|
| TC11 | Piece created with type=KING      | Returns PieceType.KING   | :x:          |
| TC12 | Piece created with type=QUEEN     | Returns PieceType.QUEEN  | :x:          |
| TC13 | Piece created with type=ROOK      | Returns PieceType.ROOK   | :x:          |
| TC14 | Piece created with type=BISHOP    | Returns PieceType.BISHOP | :x:          |
| TC15 | Piece created with type=KNIGHT    | Returns PieceType.KNIGHT | :x:          |
| TC16 | Piece created with type=PAWN      | Returns PieceType.PAWN   | :x:          |