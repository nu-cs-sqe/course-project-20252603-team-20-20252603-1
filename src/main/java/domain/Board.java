package domain;

import domain.piece.Bishop;
import domain.piece.Color;
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

    private static final int NUM_COLS = 8;
    private static final int NUM_ROWS = 8;
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
            case PAWN:   return new Pawn(color);
            case KNIGHT: return new Knight(color);
            case BISHOP: return new Bishop(color);
            case ROOK:   return new Rook(color);
            case QUEEN:  return new Queen(color);
            default:     return new Piece(type, color);
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
            // white pieces
            squares[WHITE_BACK_RANK][col] = createPiece(BACK_RANK[col], Color.WHITE);
            squares[WHITE_PAWN_RANK][col] = createPiece(PieceType.PAWN, Color.WHITE);
            // black pieces
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

        Piece piece = getPieceAt(position);
        int[][] slidingDirections = piece.getSlidingDirections();

        if (slidingDirections.length > 0) {
            List<Position> validMoves = new ArrayList<>();
            for (int[] dir : slidingDirections) {
                int row = position.getRow() + dir[0];
                int col = position.getCol() + dir[1];
                while (Position.validPosition(row, col)) {
                    Position candidate = new Position(row, col);
                    if (isEmpty(candidate)) {
                        validMoves.add(candidate);
                    } else if (getPieceAt(candidate).getColor() != piece.getColor()) {
                        validMoves.add(candidate);
                        break;
                    } else {
                        break;
                    }
                    row += dir[0];
                    col += dir[1];
                }
            }
            return validMoves;
        }

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
}
