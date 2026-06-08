# Pawn Promotion Design (US-6)

## Overview

When a pawn reaches the opponent's back rank (row 8 for WHITE, row 1 for BLACK) it must be replaced by a QUEEN, ROOK, BISHOP, or KNIGHT. The promoting move can be a forward push or a diagonal capture. Pawn diagonal captures are introduced here as they were deferred from US-4.

After the move, the game enters a promotion-pending state and pauses the turn until the player selects a piece. The UI shows a modal dialog for piece selection.

## Changes

### Piece
- Add `getCapturePositions(Position position): List<Position>` - non-abstract, returns empty list by default; Pawn overrides

### Pawn
- Override `getCapturePositions(Position position): List<Position>` - returns forward-diagonal squares (col +/- 1 in movement direction) that are in bounds; does not check occupancy

### Board
- Modify `getNonSlidingValidMoves` - for pawns, include squares from `getCapturePositions` that contain an enemy piece
- Add `promotePawn(Position position, PieceType pieceType): void` - replaces the pawn at `position` with a new piece of the same color; throws `IllegalArgumentException` if position is not a pawn at the promotion rank, or if `pieceType` is PAWN or KING

### Game
- Add `promotionPending: boolean` - private, initially false
- Add `promotionPosition: Optional<Position>` - private, initially empty
- Modify `executeMove` - after `board.movePiece`, if the moved piece is a PAWN at the promotion rank, set `promotionPending` and return without switching turns; throw `IllegalStateException` if called while `promotionPending` is true
- Add `isPromotionPending(): boolean` - returns `promotionPending`
- Add `executePromotion(PieceType pieceType): void` - throws `IllegalStateException` if not pending; calls `board.promotePawn`; clears promotion state; switches turn

### BoardController
- Add `Component dialogParent` field; update constructor to `BoardController(BoardChangeListener changeListener, Component parent)`
- After `game.executeMove`, check `isPromotionPending`; if true, show `PromotionDialog` and call `game.executePromotion` with the result
- Update `BoardView` to pass `this` as the second constructor argument

## Additions

### PromotionDialog extends JDialog
- `PromotionDialog(Frame parent, Color pawnColor)` - modal dialog with four buttons for QUEEN, ROOK, BISHOP, KNIGHT; loads piece images using the same classpath convention as `BoardView`; `setDefaultCloseOperation(DO_NOTHING_ON_CLOSE)` to require a selection
- `showAndGetResult(): PieceType` - makes dialog visible and returns the selected piece type