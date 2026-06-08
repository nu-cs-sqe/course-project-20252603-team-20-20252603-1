package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Piece {
    private final PieceType pieceType;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        if (pieceType == null) {
            throw new IllegalArgumentException("Piece type cannot be null");
        }
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }

        this.pieceType = pieceType;
        this.color = color;
    }

    @Override
    protected final void finalize() throws Throwable {
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public List<Position> getCandidateMoves(Position position) {
        return new ArrayList<Position>();
    }

    public int[][] getSlidingDirections() {
        return new int[0][0];
    }

    public void markMoved() {
    }

}
