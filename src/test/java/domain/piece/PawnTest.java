package domain.piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @Test
    public void Constructor_ColorWhite_PawnCreated() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertEquals(PieceType.PAWN, pawn.getPieceType());
    }
}
