package ui;

import domain.ChessClock;
import domain.ClockListener;
import domain.Game;
import domain.GameState;
import domain.Position;
import domain.piece.Color;
import domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BoardController {
    private final BoardChangeListener changeListener;
    private final ClockListener clockListener;
    private final Game game;
    private ChessClock clock;
    private Optional<Position> selectedPosition = Optional.empty();
    private List<Position> currentValidMoves = Collections.emptyList();

    public BoardController(BoardChangeListener changeListener, ClockListener clockListener) {
        this.changeListener = changeListener;
        this.clockListener = clockListener;
        this.game = new Game();
    }

    public void startGame(long initialTime) {
        this.clock = new ChessClock(initialTime, this.clockListener);
        game.startGame();
        clock.start();
    }

    public void handleSquareClick(Position selection) {
        if (game.isGameOver()) {
            return;
        }
        if (selectedPosition.isEmpty()) {
            trySelect(selection);
        } else if (currentValidMoves.contains(selection)) {
            game.executeMove(selectedPosition.get(), selection);
            clock.switchClock();
            selectedPosition = Optional.empty();
            currentValidMoves = Collections.emptyList();
            if (game.isGameOver()) {
                clock.stop();
            }
        } else {
            trySelect(selection);
        }
        changeListener.onBoardChanged();
    }

    public void handleTimeout(Color loser) {
        clock.stop();
        // TODO: handle winning condition
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

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public boolean isCheckmate() {
        return game.isGameOver() && game.whyIsGameOver() == GameState.CHECKMATE;
    }
}
