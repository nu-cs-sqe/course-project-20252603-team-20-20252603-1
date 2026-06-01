package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    @Test
    public void Constructor_ColorWhite_QueenCreated() {
        Queen queen = new Queen(Color.WHITE);

        assertEquals(Color.WHITE, queen.getColor());
        assertEquals(PieceType.QUEEN, queen.getPieceType());
    }
}
