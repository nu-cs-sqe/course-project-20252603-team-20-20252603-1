package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final int[][] MOVE_VECTORS = { { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    private List<Position> getCandidateMovesForDirection(int[] direction, Position position) {
        List<Position> candidates = new ArrayList<Position>();

        int dx = direction[0];
        int dy = direction[1];

        int row = position.getRow();
        int col = position.getCol();

        while (Position.validPosition(row + dx, col + dy)) {
            candidates.add(new Position(row + dx, col + dy));

            row = row + dx;
            col = col + dy;
        }

        return candidates;
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<Position>();

        for (int[] direction : MOVE_VECTORS) {
            List<Position> newMoves = getCandidateMovesForDirection(direction, position);
            candidates.addAll(newMoves);
        }

        return candidates;
    }

}
