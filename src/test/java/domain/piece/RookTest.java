package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RookTest {

        @Test
        public void Constructor_ColorWhite_RookCreated() {
                Rook rook = new Rook(Color.WHITE);

                assertEquals(Color.WHITE, rook.getColor());
                assertEquals(PieceType.ROOK, rook.getPieceType());
        }
}