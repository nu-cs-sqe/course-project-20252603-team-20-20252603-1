package ui;

import domain.ChessClock;
import domain.ClockListener;
import domain.Game;
import domain.Position;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.awt.Frame;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BoardController {
    private final BoardChangeListener changeListener;
    private final ClockListener clockListener;
    private final Game game;
    private final Locale locale;
    private ChessClock clock;
    private Optional<Position> selectedPosition = Optional.empty();
    private List<Position> currentValidMoves = Collections.emptyList();

    public BoardController(BoardChangeListener changeListener, ClockListener clockListener,
            Locale locale) {
        this.changeListener = changeListener;
        this.clockListener = clockListener;
        this.locale = locale;
        this.game = new Game();
    }

    public void startGame(long initialTime) {
        this.clock = new ChessClock(initialTime, this.clockListener);
        game.startGame();
        clock.start();
    }

    public void handleSquareClick(Position selection) {
        if (selectedPosition.isEmpty()) {
            trySelect(selection);
        } else if (currentValidMoves.contains(selection)) {
            game.executeMove(selectedPosition.get(), selection);
            if (game.isPromotionPending()) {
                Frame frame = (changeListener instanceof Frame) ? (Frame) changeListener : null;
                PieceType chosen = new PromotionDialog(
                        frame, game.getCurrentTurn(), locale).showAndGetResult();
                game.executePromotion(chosen);
            }
            clock.switchClock();
            selectedPosition = Optional.empty();
            currentValidMoves = Collections.emptyList();
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
}
