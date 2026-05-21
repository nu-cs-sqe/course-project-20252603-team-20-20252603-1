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
