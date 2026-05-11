package domain;

import domain.piece.Color;
import domain.piece.Piece;

public class Game {
    private final Board board;
    private Color currentTurn;
    private boolean gameInProgress = false;

    public Game(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("No valid board passed");
        }
        this.board = board;
    }

    public void startGame() {
        board.initializeBoard();
        this.gameInProgress = true;
        this.currentTurn = Color.WHITE;
    }

    public Color getCurrentTurn() {
        if (!this.gameInProgress) {
            throw new IllegalStateException("Game has not started yet, no player has a turn");
        }

        return this.currentTurn;
    }

    public Piece getPieceAt(Position pos) {
        if (!this.gameInProgress) {
            throw new IllegalStateException("Game has not started yet, no pieces are on the board");
        }
        return board.getPieceAt(pos);
    }
}
