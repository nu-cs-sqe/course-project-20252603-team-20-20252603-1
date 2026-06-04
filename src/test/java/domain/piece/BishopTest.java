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

public class BishopTest {
    @Test
    public void Constructor_ColorWhite_BishopCreated() {
        Bishop bishop = new Bishop(Color.WHITE);

        assertEquals(Color.WHITE, bishop.getColor());
        assertEquals(PieceType.BISHOP, bishop.getPieceType());
    }

    @Test
    public void Constructor_ColorBlack_BishopCreated() {
        Bishop bishop = new Bishop(Color.BLACK);

        assertEquals(Color.BLACK, bishop.getColor());
        assertEquals(PieceType.BISHOP, bishop.getPieceType());
    }

    @Test
    public void Constructor_ColorNull_ThrowsIllegalArgumentException() {

        Exception actual = assertThrows(IllegalArgumentException.class, () -> {
            new Bishop(null);
        });

        assertEquals("Color cannot be null", actual.getMessage());

    }

    @ParameterizedTest
    @MethodSource("getCandidatesProvider")
    public void GetCandidates_ColorCRowXColY_ReturnsNCandidates(Color color, int row, int col,
            List<Position> expected) {

        Bishop bishop = new Bishop(color);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(row);
        EasyMock.expect(position.getCol()).andStubReturn(col);
        EasyMock.replay(position);

        List<Position> actual = bishop.getCandidateMoves(position);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    static Stream<Arguments> getCandidatesProvider() {
        return Stream.of(
                // TC 4
                Arguments.of(Color.WHITE, 1, 4, List.of(
                        new Position(2, 5),
                        new Position(3, 6),
                        new Position(4, 7),
                        new Position(5, 8),
                        new Position(2, 3),
                        new Position(3, 2),
                        new Position(4, 1))),
                // TC 5
                Arguments.of(Color.BLACK, 8, 4, List.of(
                        new Position(7, 5),
                        new Position(6, 6),
                        new Position(5, 7),
                        new Position(4, 8),
                        new Position(7, 3),
                        new Position(6, 2),
                        new Position(5, 1))),
                // TC 5
                Arguments.of(Color.WHITE, 4, 1, List.of(
                        new Position(5, 2),
                        new Position(6, 3),
                        new Position(7, 4),
                        new Position(8, 5),
                        new Position(3, 2),
                        new Position(2, 3),
                        new Position(1, 4))));
    }
}
