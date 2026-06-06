package ui;

import domain.Board;
import domain.Game;
import domain.Position;
import domain.piece.Color;
import domain.piece.Piece;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class BoardController {
    private final BoardChangeListener changeListener;
    private final Game game;
    private final Board board;
    private Position selectedPosition = null;
    private List<Position> currentValidMoves = Collections.emptyList();

    public BoardController(BoardChangeListener changeListener) {
        this.changeListener = changeListener;
        this.board = new Board();
        this.game = new Game(board);
        game.startGame();
    }

    public void handleSquareClick(Position selection) {
        if (selectedPosition == null) {
            trySelect(selection);
        } else if (currentValidMoves.contains(selection)) {
            game.executeMove(selectedPosition, selection);
            selectedPosition = null;
            currentValidMoves = Collections.emptyList();
        } else {
            trySelect(selection);
        }
        changeListener.onBoardChanged();
    }

    private void trySelect(Position pos) {
        try {
            currentValidMoves = game.getValidMoves(pos);
            selectedPosition = pos;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            selectedPosition = null;
            currentValidMoves = Collections.emptyList();
        }
    }

    public Piece[][] getBoardSnapshot() {
        return this.board.getSnapshot();
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public List<Position> getValidMoves() {
        return currentValidMoves;
    }

    public Color getCurrentTurn() {
        return game.getCurrentTurn();
    }
}
