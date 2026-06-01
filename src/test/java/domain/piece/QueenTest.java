package domain.piece;

import domain.Position;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {
    @Test
    public void Constructor_ColorWhite_QueenCreated() {
        Queen queen = new Queen(Color.WHITE);

        assertEquals(Color.WHITE, queen.getColor());
        assertEquals(PieceType.QUEEN, queen.getPieceType());
    }

    @Test
    public void Constructor_ColorBlack_QueenCreated() {
        Queen queen = new Queen(Color.BLACK);

        assertEquals(Color.BLACK, queen.getColor());
        assertEquals(PieceType.QUEEN, queen.getPieceType());
    }

    @Test
    public void Constructor_ColorNull_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Queen(null);
        });
    }

    @Test
    public void GetCandidateMoves_White_Row1_Col1_Returns21Candidates() {
        Queen queen = new Queen(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(1);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> actual = queen.getCandidateMoves(position);

        List<Position> expected = List.of(
                new Position(2,1), new Position(3,1), new Position(4,1), new Position(5,1),
                new Position(6,1), new Position(7,1), new Position(8,1), new Position(1,2),
                new Position(1,3), new Position(1,4), new Position(1,5), new Position(1,6),
                new Position(1,7), new Position(1,8), new Position(2,2), new Position(3,3),
                new Position(4,4), new Position(5,5), new Position(6,6), new Position(7,7),
                new Position(8,8)
        );

        assertEquals(21, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void GetCandidateMoves_Black_Row8_Col8_Returns21Candidates() {
        Queen queen = new Queen(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(8);
        EasyMock.expect(position.getCol()).andStubReturn(8);
        EasyMock.replay(position);

        List<Position> actual = queen.getCandidateMoves(position);

        List<Position> expected = List.of(
                new Position(7,8), new Position(6,8), new Position(5,8), new Position(4,8),
                new Position(3,8), new Position(2,8), new Position(1,8), new Position(8,7),
                new Position(8,6), new Position(8,5), new Position(8,4), new Position(8,3),
                new Position(8,2), new Position(8,1), new Position(7,7), new Position(6,6),
                new Position(5,5), new Position(4,4), new Position(3,3), new Position(2,2),
                new Position(1,1)
        );

        assertEquals(21, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void GetCandidateMoves_White_Row1_Col8_Returns21Candidates() {
        Queen queen = new Queen(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(1);
        EasyMock.expect(position.getCol()).andStubReturn(8);
        EasyMock.replay(position);

        List<Position> actual = queen.getCandidateMoves(position);

        List<Position> expected = List.of(
                new Position(2,8), new Position(3,8), new Position(4,8), new Position(5,8),
                new Position(6,8), new Position(7,8), new Position(8,8), new Position(1,7),
                new Position(1,6), new Position(1,5), new Position(1,4), new Position(1,3),
                new Position(1,2), new Position(1,1), new Position(2,7), new Position(3,6),
                new Position(4,5), new Position(5,4), new Position(6,3), new Position(7,2),
                new Position(8,1)
        );

        assertEquals(21, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void GetCandidateMoves_Black_Row8_Col1_Returns21Candidates() {
        Queen queen = new Queen(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(8);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> actual = queen.getCandidateMoves(position);

        List<Position> expected = List.of(
                new Position(7,1), new Position(6,1), new Position(5,1), new Position(4,1),
                new Position(3,1), new Position(2,1), new Position(1,1), new Position(8,2),
                new Position(8,3), new Position(8,4), new Position(8,5), new Position(8,6),
                new Position(8,7), new Position(8,8), new Position(7,2), new Position(6,3),
                new Position(5,4), new Position(4,5), new Position(3,6), new Position(2,7),
                new Position(1,8)
        );

        assertEquals(21, actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void GetCandidateMoves_White_Row1_Col4_Returns21Candidates() {
        Queen queen = new Queen(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(1);
        EasyMock.expect(position.getCol()).andStubReturn(4);
        EasyMock.replay(position);

        List<Position> actual = queen.getCandidateMoves(position);

        List<Position> expected = List.of(
                new Position(2,4), new Position(3,4), new Position(4,4), new Position(5,4),
                new Position(6,4), new Position(7,4), new Position(8,4), new Position(1,5),
                new Position(1,6), new Position(1,7), new Position(1,8), new Position(1,3),
                new Position(1,2), new Position(1,1), new Position(2,5), new Position(3,6),
                new Position(4,7), new Position(5,8), new Position(2,3), new Position(3,2),
                new Position(4,1)
        );

        assertEquals(21, actual.size());
        assertTrue(actual.containsAll(expected));
    }
}
