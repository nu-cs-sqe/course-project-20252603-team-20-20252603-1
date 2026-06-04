package domain.piece;

import java.util.ArrayList;
import java.util.List;

import domain.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<Position>(List.of(
                new Position(2, 5),
                new Position(3, 6),
                new Position(4, 7),
                new Position(5, 8),
                new Position(2, 3),
                new Position(3, 2),
                new Position(4, 1)));

        return candidates;
    }

}
