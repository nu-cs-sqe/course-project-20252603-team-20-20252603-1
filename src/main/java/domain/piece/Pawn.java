package domain.piece;

public class Pawn extends Piece{
    private boolean hasMoved = false;

    public Pawn (Color color) {
        super(PieceType.PAWN, color);
    }

    public boolean hasMoved() {
        return hasMoved;
    }
}
