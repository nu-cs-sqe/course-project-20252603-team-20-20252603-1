# BVA: Queen

### Method under test: Queen(Color color)

| ID  | State of the System | Expected output                                       | Implemented?       |
|-----|---------------------|-------------------------------------------------------|--------------------|
| TC1 | color=WHITE         | Queen created, getPieceType()=QUEEN, getColor()=WHITE | :white_check_mark: |
| TC2 | color=BLACK         | Queen created, getPieceType()=QUEEN, getColor()=BLACK | :white_check_mark: |
| TC3 | color=null          | IllegalArgumentException thrown                       | :white_check_mark: |

### Method under test: getCandidateMoves(Position position)

position.row and position.col are each an interval [1, 8]; their combined boundary pairs determine which directional rays are clipped by the board edge. Color does not affect the queen's movement directions.

#### Input States

- (1,1), (1,8), (8,1), (8,8) - all four corners
- (1,4), (8,4), (4,1), (4,8) - all four edge midpoints
- (4,4) - interior position

#### Output State

Note: getCandidateMoves models an empty board. From every position on an 8x8 board the queen can reach at least 21 squares, so the list is never empty. From all four corners and all four edge midpoints the result size is exactly 21; from the interior position (4,4) it is 27.

| ID   | State of the System                              | Expected output                                                                                                                                         | Implemented?       |
|------|--------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------|
| TC4  | color=WHITE, position=(1,1) (row LOW, col LOW)   | returns 21 squares - N: (2,1)-(8,1), E: (1,2)-(1,8), NE: (2,2)-(8,8)                                                                                    | :white_check_mark: |
| TC5  | color=BLACK, position=(8,8) (row HIGH, col HIGH) | returns 21 squares - S: (7,8)-(1,8), W: (8,7)-(8,1), SW: (7,7)-(1,1)                                                                                    | :white_check_mark: |
| TC6  | color=WHITE, position=(1,8) (row LOW, col HIGH)  | returns 21 squares - N: (2,8)-(8,8), W: (1,7)-(1,1), NW: (2,7)-(8,1)                                                                                    | :white_check_mark: |
| TC7  | color=BLACK, position=(8,1) (row HIGH, col LOW)  | returns 21 squares - S: (7,1)-(1,1), E: (8,2)-(8,8), SE: (7,2)-(1,8)                                                                                    | :white_check_mark: |
| TC8  | color=WHITE, position=(1,4) (row LOW, col mid)   | returns 21 squares - N: (2,4)-(8,4), E: (1,5)-(1,8), W: (1,3)-(1,1), NE: (2,5)-(5,8), NW: (2,3)-(4,1); no S/SE/SW rays                                  | :white_check_mark: |
| TC9  | color=BLACK, position=(8,4) (row HIGH, col mid)  | returns 21 squares - S: (7,4)-(1,4), E: (8,5)-(8,8), W: (8,3)-(8,1), SE: (7,5)-(4,8), SW: (7,3)-(5,1); no N/NE/NW rays                                  | :white_check_mark: |
| TC10 | color=WHITE, position=(4,1) (row mid, col LOW)   | returns 21 squares - N: (5,1)-(8,1), S: (3,1)-(1,1), E: (4,2)-(4,8), NE: (5,2)-(8,5), SE: (3,2)-(1,4); no W/NW/SW rays                                  | :white_check_mark: |
| TC11 | color=BLACK, position=(4,8) (row mid, col HIGH)  | returns 21 squares - N: (5,8)-(8,8), S: (3,8)-(1,8), W: (4,7)-(4,1), NW: (5,7)-(8,4), SW: (3,7)-(1,5); no E/NE/SE rays                                  | :white_check_mark: |
| TC12 | color=WHITE, position=(4,4) (row mid, col mid)   | returns 27 squares - N: (5,4)-(8,4), S: (3,4)-(1,4), E: (4,5)-(4,8), W: (4,3)-(4,1), NE: (5,5)-(8,8), NW: (5,3)-(7,1), SE: (3,5)-(1,7), SW: (3,3)-(1,1) | :white_check_mark: |

### Method under test: getSlidingDirections()

| ID   | State of the System | Expected output                                                                    | Implemented?       |
|------|---------------------|------------------------------------------------------------------------------------|--------------------|
| TC13 | any Queen instance  | returns 8 direction vectors: {1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1} | :white_check_mark: |
