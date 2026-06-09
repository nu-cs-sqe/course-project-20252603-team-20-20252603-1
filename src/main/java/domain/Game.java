package domain;

import domain.piece.Color;
import domain.piece.Piece;
import java.util.List;

public class Game {
    private final Board board;
    private Color currentTurn;
    private boolean gameInProgress = false;

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
    }

    public void startGame() {
        if (this.gameInProgress) {
            throw new IllegalStateException("Game has already started, cannot restart");
        } else {
            board.initializeBoard();
            this.gameInProgress = true;
            this.currentTurn = Color.WHITE;
        }
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

    public void executeMove(Position from, Position to) {
        if (!this.gameInProgress) {
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
        if (!this.gameInProgress) {
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
        if (!this.gameInProgress) {
            throw new IllegalStateException(
                    "Cannot get board snapshot if the game has not started.");
        }
        return board.getSnapshot();
    }

    public boolean playerInCheck(Color player) {
        if (!this.gameInProgress) {
            throw new IllegalStateException(
                    "Cannot check if players are in check if the game has not started.");
        }

        return board.isInCheck(player);
    }
}
