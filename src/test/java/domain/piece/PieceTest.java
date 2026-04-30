package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PieceTest {
    @Test
    public void constructor_kingAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_queenAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.QUEEN, Color.BLACK);
        assertNotNull(piece);
    }

    @Test
    public void constructor_rookAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_bishopAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.BISHOP, Color.BLACK);
        assertNotNull(piece);
    }
}
