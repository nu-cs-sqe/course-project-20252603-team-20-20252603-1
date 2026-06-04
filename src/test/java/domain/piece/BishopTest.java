package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BishopTest {
    @Test
    public void Constructor_ColorWhite_BishopCreated() {
        Bishop bishop = new Bishop(Color.WHITE);

        assertEquals(Color.WHITE, bishop.getColor());
        assertEquals(PieceType.BISHOP, bishop.getPieceType());
    }
}
