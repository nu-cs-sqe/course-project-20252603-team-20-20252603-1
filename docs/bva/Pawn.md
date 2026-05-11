# BVA: Pawn

### Method under test: `Pawn(Color color)`

| ID  | State of the System | Expected output                                                             | Implemented?       |
|-----|---------------------|-----------------------------------------------------------------------------|--------------------|
| TC1 | color=WHITE         | Pawn created, `getPieceType()`=PAWN, `getColor()`=WHITE, `hasMoved()`=false | :white_check_mark: |
| TC2 | color=BLACK         | Pawn created, `getPieceType()`=PAWN, `getColor()`=BLACK, `hasMoved()`=false | :white_check_mark: |
| TC3 | color=null          | `IllegalArgumentException` thrown                                           | :white_check_mark: |

### Method under test: `getCandidateMoves(Position position)`

`hasMoved` is a Boolean (false, true). `position` row is an Interval [1,8]. The key variable interaction is `hasMoved` & `position.row` & `color`

**WHITE pawn (moves toward higher rows):**

| ID  | State of the System                                        | Expected output                             | Implemented?       |
|-----|------------------------------------------------------------|---------------------------------------------|--------------------|
| TC4 | WHITE pawn, `hasMoved`=false, position=`(2,1)` (min col)   | `[(3,1), (4,1)]` - two candidates           | :white_check_mark: |
| TC5 | WHITE pawn, `hasMoved`=false, position=`(2,8)` (max col)   | `[(3,8), (4,8)]` - two candidates           | :white_check_mark: |
| TC6 | WHITE pawn, `hasMoved`=true, position=`(3,1)`              | `[(4,1)]` - one candidate only              | :white_check_mark: |
| TC7 | WHITE pawn, `hasMoved`=true, position=`(7,1)` (row HIGH-1) | `[(8,1)]` - one candidate                   | :white_check_mark: |
| TC8 | WHITE pawn, `hasMoved`=true, position=`(8,1)` (row HIGH)   | `[]` - empty list, no forward square exists | :white_check_mark: |

**BLACK pawn (moves toward lower rows):**

| ID   | State of the System                                       | Expected output                             | Implemented?       |
|------|-----------------------------------------------------------|---------------------------------------------|--------------------|
| TC9  | BLACK pawn, `hasMoved`=false, position=`(7,1)` (min col)  | `[(6,1), (5,1)]` - two candidates           | :white_check_mark: |
| TC10 | BLACK pawn, `hasMoved`=true, position=`(6,1)`             | `[(5,1)]` - one candidate only              | :x:                |
| TC11 | BLACK pawn, `hasMoved`=true, position=`(2,1)` (row LOW+1) | `[(1,1)]` - one candidate                   | :x:                |
| TC12 | BLACK pawn, `hasMoved`=true, position=`(1,1)` (row LOW)   | `[]` - empty list, no forward square exists | :x:                |

### Method under test: `markMoved()`

| ID   | State of the System                                   | Expected output                        | Implemented? |
|------|-------------------------------------------------------|----------------------------------------|--------------|
| TC13 | new Pawn, `hasMoved`=false, `markMoved()` called once | `hasMoved()` returns true              | :x:          |
| TC14 | `markMoved()` called a second time                    | `hasMoved()` returns true (idempotent) | :x:          |

### Method under test: `hasMoved()`

| ID   | State of the System            | Expected output            | Implemented? |
|------|--------------------------------|----------------------------|--------------|
| TC15 | new Pawn, before `markMoved()` | `hasMoved()` returns false | :x:          |
| TC16 | after `markMoved()` called     | `hasMoved()` returns true  | :x:          |