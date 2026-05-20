package domain.piece;

import java.util.ArrayList;
import java.util.List;

import domain.Position;

public class Knight extends Piece {

    private static final int[][] DIRECTIONS = {
            { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 },
            { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }
    };

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    private boolean inBounds(int row, int col) {
        return 1 <= row && row <= 8 && 1 <= col && col <= 8;
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<Position>();

        for (int[] direction : DIRECTIONS) {
            int row = position.getRow() + direction[0];
            int col = position.getCol() + direction[1];

            if (1 <= row && row <= 8 && 1 <= col && col <= 8) {
                candidates.add(new Position(row, col));
            }
        }

        return candidates;
    }

}
