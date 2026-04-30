package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void constructor_knightAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_pawnAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.PAWN, Color.BLACK);
        assertNotNull(piece);
    }

    @Test
    public void constructor_nullPieceType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Piece(null, Color.BLACK));
    }
}
