package domain.piece;

import domain.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void markMoved() {
        hasMoved = true;
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<>();
        // white moves to higher rows, black to lower rows
        int direction = (getColor() == Color.WHITE) ? 1 : -1;
        int row = position.getRow();
        int col = position.getCol();

        int oneForward = row + direction;
        if (Position.validPosition(oneForward, col)) {
            candidates.add(new Position(oneForward, col));
        }

        if (!hasMoved) {
            int twoForward = row + (2 * direction);
            if (Position.validPosition(twoForward, col)) {
                candidates.add(new Position(twoForward, col));
            }
        }

        return candidates;
    }

    @Override
    public List<Position> getCaptureMoves(Position position) {
        List<Position> captures = new ArrayList<>();
        int direction = (getColor() == Color.WHITE) ? 1 : -1;
        int row = position.getRow() + direction;
        int col = position.getCol();
        if (Position.validPosition(row, col - 1)) {
            captures.add(new Position(row, col - 1));
        }
        if (Position.validPosition(row, col + 1)) {
            captures.add(new Position(row, col + 1));
        }
        return captures;
    }
}
