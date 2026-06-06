package ui;

import domain.Board;
import domain.Position;
import domain.piece.Piece;

public class BoardController {
    private final BoardChangeListener changeListener;
    private final Board board;

    public BoardController(BoardChangeListener changeListener) {
        this.changeListener = changeListener;
        this.board = new Board();
        board.initializeBoard();
    }

    public void handleSquareClick(Position location) {
        // TODO
        changeListener.onBoardChanged();;
        System.out.println("TEST: Square clicked at "
                + location.getRow() + ", " + location.getCol());
    }

    public Piece[][] getBoardSnapshot() {
        return this.board.getSnapshot();
    }
}