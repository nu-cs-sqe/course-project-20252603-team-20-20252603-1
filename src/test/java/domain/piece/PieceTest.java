package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PieceTest {
    @Test
    public void constructor_kingAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        assertNotNull(piece);
    }
}
