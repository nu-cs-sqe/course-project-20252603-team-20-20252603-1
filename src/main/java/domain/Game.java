package domain;

import domain.piece.Color;
import domain.piece.Piece;
import java.util.List;

public class Game {
    private final Board board;
    private Color currentTurn;
    private GameState gameState = GameState.NOT_STARTED;

    public Game() {
        this.board = new Board();
    }

    Game(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("No valid board passed");
        }
        this.board = board;
    }

    @Override
    protected final void finalize() throws Throwable {
    }

    private void switchTurn() {
        this.currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
        checkForGameEnd();
    }

    public void startGame() {
        if (this.gameState != GameState.NOT_STARTED) {
            throw new IllegalStateException("Game has already started, cannot restart");
        } else {
            board.initializeBoard();
            this.gameState = GameState.IN_PROGRESS;
            this.currentTurn = Color.WHITE;
        }
    }

    public Color getCurrentTurn() {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("Game has not started yet, no player has a turn");
        }

        return this.currentTurn;
    }

    public Piece getPieceAt(Position pos) {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("Game has not started yet, no pieces are on the board");
        }
        return board.getPieceAt(pos);
    }

    public void executeMove(Position from, Position to) {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("Cannot execute move if the game has not started.");
        }

        Piece piece = board.getPieceAt(from);
        Color pieceColor = piece.getColor();

        if (pieceColor != this.currentTurn) {
            throw new IllegalArgumentException(
                    "Cannot execute move if the current turn is not the piece's color.");
        }

        board.movePiece(from, to);
        switchTurn();
    }

    public List<Position> getValidMoves(Position position) {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException(
                    "Cannot get valid moves if the game has not started.");
        }

        Piece piece = board.getPieceAt(position);
        Color pieceColor = piece.getColor();

        if (pieceColor != this.currentTurn) {
            throw new IllegalArgumentException(
                    "Cannot get moves for piece that the current turn's color");
        }

        List<Position> validMoves = board.getValidMoves(position);

        return validMoves;
    }

    public Piece[][] getBoardSnapshot() {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException(
                    "Cannot get board snapshot if the game has not started.");
        }
        return board.getSnapshot();
    }

    public boolean playerInCheck(Color player) {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException(
                    "Cannot check if players are in check if the game has not started.");
        }

        return board.isInCheck(player);
    }

    public boolean isGameOver() {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("Cannot check if game is over if the game has not started.");
        }
        return this.gameState != GameState.IN_PROGRESS;
    }

    public GameState whyIsGameOver() {
        switch (this.gameState) {
            case NOT_STARTED:
                throw new IllegalStateException("Cannot answer why game is over if the game has not started.");
            case IN_PROGRESS:
                throw new IllegalStateException("Cannot answer why game is over if the game is in progress.");
            default:
                return this.gameState;
        }
    }

    private void checkForGameEnd() {
        if (checkForCheckmate()) {
            this.gameState = GameState.CHECKMATE;
        }
    }

    private boolean checkForCheckmate() {
        return board.isInCheck(currentTurn)
                && board.getValidMovesForPlayer(currentTurn).isEmpty();
    }
}
