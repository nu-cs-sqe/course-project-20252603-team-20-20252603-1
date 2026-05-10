package domain;

import domain.piece.Color;
import domain.piece.Piece;

public class Game {
    private Board board;
    private Color currentTurn;

    public Game(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("No valid board passed");
        }
        this.board = board;
    }

    public void startGame() {
        board.initializeBoard();
        
        this.currentTurn = Color.WHITE;
    }

    public Piece getPieceAt(Position pos) {
        return board.getPieceAt(pos);
    }
}
