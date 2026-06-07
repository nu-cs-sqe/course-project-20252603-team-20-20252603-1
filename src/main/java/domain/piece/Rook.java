package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public Rook(Color color) {
        super(PieceType.ROOK, color);
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
}