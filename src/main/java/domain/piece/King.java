package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private boolean hasMoved = false;

    private static final int[][] DIRECTIONS = {
            { 1, 1 },
            { -1, -1 },
            { -1, 1 },
            { 1, -1 },
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 },
    };

    public King(Color color) {
        super(PieceType.KING, color);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void markMoved() {
        hasMoved = true;
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<Position>();

        for (int[] direction : DIRECTIONS) {
            int row = position.getRow() + direction[0];
            int col = position.getCol() + direction[1];

            if (Position.validPosition(row, col)) {
                candidates.add(new Position(row, col));
            }
        }

        return candidates;
    }

}
