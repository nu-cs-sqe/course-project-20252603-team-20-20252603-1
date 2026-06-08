package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    public void Constructor_ColorWhite_KingCreated() {
        King king = new King(Color.WHITE);

        assertEquals(Color.WHITE, king.getColor());
        assertEquals(PieceType.KING, king.getPieceType());
    }
}
