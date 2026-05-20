package domain.piece;

import java.util.ArrayList;
import java.util.List;

import domain.Position;

public class Knight extends Piece {

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    public List<Position> getCandidateMoves(Position position) {
        List<Position> candidates = new ArrayList<>(List.of(
                new Position(2, 6),
                new Position(2, 2),
                new Position(3, 3),
                new Position(3, 5)));

        return candidates;
    }

}
