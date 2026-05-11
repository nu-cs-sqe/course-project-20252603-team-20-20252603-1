package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTest {
    @Test
    public void Constructor_ColorWhite_PawnCreated() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertEquals(PieceType.PAWN, pawn.getPieceType());
        assertEquals(Color.WHITE, pawn.getColor());
        assertFalse(pawn.hasMoved());
    }

    @Test
    public void Constructor_ColorBlack_PawnCreated() {
        Pawn pawn = new Pawn(Color.BLACK);

        assertEquals(PieceType.PAWN, pawn.getPieceType());
        assertEquals(Color.WHITE, pawn.getColor());
        assertFalse(pawn.hasMoved());
    }
}
