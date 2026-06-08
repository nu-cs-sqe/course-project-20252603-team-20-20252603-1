package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    public void Constructor_ColorWhite_KingCreated() {
        King king = new King(Color.WHITE);

        assertEquals(Color.WHITE, king.getColor());
        assertEquals(PieceType.KING, king.getPieceType());
    }

    @Test
    public void Constructor_ColorBlack_KingCreated() {
        King king = new King(Color.BLACK);

        assertEquals(Color.BLACK, king.getColor());
        assertEquals(PieceType.KING, king.getPieceType());
    }

    @Test
    public void Constructor_ColorNull_ThrowsIllegalArgumentException() {

        Exception actual = assertThrows(IllegalArgumentException.class, () -> {
            new King(null);
        });

        assertEquals("Color cannot be null", actual.getMessage());

    }
}
