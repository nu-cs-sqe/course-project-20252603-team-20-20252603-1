package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final int[][] DIRECTIONS = { { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<Position>();

        for (int[] direction : DIRECTIONS) {
            int row = position.getRow() + direction[0];
            int col = position.getCol() + direction[1];

            while (Position.validPosition(row, col)) {
                candidates.add(new Position(row, col));
                row += direction[0];
                col += direction[1];
            }
        }

        return candidates;
    }

    @Override
    public int[][] getSlidingDirections() {
        return DIRECTIONS;
    }
}
