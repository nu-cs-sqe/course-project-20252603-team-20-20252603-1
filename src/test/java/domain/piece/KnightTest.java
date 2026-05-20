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

    @ParameterizedTest
    @MethodSource("getCandidatesProvider")
    public void GetCandidates_ColorCRowXColY_ReturnsNCandidates(Color color, int row, int col,
            List<Position> expected) {

        Knight knight = new Knight(color);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(row);
        EasyMock.expect(position.getCol()).andStubReturn(col);
        EasyMock.replay(position);

        List<Position> actual = knight.getCandidateMoves(position);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    static Stream<Arguments> getCandidatesProvider() {
        return Stream.of(
                // TC 4
                Arguments.of(Color.WHITE, 1, 4, List.of(
                        new Position(2, 6),
                        new Position(2, 2),
                        new Position(3, 3),
                        new Position(3, 5))),
                // TC 5
                Arguments.of(Color.BLACK, 8, 4, List.of(
                        new Position(7, 6),
                        new Position(7, 2),
                        new Position(6, 3),
                        new Position(6, 5))),
                // TC 6
                Arguments.of(Color.WHITE, 4, 1, List.of(
                        new Position(5, 3),
                        new Position(6, 2),
                        new Position(3, 3),
                        new Position(2, 2))),
                // TC 7
                Arguments.of(Color.BLACK, 4, 8, List.of(
                        new Position(5, 6),
                        new Position(6, 7),
                        new Position(3, 6),
                        new Position(2, 7))),
                // TC 8
                Arguments.of(Color.WHITE, 1, 1, List.of(
                        new Position(2, 3),
                        new Position(3, 2))),
                // TC 9
                Arguments.of(Color.BLACK, 8, 8, List.of(
                        new Position(7, 6),
                        new Position(6, 7 /* lol */))),
                // TC 10
                Arguments.of(Color.BLACK, 3, 3, List.of(
                        new Position(4, 5),
                        new Position(4, 1),
                        new Position(5, 2),
                        new Position(5, 4),
                        new Position(2, 5),
                        new Position(2, 1),
                        new Position(1, 2),
                        new Position(1, 4))),
                // TC 11
                Arguments.of(Color.WHITE, 2, 3, List.of(
                        new Position(3, 5),
                        new Position(3, 1),
                        new Position(4, 2),
                        new Position(4, 4),
                        new Position(1, 5),
                        new Position(1, 1))));
    }

}
