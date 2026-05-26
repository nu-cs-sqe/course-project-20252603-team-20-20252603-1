package ui;

import domain.Board;
import domain.Position;
import domain.piece.Piece;

public class BoardController {
    private BoardView boardView;
    private Board board;

    public BoardController() {
        this.board = new Board();
        board.initializeBoard();
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public void handleSquareClick(Position location) {
        // TODO
        System.out.println("TEST: Square clicked at " + location.getRow() + ", " + location.getCol());
    }

    public Piece[][] getBoardSnapshot() {
        return this.board.getSnapshot();
    }
}