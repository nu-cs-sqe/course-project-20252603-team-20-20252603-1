package domain.piece;

import org.junit.jupiter.api.Test;

import domain.Position;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.easymock.EasyMock;

public class PieceTest {
    @Test
    public void constructor_kingAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_queenAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.QUEEN, Color.BLACK);
        assertNotNull(piece);
    }

    @Test
    public void constructor_rookAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_bishopAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.BISHOP, Color.BLACK);
        assertNotNull(piece);
    }

    @Test
    public void constructor_knightAndWhite_createsSuccessfully() {
        Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
        assertNotNull(piece);
    }

    @Test
    public void constructor_pawnAndBlack_createsSuccessfully() {
        Piece piece = new Piece(PieceType.PAWN, Color.BLACK);
        assertNotNull(piece);
    }

    @Test
    public void constructor_nullPieceType_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Piece(null, Color.BLACK));
    }

    @Test
    public void constructor_nullColor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Piece(PieceType.PAWN, null));
    }

    @Test
    public void getColor_pieceCreatedWithWhite_returnsWhite() {
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    public void getColor_pieceCreatedWithBlack_returnsBlack() {
        Piece piece = new Piece(PieceType.KING, Color.BLACK);
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    public void getPieceType_pieceCreatedWithKing_returnsKing() {
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        assertEquals(PieceType.KING, piece.getPieceType());
    }

    @Test
    public void getPieceType_pieceCreatedWithQueen_returnsQueen() {
        Piece piece = new Piece(PieceType.QUEEN, Color.WHITE);
        assertEquals(PieceType.QUEEN, piece.getPieceType());
    }

    @Test
    public void getPieceType_pieceCreatedWithRook_returnsRook() {
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        assertEquals(PieceType.ROOK, piece.getPieceType());
    }

    @Test
    public void getPieceType_pieceCreatedWithBishop_returnsBishop() {
        Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
        assertEquals(PieceType.BISHOP, piece.getPieceType());
    }

    @Test
    public void getPieceType_pieceCreatedWithKnight_returnsKnight() {
        Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
        assertEquals(PieceType.KNIGHT, piece.getPieceType());
    }

    @Test
    public void getPieceType_pieceCreatedWithPawn_returnsPawn() {
        Piece piece = new Piece(PieceType.PAWN, Color.WHITE);
        assertEquals(PieceType.PAWN, piece.getPieceType());
    }

    @Test
    public void SlidingDirections_AnyPiece_ReturnsEmptyArray() {
        Piece piece = new Piece(PieceType.KING, Color.BLACK);
        int[][] actual = piece.getSlidingDirections();
        assertEquals(0, actual.length);
    }

    @Test
    public void GetCandidateMoves_AnyPosition_ReturnsEmptyArray() {
        Piece piece = new Piece(PieceType.KING, Color.BLACK);
        Position position = EasyMock.createMock(Position.class);

        EasyMock.replay(position);

        List<Position> actual = piece.getCandidateMoves(position);

        assertTrue(actual.isEmpty());

        EasyMock.verify(position);

    }

    @Test
    public void GetCaptureMoves_AnyPosition_ReturnsEmptyList() {
        Piece piece = new Piece(PieceType.KING, Color.BLACK);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.replay(position);

        List<Position> actual = piece.getCaptureMoves(position);

        assertTrue(actual.isEmpty());
        EasyMock.verify(position);
    }

    @Test
    public void GetCaptureMoves_WhiteInterior_ReturnsBothDiagonals() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(4);
        EasyMock.expect(position.getCol()).andStubReturn(4);
        EasyMock.replay(position);

        List<Position> captures = pawn.getCaptureMoves(position);

        assertEquals(2, captures.size());
        assertTrue(captures.containsAll(List.of(new Position(5, 3), new Position(5, 5))));
    }

    @Test
    public void GetCaptureMoves_WhiteColLow_ReturnsOneDiagonal() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(4);
        EasyMock.expect(position.getCol()).andStubReturn(1);
        EasyMock.replay(position);

        List<Position> captures = pawn.getCaptureMoves(position);

        assertEquals(1, captures.size());
        assertTrue(captures.contains(new Position(5, 2)));
    }

    @Test
    public void GetCaptureMoves_WhiteColHigh_ReturnsOneDiagonal() {
        Pawn pawn = new Pawn(Color.WHITE);
        Position position = EasyMock.createMock(Position.class);
        EasyMock.expect(position.getRow()).andStubReturn(4);
        EasyMock.expect(position.getCol()).andStubReturn(8);
        EasyMock.replay(position);

        List<Position> captures = pawn.getCaptureMoves(position);

        assertEquals(1, captures.size());
        assertTrue(captures.contains(new Position(5, 7)));
    }
}
