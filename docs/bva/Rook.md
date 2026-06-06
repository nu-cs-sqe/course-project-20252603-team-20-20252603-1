# BVA: Rook

### Method under test: `Rook(Color color)`

| ID  | State of the System | Expected output                                         |    Implemented?    |
| --- | ------------------- | ------------------------------------------------------- | ------------------ |
| TC1 | color=`WHITE`       | Rook created, `getPieceType()`=ROOK, `getColor()`=WHITE | :white_check_mark: |
| TC2 | color=`BLACK`       | Rook created, `getPieceType()`=ROOK, `getColor()`=BLACK | :white_check_mark: |
| TC3 | color=`null`        | `IllegalArgumentException` thrown                       |         :x:        |

### Method under test: `getCandidateMoves(Position position)`

#### Input States

```math
\{\text{WHITE}, \text{BLACK} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

#### Output State

*Note: There is no valid position such that from the rook's visibility, there will not be exactly 14 candidate moves — a rook on any square always has 14 candidate moves, so we will only test list size 14. Our list never contains duplicates.*

```math
\{\text{size 14} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

| ID   | State of the System             | Expected output                                                     | Implemented?  |
| ---- | ------------------------------- | ------------------------------------------------------------------- | ------------- |
| TC4  | color=`WHITE`, position=`(1,4)` | returns 14 squares - N: (2,4)-(8,4), W: (1,1)-(1,3), E: (1,5)-(1,8) |      :x:      |
| TC5  | color=`BLACK`, position=`(8,4)` | returns 14 squares - S: (1,4)-(7,4), W: (8,1)-(8,3), E: (8,5)-(8,8) |      :x:      |
| TC6  | color=`WHITE`, position=`(4,1)` | returns 14 squares - N: (5,1)-(8,1), S: (3,1)-(1,1), E: (4,2)-(4,8) |      :x:      |
| TC7  | color=`BLACK`, position=`(4,8)` | returns 14 squares - N: (5,8)-(8,8), S: (3,8)-(1,8), W: (8,1)-(8,7) |      :x:      |
| TC8  | color=`WHITE`, position=`(1,1)` | returns 14 squares - N: (2,1)-(8,1), E: (1,2)-(1,8)                 |      :x:      |
| TC9  | color=`BLACK`, position=`(8,8)` | returns 14 squares - S: (7,8)-(1,8), W: (8,7)-(8,1)                 |      :x:      |