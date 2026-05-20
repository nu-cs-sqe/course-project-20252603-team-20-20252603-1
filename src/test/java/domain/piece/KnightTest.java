package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    public void Constructor_ColorWhite_KnightCreated() {
        Knight knight = new Knight(Color.WHITE);

        assertEquals(Color.WHITE, knight.getColor());
        assertEquals(PieceType.KNIGHT, knight.getPieceType());
    }

    @Test
    public void Constructor_ColorBlack_KnightCreated() {
        Knight knight = new Knight(Color.BLACK);

        assertEquals(Color.BLACK, knight.getColor());
        assertEquals(PieceType.KNIGHT, knight.getPieceType());
    }

}
