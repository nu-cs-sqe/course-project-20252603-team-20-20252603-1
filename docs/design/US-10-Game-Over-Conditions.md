# Other Game-Ending Conditions

## Additions

### `enum GameState`

- `TIMEOUT`
- `TIMEOUT_VS_INSUFFICIENT_MATERIAL`

### Game

1. `public void handleTimeout(Color loser)`
    1. Throws IllegalStateException if game has not started
    2. Throws IllegalStateException if isGameOver() is true
    3. If `board.hasInsufficientMaterial(loser)`, sets state to `TIMEOUT_VS_INSUFFICIENT_MATERIAL`
    4. Otherwise, sets state to `TIMEOUT`

2. `private boolean checkForStalemate()`
    1. Returns true if player is not in check, and `board.getValidMovesForPlayer` is empty.

3. `private boolean checkForInsufficientMaterial()`
    1. Returns true if `board.hasInsufficientMaterial(WHITE)` and `board.hasInsufficientMaterial(BLACK)` are both true

4. `checkForGameEnd()` 
    1. Add case for INSUFFICIENT_MATERIAL
    2. Add case for STALEMATE

5. `isGameOver()` 
    1. Add case for INSUFFICIENT_MATERIAL
    2. Add case for STALEMATE
    3. Add case for TIMEOUT
    4. Add case for TIMEOUT_VS_INSUFFICIENT_MATERIAL

6. `whyIsGameOver()` 
    1. Add case for INSUFFICIENT_MATERIAL
    2. Add case for STALEMATE
    3. Add case for TIMEOUT
    4. Add case for TIMEOUT_VS_INSUFFICIENT_MATERIAL

### BoardController
- Fix TODO in `handleTimeout(Color loser)` with `game.handleTimeout(loser)`

### Board
1. `public boolean hasInsufficientMaterial(Color color)`
    1. Throws IllegalArgumentException if color is null
    2. Returns true if the given color has only a king, or king + single bishop or knight, or king + two knights
    3. Returns false otherwise