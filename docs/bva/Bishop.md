# BVA: Bishop

### Method under test: `Bishop(Color color)`

| ID  | State of the System | Expected output                                             | Implemented?       |
| --- | ------------------- | ----------------------------------------------------------- | ------------------ |
| TC1 | color=`WHITE`       | Bishop created, `getPieceType()`=BISHOP, `getColor()`=WHITE | :white_check_mark: |
| TC2 | color=`BLACK`       | Bishop created, `getPieceType()`=BISHOP, `getColor()`=BLACK | :white_check_mark: |
| TC3 | color=`null`        | `IllegalArgumentException` thrown                           | :x:                |

### Method under test: `getCandidateMoves(Position position)`

#### Input States

```math
\{\text{WHITE}, \text{BLACK} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

#### Output State

*Note: There is no valid position such that from the bishop's visibility, there will be fewer than 7 candidate moves — a bishop on any square always has at least 7 candidate moves. We cannot test list sizes smaller than 7.*

*Note: There are at most 13 possible bishop moves, so our list has a maximum possible size of 13 and never contains duplicates.*

```math
\{\text{size 7}, \text{size 13} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

| ID   | State of the System             | Expected output                                                                                        | Implemented? |
| ---- | ------------------------------- | ------------------------------------------------------------------------------------------------------ | ------------ |
| TC4  | color=`WHITE`, position=`(1,4)` | returns: `[(2,5), (3,6), (4,7), (5,8), (2,3), (3,2), (4,1)]`                                           | :x:          |
| TC5  | color=`BLACK`, position=`(8,4)` | returns: `[(7,5), (6,6), (5,7), (4,8), (7,3), (6,2), (5,1)]`                                           | :x:          |
| TC6  | color=`WHITE`, position=`(4,1)` | returns: `[(5,2), (6,3), (7,4), (8,5), (3,2), (2,3), (1,4)]`                                           | :x:          |
| TC7  | color=`BLACK`, position=`(4,8)` | returns: `[(5,7), (6,6), (7,5), (8,4), (3,7), (2,6), (1,5)]`                                           | :x:          |
| TC8  | color=`WHITE`, position=`(1,1)` | returns: `[(2,2), (3,3), (4,4), (5,5), (6,6), (7,7), (8,8)]`                                           | :x:          |
| TC9  | color=`BLACK`, position=`(8,8)` | returns: `[(7,7), (6,6), (5,5), (4,4), (3,3), (2,2), (1,1)]`                                           | :x:          |
| TC10 | color=`BLACK`, position=`(4,4)` | returns: `[(5,5), (6,6), (7,7), (8,8), (5,3), (6,2), (7,1), (3,5), (2,6), (1,7), (3,3), (2,2), (1,1)]` | :x:          |
| TC11 | color=`WHITE`, position=`(2,3)` | returns: `[(3,4), (4,5), (5,6), (6,7), (7,8), (3,2), (4,1), (1,4), (1,2)]`                             | :x:          |
| TC12 | color=`BLACK`, position=`(7,6)` | returns: `[(8,7), (8,5), (6,7), (5,8), (6,5), (5,4), (4,3), (3,2), (2,1)]`                             | :x:          |
| TC13 | color=`WHITE`, position=`(6,7)` | returns: `[(7,8), (7,6), (8,5), (5,8), (5,6), (4,5), (3,4), (2,3), (1,2)]`                             | :x:          |
