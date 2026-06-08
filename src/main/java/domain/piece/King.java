package domain.piece;

import java.util.List;

import domain.Position;

public class King extends Piece {

    public King(Color color) {
        super(PieceType.KING, color);
    }

    public List<Position> getCandidateMoves(Position position) {
        return List.of(
                new Position(2, 4),
                new Position(1, 3),
                new Position(1, 5),
                new Position(2, 5),
                new Position(2, 3));
    }

}
