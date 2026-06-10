package domain;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.util.List;
import java.util.Optional;

public class Game {
    private final Board board;
    private Color currentTurn;
    private GameState gameState = GameState.NOT_STARTED;
    private boolean promotionPending = false;
    private static final int WHITE_PROMOTION_ROW = 8;
    private static final int BLACK_PROMOTION_ROW = 1;
    private Optional<Position> promotionPosition = Optional.empty();

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

        validateTurn(from);

        if (this.promotionPending) {
            throw new IllegalStateException("Cannot execute move while promotion is pending.");
        }
        board.movePiece(from, to);
        handlePostMove(to);
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

    public boolean isPromotionPending() {
        return this.promotionPending;
    }

    private void validateTurn(Position from) {
        Piece piece = board.getPieceAt(from);
        if (piece.getColor() != this.currentTurn) {
            throw new IllegalArgumentException(
                    "Cannot execute move if the current turn is not the piece's color.");
        }
    }

    private void handlePostMove(Position to) {
        Piece movedPiece = board.getPieceAt(to);
        if (isPromotionMove(movedPiece, to)) {
            this.promotionPending = true;
            this.promotionPosition = Optional.of(to);
        } else {
            switchTurn();
        }
    }

    private boolean isPromotionMove(Piece piece, Position position) {
        if (piece.getPieceType() != PieceType.PAWN) {
            return false;
        }
        return (piece.getColor() == Color.WHITE && position.getRow() == WHITE_PROMOTION_ROW)
                || (piece.getColor() == Color.BLACK && position.getRow() == BLACK_PROMOTION_ROW);
    }

    public void executePromotion(PieceType pieceType) {
        if (!this.promotionPending) {
            throw new IllegalStateException("No promotion is pending.");
        }
        board.promotePawn(promotionPosition.get(), pieceType);
        this.promotionPending = false;
        this.promotionPosition = Optional.empty();
        switchTurn();
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
            throw new IllegalStateException(
                    "Cannot check if game is over if the game has not started.");
        }
        return this.gameState != GameState.IN_PROGRESS;
    }

    public GameState whyIsGameOver() {
        switch (this.gameState) {
            case NOT_STARTED:
                throw new IllegalStateException(
                        "Cannot answer why game is over if the game has not started.");
            case IN_PROGRESS:
                throw new IllegalStateException(
                        "Cannot answer why game is over if the game is in progress.");
            default:
                return this.gameState;
        }
    }

    public void handleTimeout(Color loserOnTime) {
        if (this.gameState == GameState.NOT_STARTED) {
            throw new IllegalStateException("No timeout, game has not started");
        } else if (isGameOver()) {
            throw new IllegalStateException("Game is already over");
        } else {
            Color winnerOnTime = (loserOnTime == Color.WHITE) ? Color.BLACK : Color.WHITE;
            if (board.hasInsufficientMaterial(winnerOnTime)) {
                this.gameState = GameState.TIMEOUT_VS_INSUFFICIENT_MATERIAL;
            } else {
                this.gameState = GameState.TIMEOUT;
            }
        }
    }

    private void checkForGameEnd() {
        if (checkForCheckmate()) {
            this.gameState = GameState.CHECKMATE;
        } else if (checkForStalemate()) {
            this.gameState = GameState.STALEMATE;
        } else if (checkForInsufficientMaterial()) {
            this.gameState = GameState.INSUFFICIENT_MATERIAL;
        }
    }

    private boolean checkForCheckmate() {
        return board.isInCheck(currentTurn)
                && board.getValidMovesForPlayer(currentTurn).isEmpty();
    }

    private boolean checkForStalemate() {
        return !board.isInCheck(currentTurn)
                && board.getValidMovesForPlayer(currentTurn).isEmpty();
    }

    private boolean checkForInsufficientMaterial() {
        return board.hasInsufficientMaterial(Color.BLACK)
                && board.hasInsufficientMaterial(Color.WHITE);
    }
}
