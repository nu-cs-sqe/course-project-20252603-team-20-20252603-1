package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<>();

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
        int[][] copy = new int[DIRECTIONS.length][];
        for (int i = 0; i < DIRECTIONS.length; i++) {
            copy[i] = DIRECTIONS[i].clone();
        }
        return copy;
    }
}