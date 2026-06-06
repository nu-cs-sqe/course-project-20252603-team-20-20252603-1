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

public class RookTest {

        @Test
        public void Constructor_ColorWhite_RookCreated() {
                Rook rook = new Rook(Color.WHITE);

                assertEquals(Color.WHITE, rook.getColor());
                assertEquals(PieceType.ROOK, rook.getPieceType());
        }

        @Test
        public void Constructor_ColorBlack_RookCreated() {
                Rook rook = new Rook(Color.BLACK);

                assertEquals(Color.BLACK, rook.getColor());
                assertEquals(PieceType.ROOK, rook.getPieceType());
        }

        @Test
        public void Constructor_ColorNull_ThrowsIllegalArgumentException() {

                Exception actual = assertThrows(IllegalArgumentException.class, () -> {
                        new Rook(null);
                });

                assertEquals("Color cannot be null", actual.getMessage());

        }

        @ParameterizedTest
        @MethodSource("getCandidatesProvider")
        public void GetCandidates_ColorCRowXColY_ReturnsNCandidates(Color color, int row, int col, List<Position> expected) {

                Rook rook = new Rook(color);
                Position position = EasyMock.createMock(Position.class);

                EasyMock.expect(position.getRow()).andStubReturn(row);
                EasyMock.expect(position.getCol()).andStubReturn(col);
                EasyMock.replay(position);

                List<Position> actual = rook.getCandidateMoves(position);

                assertEquals(expected.size(), actual.size());
                assertTrue(actual.containsAll(expected));
        }

        static Stream<Arguments> getCandidatesProvider() {
                return Stream.of(
                        // TC 4
                        Arguments.of(Color.WHITE, 1, 4, List.of(
                                new Position(2, 4),
                                new Position(3, 4),
                                new Position(4, 4),
                                new Position(5, 4),
                                new Position(6, 4),
                                new Position(7, 4),
                                new Position(8, 4),
                                new Position(1, 1),
                                new Position(1, 2),
                                new Position(1, 3),
                                new Position(1, 5),
                                new Position(1, 6),
                                new Position(1, 7),
                                new Position(1, 8))),
                        // TC 5
                        Arguments.of(Color.WHITE, 8, 4, List.of(
                                new Position(1, 4),
                                new Position(2, 4),
                                new Position(3, 4),
                                new Position(4, 4),
                                new Position(5, 4),
                                new Position(6, 4),
                                new Position(7, 4),
                                new Position(8, 1),
                                new Position(8, 2),
                                new Position(8, 3),
                                new Position(8, 5),
                                new Position(8, 6),
                                new Position(8, 7),
                                new Position(8, 8))),
                        // TC 6
                        Arguments.of(Color.WHITE, 4, 1, List.of(
                                new Position(5, 1),
                                new Position(6, 1),
                                new Position(7, 1),
                                new Position(8, 1),
                                new Position(1, 1),
                                new Position(2, 1),
                                new Position(3, 1),
                                new Position(1, 1),
                                new Position(4, 2),
                                new Position(4, 3),
                                new Position(4, 5),
                                new Position(4, 6),
                                new Position(4, 7),
                                new Position(4, 8))),
                        // TC 7
                        Arguments.of(Color.BLACK, 4, 8, List.of(
                                new Position(5, 8),
                                new Position(6, 8),
                                new Position(7, 8),
                                new Position(8, 8),
                                new Position(1, 8),
                                new Position(2, 8),
                                new Position(3, 8),
                                new Position(4, 1),
                                new Position(4, 2),
                                new Position(4, 3),
                                new Position(4, 4),
                                new Position(4, 5),
                                new Position(4, 6),
                                new Position(4, 7))),
                        // TC 8
                        Arguments.of(Color.WHITE, 1, 1, List.of(
                                new Position(2, 1),
                                new Position(3, 1),
                                new Position(4, 1),
                                new Position(5, 1),
                                new Position(6, 1),
                                new Position(7, 1),
                                new Position(8, 1),
                                new Position(1, 2),
                                new Position(1, 3),
                                new Position(1, 4),
                                new Position(1, 5),
                                new Position(1, 6),
                                new Position(1, 7),
                                new Position(1, 8))),
                        // TC 9
                        Arguments.of(Color.BLACK, 8, 8, List.of(
                                new Position(1, 8),
                                new Position(2, 8),
                                new Position(3, 8),
                                new Position(4, 8),
                                new Position(5, 8),
                                new Position(6, 8),
                                new Position(7, 8),
                                new Position(8, 1),
                                new Position(8, 2),
                                new Position(8, 3),
                                new Position(8, 4),
                                new Position(8, 5),
                                new Position(8, 6),
                                new Position(8, 7)))
                        );
        }
}