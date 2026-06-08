package domain.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.Position;

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

    @ParameterizedTest
    @MethodSource("getCandidatesProvider")
    public void GetCandidates_ColorCRowXColY_ReturnsNCandidates(Color color, int row, int col,
            List<Position> expected) {

        King king = new King(color);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(row);
        EasyMock.expect(position.getCol()).andStubReturn(col);
        EasyMock.replay(position);

        List<Position> actual = king.getCandidateMoves(position);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    static Stream<Arguments> getCandidatesProvider() {
        return Stream.of(
                // TC 4
                Arguments.of(Color.WHITE, 1, 4, List.of(
                        new Position(2, 4),
                        new Position(1, 3),
                        new Position(1, 5),
                        new Position(2, 5),
                        new Position(2, 3))));

    }
}
