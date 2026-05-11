package domain.piece;

import domain.Position;
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
        Position position = new Position (2, 1);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(2, candidates.size());
    }

    @Test
    public void GetCandidateMoves_WhiteNotMovedMaxCol_ReturnsTwoCandidates() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = new Position(2, 8);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(2, candidates.size());
    }

    @Test
    public void GetCandidateMoves_WhiteHasMoved_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = new Position(3, 1);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
    }

    @Test
    public void GetCandidateMoves_WhiteHasMovedRowHighMinusOne_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = new Position(7, 1);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
    }

    @Test
    public void GetCandidateMoves_WhiteHasMovedRowHigh_ReturnsNoCandidates() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = new Position(8, 1);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(0, candidates.size());
    }

    @Test
    public void GetCandidateMoves_BlackNotMoved_ReturnsTwoCandidates() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = new Position(7, 1);

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(PieceType.PAWN, pawn.getPieceType());
        assertEquals(Color.BLACK, pawn.getColor());
        assertFalse(pawn.hasMoved());
        assertEquals(2, candidates.size());
    }

    @Test
    public void GetCandidateMoves_BlackHasMoved_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = new Position(6, 1);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
    }

    @Test
    public void GetCandidateMoves_BlackHasMovedRowLowPlusOne_ReturnsOneCandidate() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = new Position(2, 1);

        pawn.markMoved();

        List<Position> candidates = pawn.getCandidateMoves(position);

        assertEquals(1, candidates.size());
    }

    @Test
    public void GetCandidateMoves_BlackHasMovedRowLow_ReturnsNoCandidates() {
        Pawn pawn = new Pawn(Color.BLACK);
        Position position = new Position(1, 1);

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
}
