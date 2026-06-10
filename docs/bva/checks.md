# BVA: Detect Check Integration Test

See `src/test/resources/features/DetectCheck.feature` for the Scenario Outlines.

## Scenarios

### \<player\> is in check by one piece

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### King Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

#### PieceType of Attacker

| Value    | Covered                  |
| -------- | ------------------------ |
| `Rook`   | :white_check_mark:       |
| `Bishop` | :white_check_mark:       |
| `Queen`  | :white_check_mark:       |
| `Knight` | :white_check_mark:       |
| `Pawn`   | :hourglass_flowing_sand: |

#### Attacker Position

| Value    | Covered                  |
| -------- | ------------------------ |
| `(1, 4)` | :white_check_mark:       |
| `(4, 1)` | :white_check_mark:       |
| `(8, 4)` | :white_check_mark:       |
| `(4, 8)` | :hourglass_flowing_sand: |

---

### \<player\> is in check by more than one piece

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### King Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

#### PieceType of Attacker

| Value    | Covered                  |
| -------- | ------------------------ |
| `Rook`   | :white_check_mark:       |
| `Bishop` | :white_check_mark:       |
| `Queen`  | :white_check_mark:       |
| `Knight` | :white_check_mark:       |
| `Pawn`   | :hourglass_flowing_sand: |

#### Attacker Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

---

### \<player\> is not in check

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### `gameInProgress`

| Value   | Covered            |
| ------- | ------------------ |
| `true`  | :white_check_mark: |
| `false` | :white_check_mark: |

#### King Position (row, col)

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

#### PieceType of Attacker

| Value    | Covered            |
| -------- | ------------------ |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Queen`  | :white_check_mark: |
| `Knight` | :white_check_mark: |
| `Pawn`   | :white_check_mark: |


#### Attacker Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

---

### \<player\> is blocking check

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### King Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |

#### PieceType of Attacker

| Value    | Covered            |
| -------- | ------------------ |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Queen`  | :white_check_mark: |

#### Attacker Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :x:                |
| `(4, 1)` | :white_check_mark: |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :white_check_mark: |


#### PieceType of Blocker

| Value    | Covered            |
| -------- | ------------------ |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Queen`  | :white_check_mark: |
| `Knight` | :white_check_mark: |
| `Pawn`   | :x:                |

#### Blocker Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |
