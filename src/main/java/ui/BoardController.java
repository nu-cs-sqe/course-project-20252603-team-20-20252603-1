package ui;

import domain.Game;
import domain.Position;
import domain.piece.Color;
import domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BoardController {
    private final BoardChangeListener changeListener;
    private final Game game;
    private Optional<Position> selectedPosition = Optional.empty();
    private List<Position> currentValidMoves = Collections.emptyList();

    public BoardController(BoardChangeListener changeListener) {
        this.changeListener = changeListener;
        this.game = new Game();
        game.startGame();
    }

    public void handleSquareClick(Position selection) {
        if (selectedPosition.isEmpty()) {
            trySelect(selection);
        } else if (currentValidMoves.contains(selection)) {
            game.executeMove(selectedPosition.get(), selection);
            selectedPosition = Optional.empty();
            currentValidMoves = Collections.emptyList();
        } else {
            trySelect(selection);
        }
        changeListener.onBoardChanged();
    }

    private void trySelect(Position pos) {
        try {
            currentValidMoves = game.getValidMoves(pos);
            selectedPosition = Optional.of(pos);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            selectedPosition = Optional.empty();
            currentValidMoves = Collections.emptyList();
        }
    }

    public Piece[][] getBoardSnapshot() {
        return game.getBoardSnapshot();
    }

    public Optional<Position> getSelectedPosition() {
        return selectedPosition;
    }

    public List<Position> getValidMoves() {
        return List.copyOf(currentValidMoves);
    }

    public Color getCurrentTurn() {
        return game.getCurrentTurn();
    }

    public boolean playerInCheck(Color color) {
        return game.playerInCheck(color);
    }
}
