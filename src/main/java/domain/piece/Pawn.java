package domain.piece;

import domain.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private boolean hasMoved = false;

    public Pawn (Color color) {
        super(PieceType.PAWN, color);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<>();
        // white moves to higher rows, black to lower rows
        int direction = (getColor() == Color.WHITE) ? 1 : -1;
        int row = position.getRow();
        int col = position.getCol();

        int oneForward = row + direction;
        if (oneForward >= 1 && oneForward <= 8) {
            candidates.add(new Position(oneForward, col));
        }

        if (!hasMoved) {
            int twoForward = row + (2 * direction);
            if (twoForward >= 1 && twoForward <= 8) {
                candidates.add(new Position(twoForward, col));
            }
        }

        return candidates;
    }
}
