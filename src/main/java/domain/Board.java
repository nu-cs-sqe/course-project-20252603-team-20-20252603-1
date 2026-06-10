package domain;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Board {

    public static final int NUM_COLS = 8;
    public static final int NUM_ROWS = 8;
    private static final int WHITE_BACK_RANK = 0;
    private static final int BLACK_BACK_RANK = 7;
    private static final int WHITE_PAWN_RANK = 1;
    private static final int BLACK_PAWN_RANK = 6;

    private static final PieceType[] BACK_RANK = {
            PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
            PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK,
    };

    private Piece createPiece(PieceType type, Color color) {
        switch (type) {
            case PAWN:
                return new Pawn(color);
            case KNIGHT:
                return new Knight(color);
            case KING:
                return new King(color);
            case BISHOP:
                return new Bishop(color);
            case ROOK:
                return new Rook(color);
            case QUEEN:
                return new Queen(color);
            default:
                throw new IllegalStateException("Unhandled piece type: " + type);
        }
    }

    private final Piece[][] squares = new Piece[NUM_ROWS][NUM_COLS];

    private Optional<Piece> pieceAt(Position position) {
        return Optional.ofNullable(squares[position.getRow() - 1][position.getCol() - 1]);
    }

    private void setPieceAt(Position position, Piece piece) {
        squares[position.getRow() - 1][position.getCol() - 1] = piece;
    }

    void placePieceAt(Position position, Piece piece) {
        squares[position.getRow() - 1][position.getCol() - 1] = piece;
    }

    public void initializeBoard() {
        for (int col = 0; col < NUM_COLS; ++col) {
            squares[WHITE_BACK_RANK][col] = createPiece(BACK_RANK[col], Color.WHITE);
            squares[WHITE_PAWN_RANK][col] = createPiece(PieceType.PAWN, Color.WHITE);
            squares[BLACK_BACK_RANK][col] = createPiece(BACK_RANK[col], Color.BLACK);
            squares[BLACK_PAWN_RANK][col] = createPiece(PieceType.PAWN, Color.BLACK);
        }
    }

    public Piece getPieceAt(Position position) {
        return pieceAt(position).orElseThrow(
                () -> new NoSuchElementException("Cannot get piece at empty position"));
    }

    public Piece[][] getSnapshot() {
        Piece[][] copy = new Piece[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            copy[i] = Arrays.copyOf(squares[i], NUM_COLS);
        }
        return copy;
    }

    public boolean isEmpty(Position position) {
        return pieceAt(position).isEmpty();
    }

    public List<Position> getValidMoves(Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("Cannot get valid moves at an empty position");
        }

        List<Position> geometricMoves = getGeometricMoves(position);

        List<Position> legalMoves = filterMovesByCheckRule(position, geometricMoves);

        return legalMoves;
    }

    private List<Position> filterMovesByCheckRule(Position from, List<Position> candidates) {
        Color color = getPieceAt(from).getColor();
        List<Position> filtered = new ArrayList<Position>(candidates);
        filtered.removeIf((to) -> moveLeavesPlayerInCheck(from, to, color));
        return filtered;
    }

    private List<Position> getGeometricMoves(Position position) {
        Piece piece = getPieceAt(position);
        int[][] slidingDirections = piece.getSlidingDirections();
        if (slidingDirections.length > 0) {
            return getSlidingValidMoves(position, piece, slidingDirections);
        }
        return getNonSlidingValidMoves(position, piece);
    }

    private List<Position> getSlidingValidMoves(Position position, Piece piece,
            int[][] directions) {
        List<Position> validMoves = new ArrayList<>();
        for (int[] dir : directions) {
            validMoves.addAll(getValidMovesAlongRay(position, piece, dir));
        }
        return validMoves;
    }

    private List<Position> getValidMovesAlongRay(Position origin, Piece piece, int[] dir) {
        List<Position> ray = new ArrayList<>();
        int row = origin.getRow() + dir[0];
        int col = origin.getCol() + dir[1];
        while (Position.validPosition(row, col)) {
            Position candidate = new Position(row, col);
            if (isEmpty(candidate)) {
                ray.add(candidate);
            } else if (getPieceAt(candidate).getColor() != piece.getColor()) {
                ray.add(candidate);
                break;
            } else {
                break;
            }
            row += dir[0];
            col += dir[1];
        }
        return ray;
    }

    private List<Position> getNonSlidingValidMoves(Position position, Piece piece) {
        List<Position> validMoves = piece.getCandidateMoves(position);
        boolean isPawn = piece.getPieceType() == PieceType.PAWN;
        validMoves.removeIf(pos -> !isEmpty(pos)
                && (isPawn || getPieceAt(pos).getColor() == piece.getColor()));
        return validMoves;
    }

    public void movePiece(Position from, Position to) {
        if (isEmpty(from)) {
            throw new IllegalArgumentException("Cannot move piece from an empty position");
        }

        List<Position> validMoves = getValidMoves(from);

        if (!validMoves.contains(to)) {
            throw new IllegalArgumentException("Cannot move piece: destination is an illegal move");
        }

        Piece piece = getPieceAt(from);

        piece.markMoved();

        setPieceAt(to, piece);
        setPieceAt(from, null);
    }

    private List<Position> allPositions() {
        List<Position> positions = new ArrayList<>();
        for (int row = 1; row <= NUM_ROWS; ++row) {
            for (int col = 1; col <= NUM_COLS; ++col) {
                positions.add(new Position(row, col));
            }
        }
        return positions;
    }

    private Position locateKing(Color color) {
        for (Position position : allPositions()) {
            if (pieceAt(position).isEmpty()) {
                continue;
            }
            Piece piece = getPieceAt(position);
            if (piece.getColor() == color && piece.getPieceType() == PieceType.KING) {
                return position;
            }
        }
        throw new IllegalStateException(
                "cannot locateKing: king of specified color is not on the board.");
    }

    private boolean isPositionAttacked(Position position) {

        Color color = getPieceAt(position).getColor();

        for (Position square : allPositions()) {
            if (pieceAt(square).isEmpty()) {
                continue;
            }
            Piece piece = getPieceAt(square);
            if (piece.getColor() != color && getGeometricMoves(square).contains(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInCheck(Color player) {
        Position kingPosition = locateKing(player);
        return isPositionAttacked(kingPosition);
    }

    private void copyFromBoard(Piece[][] other) {
        for (int row = 0; row < NUM_ROWS; row++) {
            this.squares[row] = Arrays.copyOf(other[row], NUM_COLS);
        }
    }

    private boolean moveLeavesPlayerInCheck(Position from, Position to, Color color) {
        Board boardAfterMove = new Board();
        boardAfterMove.copyFromBoard(getSnapshot());

        Piece piece = boardAfterMove.getPieceAt(from);
        boardAfterMove.setPieceAt(from, null);
        boardAfterMove.setPieceAt(to, piece);

        return boardAfterMove.isInCheck(color);
    }

    public List<Position> getValidMovesForPlayer(Color player) {

        if (!(player instanceof Color)) {
            throw new IllegalArgumentException("Cannot get valid moves for null player");
        }

        List<Position> moves = new ArrayList<Position>();
        for (Position position : allPositions()) {
            if (pieceAt(position).isEmpty()) {
                continue;
            }
            Piece piece = getPieceAt(position);
            if (piece.getColor() == player) {
                moves.addAll(getValidMoves(position));
            }
        }
        return moves;
    }
}
