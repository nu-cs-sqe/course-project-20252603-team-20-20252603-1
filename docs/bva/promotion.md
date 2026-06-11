# BVA: Pawn Promotion Integration Test

See `src/test/resources/features/PawnPromotion.feature ` for the Scenario Outlines.

## Scenarios

### \<player\> promotes by moving to back rank

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Pawn Position before move

| Value    | Covered? |
| -------- | -------- |
| `(7, 1)` |    :x:   |
| `(7, 4)` |    :x:   |
| `(7, 8)` |    :x:   |
| `(2, 1)` |    :x:   |
| `(2, 4)` |    :x:   |
| `(2, 8)` |    :x:   |

#### Promotion Piece

| Value    | Covered? |
| -------- | -------- |
| `Queen`  |    :x:   |
| `Rook`   |    :x:   |
| `Bishop` |    :x:   |
| `Knight` |    :x:   |

### \<player\> promotes by capturing to back rank

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Pawn Position before capture

| Value    | Covered? |
| -------- | -------- |
| `(7, 1)` |    :x:   |
| `(7, 4)` |    :x:   |
| `(7, 8)` |    :x:   |
| `(2, 1)` |    :x:   |
| `(2, 4)` |    :x:   |
| `(2, 8)` |    :x:   |

#### Capture Direction

| Value            | Covered? |
| ---------------- | -------- |
| `left diagonal`  |    :x:   |
| `right diagonal` |    :x:   |

#### Promotion Piece

| Value    | Covered? |
| -------- | -------- |
| `Queen`  |    :x:   |
| `Rook`   |    :x:   |
| `Bishop` |    :x:   |
| `Knight` |    :x:   |

### Promoted Queen behaves correctly

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Move direction

| Value         | Covered? |
| ------------- | -------- |
| `horizontal`  |    :x:   |
| `vertical`    |    :x:   |
| `diagonal`    |    :x:   |

### Promoted Rook behaves correctly

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Move direction

| Value         | Covered? |
| ------------- | -------- |
| `horizontal`  |    :x:   |
| `vertical`    |    :x:   |

### Promoted Bishop behaves correctly

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Move direction

| Value      | Covered? |
| ---------- | -------- |
| `diagonal` |    :x:   |

### Promoted Knight behaves correctly

#### `player`

| Value   | Covered? |
| ------- | -------- |
| `White` |    :x:   |
| `Black` |    :x:   |

#### Move direction

| Value     | Covered? |
| --------- | -------- |
| `l-shape` |    :x:   |