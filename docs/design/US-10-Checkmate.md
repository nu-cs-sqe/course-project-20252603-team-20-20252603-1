# Checkmate

## Additions

### `enum GameState`

- `CHECKMATE`, `STALEMATE`, `INSUFFICIENT_MATERIAL`, `IN_PROGRESS`, `NOT_STARTED`

### Game

1. Set member variable to `IN_PROGRESS` once game has started.
2. `private checkForGameEnd` that calls 
   1. `private checkForCheckmate`
      1. sets game state to `CHECKMATE` if current player is in check and valid moves are empty.
3. `public boolean isGameOver()`
   1. throws `IllegalStateException` if game has not started
   2. returns `false` if game is in progress
   3. returns `true` if game has ended
4. `public GameState whyIsGameOver()`
   1. throws `IllegalStateException` if game has not started
   2. `IllegalStateException` if `isGameOver()` is `false`
   2. returns `GameState`

## Additions
### Game
- call `checkForGameEnd` after `executeMove`