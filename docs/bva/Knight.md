# BVA: Knight

### Method under test: `Knight(Color color)`

| ID  | State of the System | Expected output                                             | Implemented?       |
| --- | ------------------- | ----------------------------------------------------------- | ------------------ |
| TC1 | color=`WHITE`       | Knight created, `getPieceType()`=KNIGHT, `getColor()`=WHITE | :white_check_mark: |
| TC2 | color=`BLACK`       | Knight created, `getPieceType()`=KNIGHT, `getColor()`=BLACK | :white_check_mark: |
| TC3 | color=`null`        | `IllegalArgumentException` thrown                           | :white_check_mark: |

### Method under test: `getCandidateMoves(Position position)`

#### Input States

```math
\{\text{WHITE}, \text{BLACK} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

#### Output State

*Note: There is no valid position such that from the knight's visibility, there will be no candidate moves or only one candidate move, so we cannot test the empty list or size 1 case.*

*Note: There are at most 8 possible knight moves, so our list has a maximum possible size of 8 and never contains duplicates.*

```math
\{\text{size > 1}, \text{size 8} \}\times \big\{ (1, 4), (8, 4), (4, 1), (4, 8), (1, 1), (8, 8) \big\}
```

| ID   | State of the System             | Expected output                                                     | Implemented?       |
| ---- | ------------------------------- | ------------------------------------------------------------------- | ------------------ |
| TC4  | color=`WHITE`, position=`(1,4)` | returns: `[(2,6), (2,2), (3, 3), (3, 5)]`                           | :white_check_mark: |
| TC5  | color=`BLACK`, position=`(8,4)` | returns: `[(7,6), (7,2), (6,3), (6,5)]`                             | :white_check_mark: |
| TC6  | color=`WHITE`, position=`(4,1)` | returns: `[(5,3), (6,2), (3,3), (2,2)]`                             | :white_check_mark: |
| TC7  | color=`BLACK`, position=`(4,8)` | returns: `[(5,6), (6,7), (3,6), (2,7)]`                             | :white_check_mark: |
| TC8  | color=`WHITE`, position=`(1,1)` | returns: `[(2,3), (3,2)]`                                           | :white_check_mark: |
| TC9  | color=`BLACK`, position=`(8,8)` | returns: `[(7,6), (6,7)]`                                           | :white_check_mark: |
| TC10 | color=`BLACK`, position=`(3,3)` | returns: `[(4,5), (4,1), (5,2), (5,4), (2,5), (2,1), (1,2), (1,4)]` | :white_check_mark: |
| TC11 | color=`WHITE`, position=`(2,3)` | returns: `[(3,5), (3,1), (4,2), (4,4), (1,5), (1,1)]`               | :white_check_mark: |
| TC12 | color=`BLACK`, position=`(7,6)` | returns: `[(8,8), (8,4), (6,8), (6,4), (5,5), (5,7)]`               | :white_check_mark: |
| TC13 | color=`WHITE`, position=`(6,7)` | returns: `[(7,5), (8,6), (8,8), (5,5), (4,6), (4,8)]`               | :white_check_mark: |
