package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import domain.Position;

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

    @Test
    public void Constructor_ColorNull_ThrowsIllegalArgumentException() {

        Exception actual = assertThrows(IllegalArgumentException.class, () -> {
            new Knight(null);
        });

        assertEquals("Color cannot be null", actual.getMessage());

    }

    @Test
    public void GetCandidates_ColorWhiteRow1Col4_ReturnsFourCandidates() {
        Knight knight = new Knight(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(1);
        EasyMock.expect(position.getCol()).andStubReturn(4);
        EasyMock.replay(position);

        List<Position> candidates = knight.getCandidateMoves(position);

        assertEquals(4, candidates.size());
        assertTrue(candidates.contains(new Position(2, 6)));
        assertTrue(candidates.contains(new Position(2, 2)));
        assertTrue(candidates.contains(new Position(3, 3)));
        assertTrue(candidates.contains(new Position(3, 5)));

    }

}
