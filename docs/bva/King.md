# BVA: King

### Method under test: `King(Color color)`

| ID  | State of the System | Expected output                                         | Implemented? |
| --- | ------------------- | ------------------------------------------------------- | ------------ |
| TC1 | color=`WHITE`       | King created, `getPieceType()`=KING, `getColor()`=WHITE | :x:          |
| TC2 | color=`BLACK`       | King created, `getPieceType()`=KING, `getColor()`=BLACK | :x:          |
| TC3 | color=`null`        | `IllegalArgumentException` thrown                       | :x:          |

### Method under test: `getCandidateMoves(Position position)`

#### Input States

```math
\{\text{WHITE}, \text{BLACK} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

#### Output State

*Note: There is no valid position such that from the king's visibility, there will be less than three candidate moves, so we cannot test the empty list or size 1 case.*

*Note: There are at most 8 possible king moves, so our list has a maximum possible size of 8 and never contains duplicates.*

```math
\{\text{size > 2}, \text{size 8} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

| ID   | State of the System             | Expected output                                                     | Implemented? |
| ---- | ------------------------------- | ------------------------------------------------------------------- | ------------ |
| TC4  | color=`WHITE`, position=`(1,4)` | returns: `[(2,4), (1,3), (1,5), (2,5), (2,3)]`                      | :x:          |
| TC5  | color=`BLACK`, position=`(8,4)` | returns: `[(7,4), (8,3), (8,5), (7,5), (7,3)]`                      | :x:          |
| TC6  | color=`WHITE`, position=`(4,1)` | returns: `[(5,1), (3,1), (4,2), (5,2), (3,2)]`                      | :x:          |
| TC7  | color=`BLACK`, position=`(4,8)` | returns: `[(5,8), (3,8), (4,7), (5,7), (3,7)]`                      | :x:          |
| TC8  | color=`WHITE`, position=`(1,1)` | returns: `[(2,1), (1,2), (2,2)]`                                    | :x:          |
| TC9  | color=`BLACK`, position=`(8,8)` | returns: `[(7,8), (8,7), (7,7)]`                                    | :x:          |
| TC10 | color=`BLACK`, position=`(4,4)` | returns: `[(5,4), (3,4), (4,3), (4,5), (5,5), (5,3), (3,5), (3,3)]` | :x:          |
