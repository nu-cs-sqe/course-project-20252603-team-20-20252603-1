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
| `(4, 8)` | :x:                |

#### PieceType of Attacker

| Value    | Covered            |
| -------- | ------------------ |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Queen`  | :white_check_mark: |
| `Knight` | :x:                |
| `Pawn`   | :x:                |

#### Attacker Position

| Value    | Covered            |
| -------- | ------------------ |
| `(1, 4)` | :white_check_mark: |
| `(4, 1)` | :x:                |
| `(8, 4)` | :white_check_mark: |
| `(4, 8)` | :x:                |

---

### \<player\> is in check by more than one piece

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` | :x:      |
| `Black` | :x:      |

#### King Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |

#### PieceType of Attacker

| Value    | Covered |
| -------- | ------- |
| `Rook`   | :x:     |
| `Bishop` | :x:     |
| `Queen`  | :x:     |
| `Knight` | :x:     |
| `Pawn`   | :x:     |

#### Attacker Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |

---

### \<player\> is not in check

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` | :x:      |
| `Black` | :x:      |

#### `gameInProgress`

| Value   | Covered |
| ------- | ------- |
| `true`  | :x:     |
| `false` | :x:     |

#### King Position (row, col)

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |

#### PieceType of Attacker

| Value    | Covered |
| -------- | ------- |
| `Rook`   | :x:     |
| `Bishop` | :x:     |
| `Queen`  | :x:     |
| `Knight` | :x:     |

#### Attacker Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |

---

### \<player\> is blocking check

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` | :x:      |
| `Black` | :x:      |

#### King Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |

#### PieceType of Attacker

| Value    | Covered |
| -------- | ------- |
| `Rook`   | :x:     |
| `Bishop` | :x:     |
| `Queen`  | :x:     |
| `Knight` | :x:     |
| `Pawn`   | :x:     |

#### Attacker Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |


#### PieceType of Blocker

| Value    | Covered |
| -------- | ------- |
| `Rook`   | :x:     |
| `Bishop` | :x:     |
| `Queen`  | :x:     |
| `Knight` | :x:     |
| `Pawn`   | :x:     |

#### Blocker Position

| Value    | Covered |
| -------- | ------- |
| `(1, 4)` | :x:     |
| `(4, 1)` | :x:     |
| `(8, 4)` | :x:     |
| `(4, 8)` | :x:     |
