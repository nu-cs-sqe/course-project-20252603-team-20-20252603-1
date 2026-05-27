package domain.piece;

import domain.Position;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Color.BLACK, pawn.getColor());
        assertFalse(pawn.hasMoved());
    }

    @Test
    public void Constructor_NullColor_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> { new Pawn(null);});
    }

    @Test
    public void GetCandidateMoves_WhiteNotMoved_ReturnsTwoCandidates() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(2);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(2, candidates.size());
        assertTrue(candidates.containsAll(List.of(new Position(3,1), new Position(4,1))));
    }

    @Test
    public void GetCandidateMoves_WhiteNotMovedMaxCol_ReturnsTwoCandidates() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(2);
        EasyMock.expect(position.getCol()).andStubReturn(8);
        EasyMock.replay(position);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(2, candidates.size());
        assertTrue(candidates.containsAll(List.of(new Position(3,8), new Position(4,8))));
    }

    @Test
    public void GetCandidateMoves_WhiteHasMoved_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(3);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(4,1)));
    }

    @Test
    public void GetCandidateMoves_WhiteHasMovedRowHighMinusOne_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(7);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(8,1)));
    }

    @Test
    public void GetCandidateMoves_WhiteHasMovedRowHigh_ReturnsNoCandidates() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(8);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(0, candidates.size());
    }

    @Test
    public void GetCandidateMoves_BlackNotMoved_ReturnsTwoCandidates() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(7);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(2, candidates.size());
        assertTrue(candidates.containsAll(List.of(new Position(6,1), new Position(5,1))));
    }

    @Test
    public void GetCandidateMoves_BlackHasMoved_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(6);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(5,1)));
    }

    @Test
    public void GetCandidateMoves_BlackHasMovedRowLowPlusOne_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(2);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(1,1)));
    }

    @Test
    public void GetCandidateMoves_BlackHasMovedRowLow_ReturnsNoCandidates() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(1);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(0, candidates.size());
    }

    @Test
    public void MarkMoved_CalledOnce_HasMovedReturnsTrue() {
        Pawn pawn = new Pawn(Color.WHITE);

        pawn.markMoved();

        assertTrue(pawn.hasMoved());
    }

    @Test
    public void MarkMoved_CalledTwice_HasMovedReturnsTrue() {
        Pawn pawn = new Pawn(Color.WHITE);

        pawn.markMoved();
        pawn.markMoved();

        assertTrue(pawn.hasMoved());
    }

    @Test
    public void HasMoved_NewPawn_ReturnsFalse() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertFalse(pawn.hasMoved());
    }

    @Test
    public void HasMoved_AfterMarkMoved_ReturnsTrue() {
        Pawn pawn = new Pawn(Color.WHITE);

        pawn.markMoved();

        assertTrue(pawn.hasMoved());
    }

    @Test
    public void GetCandidateMoves_WhiteNotMovedNearEnd_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(7);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(8, 1)));
    }

    @Test
    public void GetCandidateMoves_BlackNotMovedNearStart_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(2);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
        assertTrue(candidates.contains(new Position(1, 1)));
    }
}
