# BVA: Pawn Promotion Integration Test

See `src/test/resources/features/PawnPromotion.feature ` for the Scenario Outlines.

## Scenarios

### \<player\> promotes by moving to back rank

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Pawn Position before move

| Value    | Covered?           |
| -------- | ------------------ |
| `(7, 1)` | :white_check_mark: |
| `(7, 4)` | :white_check_mark: |
| `(7, 8)` | :white_check_mark: |
| `(2, 1)` | :white_check_mark: |
| `(2, 4)` | :white_check_mark: |
| `(2, 8)` | :white_check_mark: |

#### Promotion Piece

| Value    |      Covered?      |
| -------- | ------------------ |
| `Queen`  | :white_check_mark: |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Knight` | :white_check_mark: |

### \<player\> promotes by capturing to back rank

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Pawn Position before capture

| Value    | Covered?           |
| -------- | ------------------ |
| `(7, 1)` | :white_check_mark: |
| `(7, 4)` | :white_check_mark: |
| `(7, 8)` | :white_check_mark: |
| `(2, 1)` | :white_check_mark: |
| `(2, 4)` | :white_check_mark: |
| `(2, 8)` | :white_check_mark: |

#### Capture Direction

| Value            | Covered?           |
| ---------------- | ------------------ |
| `left diagonal`  | :white_check_mark: |
| `right diagonal` | :white_check_mark: |

#### Promotion Piece

| Value    | Covered?           |
| -------- | ------------------ |
| `Queen`  | :white_check_mark: |
| `Rook`   | :white_check_mark: |
| `Bishop` | :white_check_mark: |
| `Knight` | :white_check_mark: |

### Promoted Queen behaves correctly

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Move direction

| Value         | Covered?           |
| ------------- | ------------------ |
| `horizontal`  | :white_check_mark: |
| `vertical`    | :white_check_mark: |
| `diagonal`    | :white_check_mark: |

### Promoted Rook behaves correctly

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Move direction

| Value         | Covered?           |
| ------------- | ------------------ |
| `horizontal`  | :white_check_mark: |
| `vertical`    | :white_check_mark: |

### Promoted Bishop behaves correctly

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Move direction

| Value      | Covered?           |
| ---------- | ------------------ |
| `diagonal` | :white_check_mark: |

### Promoted Knight behaves correctly

#### `player`

| Value   | Covered?           |
| ------- | ------------------ |
| `White` | :white_check_mark: |
| `Black` | :white_check_mark: |

#### Move direction

| Value     | Covered?           |
| --------- | ------------------ |
| `l-shape` | :white_check_mark: |