package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Bishop;
import domain.piece.Piece;
import domain.piece.PieceType;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BoardTest {

  private Piece mockPiece(Color color, PieceType type, List<Position> candidateMoves) {
    Piece piece = EasyMock.createMock(Piece.class);
    EasyMock.expect(piece.getColor()).andStubReturn(color);
    EasyMock.expect(piece.getPieceType()).andStubReturn(type);
    EasyMock.expect(piece.getSlidingDirections()).andStubReturn(new int[0][]);

    EasyMock.expect(piece.getCandidateMoves(EasyMock.anyObject()))
        .andReturn(new ArrayList<>(candidateMoves));

    return piece;
  }

  private Piece stubPiece(Color color, PieceType type, List<Position> candidateMoves) {
    Piece piece = EasyMock.createMock(Piece.class);
    EasyMock.expect(piece.getColor()).andStubReturn(color);
    EasyMock.expect(piece.getPieceType()).andStubReturn(type);
    EasyMock.expect(piece.getSlidingDirections()).andStubReturn(new int[0][]);

    EasyMock.expect(piece.getCandidateMoves(EasyMock.anyObject()))
        .andStubReturn(new ArrayList<>(candidateMoves));

    return piece;
  }

  /*
   * isEmpty() unit tests
   */

  @ParameterizedTest
  @CsvSource({
      "1, 1",
      "2, 1",
      "7, 1",
      "8, 1",
      "1, 2",
      "1, 7",
      "1, 8",
  })
  public void IsEmpty_NewBoardRowColBounds_IsTrue(int row, int col) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(row);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    boolean actual = board.isEmpty(position);

    assertTrue(actual);
  }

  @Test
  public void IsEmpty_AfterInitRow1Col1_IsFalse() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(1);

    EasyMock.replay(position);

    board.initializeBoard();

    boolean actual = board.isEmpty(position);

    assertFalse(actual);
  }

  /*
   * getPieceAt() SOCIABLE unit tests
   */

  @ParameterizedTest
  @CsvSource({
      "1, 1, WHITE, ROOK",
      "1, 8, WHITE, ROOK",
      "8, 1, BLACK, ROOK",
      "2, 1, WHITE, PAWN",
      "7, 1, BLACK, PAWN",
      "1, 2, WHITE, KNIGHT",
      "1, 7, WHITE, KNIGHT",
      "1, 3, WHITE, BISHOP",
      "1, 4, WHITE, QUEEN",
      "1, 5, WHITE, KING",
  })
  public void GetPieceAt_AfterInitRowXColY_CorrectPiece(int row,
      int col,
      Color color,
      PieceType pieceType) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(row);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertEquals(color, actual.getColor());
    assertEquals(pieceType, actual.getPieceType());
    assertFalse(board.isEmpty(position));
  }

  @Test
  public void GetPieceAt_NewBoardRow1Col1_ThrowsException() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(1);

    EasyMock.replay(position);

    Exception exception = assertThrows(
        NoSuchElementException.class,
        () -> board.getPieceAt(position));

    assertTrue(board.isEmpty(position));

    String expected = "Cannot get piece at empty position";
    String actual = exception.getMessage();
    assertEquals(expected, actual);

  }

  /*
   * initializeBoard() unit tests
   */

  @ParameterizedTest
  @CsvSource({
      "3, 1",
      "3, 8",
      "6, 1",
      "6, 8",
      "4, 4",
  })
  public void InitializeBoard_EmptySquare_EmptyIsTrue(int row, int col) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(row);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    boolean actual = board.isEmpty(position);

    assertTrue(actual);
  }

  @ParameterizedTest
  @CsvSource({
      "1, ROOK",
      "2, KNIGHT",
      "3, BISHOP",
      "4, QUEEN",
      "5, KING",
      "6, BISHOP",
      "7, KNIGHT",
      "8, ROOK",
  })
  public void InitializeBoard_Row1ColY_WhiteBackRankCorrect(int col,
      PieceType pieceType) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertEquals(Color.WHITE, actual.getColor());
    assertEquals(pieceType, actual.getPieceType());
    assertFalse(board.isEmpty(position));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8 })
  public void InitializeBoard_Row2_WhitePawns(int col) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(2);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertEquals(Color.WHITE, actual.getColor());
    assertEquals(PieceType.PAWN, actual.getPieceType());
    assertFalse(board.isEmpty(position));
  }

  @ParameterizedTest
  @CsvSource({
      "1, ROOK",
      "2, KNIGHT",
      "3, BISHOP",
      "4, QUEEN",
      "5, KING",
      "6, BISHOP",
      "7, KNIGHT",
      "8, ROOK",
  })
  public void InitializeBoard_Row8ColY_BlackBackRankCorrect(int col,
      PieceType pieceType) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(8);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertEquals(Color.BLACK, actual.getColor());
    assertEquals(pieceType, actual.getPieceType());
    assertFalse(board.isEmpty(position));
  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3, 4, 5, 6, 7, 8 })
  public void InitializeBoard_Row7_BlackPawns(int col) {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(7);
    EasyMock.expect(position.getCol()).andStubReturn(col);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertEquals(Color.BLACK, actual.getColor());
    assertEquals(PieceType.PAWN, actual.getPieceType());
    assertFalse(board.isEmpty(position));
  }

  @Test
  public void GetSnapshot_NewBoard_AllSquaresNull() {
    Board board = new Board();

    Piece[][] snapshot = board.getSnapshot();

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        assertNull(snapshot[row][col]);
      }
    }
  }

  @ParameterizedTest
  @CsvSource({
      "0, 0, WHITE, ROOK",
      "0, 7, WHITE, ROOK",
      "7, 0, BLACK, ROOK",
      "1, 0, WHITE, PAWN",
      "6, 0, BLACK, PAWN",
      "0, 1, WHITE, KNIGHT",
      "0, 4, WHITE, KING",
  })
  public void GetSnapshot_AfterInit_CorrectPiecesAt0IndexedPositions(
      int row, int col, Color color, PieceType pieceType) {
    Board board = new Board();
    board.initializeBoard();

    Piece[][] snapshot = board.getSnapshot();
    Piece actual = snapshot[row][col];

    assertNotNull(actual);
    assertEquals(color, actual.getColor());
    assertEquals(pieceType, actual.getPieceType());
  }

  @Test
  public void InitializeBoard_Row2Col1_IsInstanceOfPawn() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(2);
    EasyMock.expect(position.getCol()).andStubReturn(1);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertInstanceOf(Pawn.class, actual);
  }

  @Test
  public void InitializeBoard_Row1Col2_IsInstanceOfKnight() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(2);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertInstanceOf(Knight.class, actual);
  }

  @Test
  public void InitializeBoard_Row1Col5_IsInstanceOfKing() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(5);

    EasyMock.replay(position);

    board.initializeBoard();

    Piece actual = board.getPieceAt(position);

    assertInstanceOf(King.class, actual);
  }

  @Test
  public void GetValidMoves_EmptyPosition_ThrowsException() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(4);
    EasyMock.expect(position.getCol()).andStubReturn(4);

    EasyMock.replay(position);

    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> board.getValidMoves(position));

    assertTrue(board.isEmpty(position));

    String expected = "Cannot get valid moves at an empty position";
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  public void GetValidMoves_WhitePawnCol1_TwoMoves() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(2);
    EasyMock.expect(position.getCol()).andStubReturn(1);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    List<Position> expected = List.of(new Position(3, 1), new Position(4, 1));

    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
  }

  @Test
  public void GetValidMoves_WhitePawnCol8_TwoMoves() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(2);
    EasyMock.expect(position.getCol()).andStubReturn(8);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    List<Position> expected = List.of(new Position(3, 8), new Position(4, 8));

    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
  }

  @Test
  public void GetValidMoves_WhiteKnightCol2_TwoMoves() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(2);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    List<Position> expected = List.of(new Position(3, 1), new Position(3, 3));

    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
  }

  @Test
  public void GetValidMoves_WhiteKnightCol7_TwoMoves() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(7);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    List<Position> expected = List.of(new Position(3, 6), new Position(3, 8));

    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
  }

  @Test
  public void GetValidMoves_WhiteKing_EmptyList() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(5);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    assertTrue(actual.isEmpty());
  }

  @Test
  public void GetValidMoves_BlackKing_EmptyList() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(8);
    EasyMock.expect(position.getCol()).andStubReturn(5);

    EasyMock.replay(position);

    board.initializeBoard();

    List<Position> actual = board.getValidMoves(position);
    assertTrue(actual.isEmpty());
  }

  @Test
  public void MovePiece_EmptyPosition_ThrowsException() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(2);
    EasyMock.expect(position.getCol()).andStubReturn(1);

    EasyMock.replay(position);

    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(position, position));

    assertTrue(board.isEmpty(position));

    String expected = "Cannot move piece from an empty position";
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  public void MovePiece_IllegalPawnMove_ThrowsException() {
    Board board = new Board();
    Position from = EasyMock.createMock(Position.class);
    Position to = EasyMock.createMock(Position.class);

    EasyMock.expect(from.getRow()).andStubReturn(2);
    EasyMock.expect(from.getCol()).andStubReturn(1);
    EasyMock.expect(to.getRow()).andStubReturn(5);
    EasyMock.expect(to.getCol()).andStubReturn(1);

    EasyMock.replay(from, to);

    board.initializeBoard();

    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(from, to));

    String expected = "Cannot move piece: destination is an illegal move";
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  public void MovePiece_FromRow2Col1ToRow3_PawnVacates() {
    Board board = new Board();
    Position from = EasyMock.createMock(Position.class);
    Position to = new Position(3, 1);

    EasyMock.expect(from.getRow()).andStubReturn(2);
    EasyMock.expect(from.getCol()).andStubReturn(1);

    EasyMock.replay(from);

    board.initializeBoard();
    board.movePiece(from, to);

    assertTrue(board.isEmpty(from));
    assertFalse(board.isEmpty(to));

    Pawn pawn = assertInstanceOf(Pawn.class, board.getPieceAt(to));
    assertTrue(pawn.hasMoved());
  }

  @Test
  public void MovePiece_FromRow2Col1ToRow4_PawnVacates() {
    Board board = new Board();
    Position from = EasyMock.createMock(Position.class);
    Position to = new Position(4, 1);

    EasyMock.expect(from.getRow()).andStubReturn(2);
    EasyMock.expect(from.getCol()).andStubReturn(1);

    EasyMock.replay(from);

    board.initializeBoard();
    board.movePiece(from, to);

    assertTrue(board.isEmpty(from));
    assertFalse(board.isEmpty(to));

    Pawn pawn = assertInstanceOf(Pawn.class, board.getPieceAt(to));
    assertTrue(pawn.hasMoved());
  }

  @Test
  public void MovePiece_FromRow1Col2ToRow3Col3_KnightVacates() {
    Board board = new Board();
    Position from = EasyMock.createMock(Position.class);
    Position to = new Position(3, 3);

    EasyMock.expect(from.getRow()).andStubReturn(1);
    EasyMock.expect(from.getCol()).andStubReturn(2);

    EasyMock.replay(from);

    board.initializeBoard();
    board.movePiece(from, to);

    assertTrue(board.isEmpty(from));
    assertFalse(board.isEmpty(to));

    Knight knight = assertInstanceOf(Knight.class, board.getPieceAt(to));
    assertEquals(Color.WHITE, knight.getColor());

  }

  @Test
  public void MovePiece_AfterFirstPawnMove_OneMove() {
    Board board = new Board();
    Position from = EasyMock.createMock(Position.class);
    Position to = new Position(3, 1);

    EasyMock.expect(from.getRow()).andStubReturn(2);
    EasyMock.expect(from.getCol()).andStubReturn(1);

    EasyMock.replay(from);

    board.initializeBoard();
    board.movePiece(from, to);

    List<Position> actual = board.getValidMoves(to);
    List<Position> expected = List.of(new Position(4, 1));

    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
  }

  @Test
  public void InitializeBoard_Row1Col4_IsInstanceOfQueen() {
    Board board = new Board();
    Position position = EasyMock.createMock(Position.class);

    EasyMock.expect(position.getRow()).andStubReturn(1);
    EasyMock.expect(position.getCol()).andStubReturn(4);
    EasyMock.replay(position);

    board.initializeBoard();

    assertInstanceOf(Queen.class, board.getPieceAt(position));
  }

  @Test
  public void GetValidMoves_WhiteRookEmptyBoard_Returns14Squares() {
    Board board = new Board();
    Position pos = new Position(4, 4);
    board.placePieceAt(pos, new Rook(Color.WHITE));
    board.placePieceAt(new Position(1, 1), new King(Color.WHITE));

    List<Position> moves = board.getValidMoves(pos);

    assertEquals(14, moves.size());
  }

  @Test
  public void GetValidMoves_WhiteBishopFriendlyOnRay_StopsBeforeFriendly() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Bishop(Color.WHITE));
    board.placePieceAt(new Position(6, 6), new Pawn(Color.WHITE));
    board.placePieceAt(new Position(7, 7), new King(Color.WHITE));

    List<Position> moves = board.getValidMoves(new Position(4, 4));

    assertTrue(moves.contains(new Position(5, 5)));
    assertFalse(moves.contains(new Position(6, 6)));
    assertFalse(moves.contains(new Position(7, 7)));
    assertEquals(10, moves.size()); // NE:1, SW:3, NW:3, SE:3
  }

  @Test
  public void GetValidMoves_WhiteBishopEnemyOnRay_IncludesEnemyAndStops() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Bishop(Color.WHITE));
    board.placePieceAt(new Position(6, 6), new Pawn(Color.BLACK));
    board.placePieceAt(new Position(7, 7), new King(Color.WHITE));

    List<Position> moves = board.getValidMoves(new Position(4, 4));

    assertTrue(moves.contains(new Position(5, 5)));
    assertTrue(moves.contains(new Position(6, 6)));
    assertFalse(moves.contains(new Position(7, 7)));
    assertEquals(11, moves.size()); // NE:2, SW:3, NW:3, SE:3
  }

  @Test
  public void GetValidMoves_WhiteKnightEnemyAtDestination_IncludesCapture() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Knight(Color.WHITE));
    board.placePieceAt(new Position(6, 5), new Pawn(Color.BLACK));
    board.placePieceAt(new Position(1, 1), new King(Color.WHITE));

    List<Position> moves = board.getValidMoves(new Position(4, 4));

    assertTrue(moves.contains(new Position(6, 5)));
    assertEquals(8, moves.size());
  }

  @Test
  public void GetValidMoves_WhitePawnEnemyBlocksForward_ReturnsEmpty() {
    Board board = new Board();
    Pawn pawn = new Pawn(Color.WHITE);
    pawn.markMoved();
    board.placePieceAt(new Position(4, 4), pawn);
    board.placePieceAt(new Position(5, 4), new Pawn(Color.BLACK));

    List<Position> moves = board.getValidMoves(new Position(4, 4));

    assertEquals(0, moves.size());
  }

  @Test
  public void MovePiece_KnightCapturesEnemyPawn_PawnRemovedKnightMoves() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Knight(Color.WHITE));
    board.placePieceAt(new Position(6, 5), new Pawn(Color.BLACK));
    board.placePieceAt(new Position(1, 1), new King(Color.WHITE));

    board.movePiece(new Position(4, 4), new Position(6, 5));

    assertInstanceOf(Knight.class, board.getPieceAt(new Position(6, 5)));
    assertEquals(Color.WHITE, board.getPieceAt(new Position(6, 5)).getColor());
    assertTrue(board.isEmpty(new Position(4, 4)));
  }

  @Test
  public void MovePiece_WhiteKingRow1Col5ToRow2Col6_KingVacates() {
    Board board = new Board();
    Position pawnFrom = new Position(2, 6);
    Position pawnTo = new Position(3, 6);
    Position kingFrom = new Position(1, 5);

    board.initializeBoard();
    board.movePiece(pawnFrom, pawnTo);
    board.movePiece(kingFrom, pawnFrom);

    assertTrue(board.isEmpty(kingFrom));
    assertFalse(board.isEmpty(pawnFrom));

    King king = assertInstanceOf(King.class, board.getPieceAt(pawnFrom));
    assertEquals(Color.WHITE, king.getColor());
    assertTrue(king.hasMoved());
  }

  @Test
  public void MovePiece_BlackKingRow8Col5ToRow7Col4_KingVacates() {
    Board board = new Board();
    Position pawnFrom = new Position(7, 4);
    Position pawnTo = new Position(6, 4);
    Position kingFrom = new Position(8, 5);

    board.initializeBoard();
    board.movePiece(pawnFrom, pawnTo);
    board.movePiece(kingFrom, pawnFrom);

    assertTrue(board.isEmpty(kingFrom));
    assertFalse(board.isEmpty(pawnFrom));

    King king = assertInstanceOf(King.class, board.getPieceAt(pawnFrom));
    assertEquals(Color.BLACK, king.getColor());
    assertTrue(king.hasMoved());
  }

  @Test
  public void IsInCheck_InitializedBoardWhite_ReturnsFalse() {
    Board board = new Board();
    board.initializeBoard();
    assertFalse(board.isInCheck(Color.WHITE));
  }

  @Test
  public void IsInCheck_InitializedBoardBlack_ReturnsFalse() {
    Board board = new Board();
    board.initializeBoard();
    assertFalse(board.isInCheck(Color.BLACK));
  }

  @Test
  public void IsInCheck_WhiteKing58BlackRook51_ReturnsTrue() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece rook = mockPiece(Color.BLACK, PieceType.ROOK, List.of(new Position(5, 8)));

    EasyMock.replay(king, rook);

    board.placePieceAt(new Position(5, 8), king);
    board.placePieceAt(new Position(5, 1), rook);

    assertTrue(board.isInCheck(Color.WHITE));

    EasyMock.verify(rook);

  }

  @Test
  public void IsInCheck_WhiteKingBlackRookWhitePawn_ReturnsFalse() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece pawn = stubPiece(Color.WHITE, PieceType.PAWN, List.of());
    Piece rook = stubPiece(Color.BLACK, PieceType.ROOK, List.of(
        new Position(5, 2),
        new Position(5, 3),
        new Position(5, 4),
        new Position(5, 5)));
    int[][] slidingDirections = { { 0, 1 } };
    EasyMock.expect(rook.getSlidingDirections()).andReturn(slidingDirections);

    EasyMock.replay(king, rook, pawn);

    board.placePieceAt(new Position(5, 5), king);
    board.placePieceAt(new Position(5, 3), pawn);
    board.placePieceAt(new Position(5, 1), rook);

    assertFalse(board.isInCheck(Color.WHITE));

    EasyMock.verify(rook);

  }

  @Test
  public void IsInCheck_WhiteKingBlackKnight_ReturnsTrue() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece knight = mockPiece(Color.BLACK, PieceType.KNIGHT, List.of(
        new Position(5, 5),
        new Position(5, 3)));

    EasyMock.replay(king, knight);

    board.placePieceAt(new Position(5, 5), king);
    board.placePieceAt(new Position(3, 4), knight);

    assertTrue(board.isInCheck(Color.WHITE));

    EasyMock.verify(knight);

  }

  @Test
  public void IsInCheck_BlackKingWhiteQueen_ReturnsTrue() {
    Board board = new Board();
    Piece king = stubPiece(Color.BLACK, PieceType.KING, List.of());
    Piece queen = stubPiece(Color.WHITE, PieceType.QUEEN, List.of());

    int[][] slidingDirections = { { 0, 1 }, { 1, 1 } };
    EasyMock.expect(queen.getSlidingDirections()).andReturn(slidingDirections);

    EasyMock.replay(king, queen);

    board.placePieceAt(new Position(5, 5), king);
    board.placePieceAt(new Position(5, 1), queen);

    assertTrue(board.isInCheck(Color.BLACK));

    EasyMock.verify(queen);

  }

  @Test
  public void IsInCheck_BlackKingRookWhiteKingRook_ReturnsTrue() {
    Board board = new Board();
    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece blackKing = stubPiece(Color.BLACK, PieceType.KING, List.of());
    Piece whiteRook = stubPiece(Color.WHITE, PieceType.ROOK, List.of());
    Piece blackRook = stubPiece(Color.BLACK, PieceType.ROOK, List.of());

    int[][] slidingDirections = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    EasyMock.expect(blackRook.getSlidingDirections()).andReturn(slidingDirections);
    EasyMock.expect(whiteRook.getSlidingDirections()).andReturn(slidingDirections);

    EasyMock.replay(whiteKing, whiteRook, blackKing, blackRook);

    board.placePieceAt(new Position(4, 5), whiteKing);
    board.placePieceAt(new Position(1, 1), whiteRook);
    board.placePieceAt(new Position(8, 1), blackKing);
    board.placePieceAt(new Position(4, 1), blackRook);

    assertFalse(board.isInCheck(Color.BLACK));
    assertTrue(board.isInCheck(Color.WHITE));

    EasyMock.verify(blackRook, whiteRook);

  }

  @Test
  public void GetValidMoves_WhiteRookPinnedAlongRankByBlackRook_IncludesPinRayExcludesOffRay() {
    Board board = new Board();

    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece whiteRook = stubPiece(Color.WHITE, PieceType.ROOK, List.of());
    Piece blackRook = stubPiece(Color.BLACK, PieceType.ROOK, List.of());

    int[][] rookDirections = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    EasyMock.expect(whiteRook.getSlidingDirections()).andReturn(rookDirections);
    EasyMock.expect(blackRook.getSlidingDirections()).andReturn(rookDirections).times(9);

    EasyMock.replay(whiteKing, whiteRook, blackRook);

    board.placePieceAt(new Position(1, 5), whiteKing);
    board.placePieceAt(new Position(1, 3), whiteRook);
    board.placePieceAt(new Position(1, 1), blackRook);

    List<Position> moves = board.getValidMoves(new Position(1, 3));

    assertTrue(moves.contains(new Position(1, 2)));
    assertFalse(moves.contains(new Position(2, 3)));
  }

  @Test
  public void GetValidMoves_WhiteKingInCheckByBlackRook_ExcludesAttackedSquareIncludesSafeSquare() {
    Board board = new Board();

    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of(
        new Position(4, 4), new Position(4, 5), new Position(4, 6),
        new Position(5, 4), new Position(5, 6),
        new Position(6, 4), new Position(6, 5), new Position(6, 6)));
    Piece blackRook = stubPiece(Color.BLACK, PieceType.ROOK, List.of());

    int[][] rookDirections = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    EasyMock.expect(blackRook.getSlidingDirections()).andReturn(rookDirections).times(8);

    EasyMock.replay(whiteKing, blackRook);

    board.placePieceAt(new Position(5, 5), whiteKing);
    board.placePieceAt(new Position(5, 1), blackRook);

    List<Position> moves = board.getValidMoves(new Position(5, 5));

    assertFalse(moves.contains(new Position(5, 6)));
    assertTrue(moves.contains(new Position(6, 5)));

    EasyMock.verify(blackRook);
  }

  @Test
  public void GetValidMoves_WhiteKnightBlocksCheckByBlackRook_IncludesBlockExcludesNonBlock() {
    Board board = new Board();

    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece whiteKnight = stubPiece(Color.WHITE, PieceType.KNIGHT, List.of(
        new Position(5, 4), new Position(5, 2),
        new Position(1, 4), new Position(1, 2),
        new Position(4, 5), new Position(4, 1),
        new Position(2, 5), new Position(2, 1)));
    Piece blackRook = stubPiece(Color.BLACK, PieceType.ROOK, List.of());

    int[][] rookDirections = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    EasyMock.expect(blackRook.getSlidingDirections()).andReturn(rookDirections).times(8);

    EasyMock.replay(whiteKing, whiteKnight, blackRook);

    board.placePieceAt(new Position(5, 8), whiteKing);
    board.placePieceAt(new Position(3, 3), whiteKnight);
    board.placePieceAt(new Position(5, 1), blackRook);

    List<Position> moves = board.getValidMoves(new Position(3, 3));

    assertTrue(moves.contains(new Position(5, 4)));
    assertFalse(moves.contains(new Position(1, 2)));

    EasyMock.verify(blackRook);
  }

  @Test
  public void GetValidMoves_WhiteKingCapturesAttackingBlackRook_IncludesCaptureSquare() {
    Board board = new Board();

    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of(
        new Position(4, 4), new Position(4, 5), new Position(4, 6),
        new Position(5, 4), new Position(5, 6),
        new Position(6, 4), new Position(6, 5), new Position(6, 6)));
    Piece blackRook = stubPiece(Color.BLACK, PieceType.ROOK, List.of());

    int[][] rookDirections = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    EasyMock.expect(blackRook.getSlidingDirections()).andReturn(rookDirections).times(7);

    EasyMock.replay(whiteKing, blackRook);

    board.placePieceAt(new Position(5, 5), whiteKing);
    board.placePieceAt(new Position(6, 5), blackRook);

    List<Position> moves = board.getValidMoves(new Position(5, 5));

    assertTrue(moves.contains(new Position(6, 5)));

    EasyMock.verify(blackRook);
  }

  @Test
  public void GetValidMoves_WhiteKnightFullyPinnedByBlackBishop_ReturnsEmpty() {
    Board board = new Board();

    Piece whiteKing = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece whiteKnight = stubPiece(Color.WHITE, PieceType.KNIGHT, List.of(
        new Position(5, 4), new Position(5, 2),
        new Position(1, 4), new Position(1, 2),
        new Position(4, 5), new Position(4, 1),
        new Position(2, 5), new Position(2, 1)));
    Piece blackBishop = stubPiece(Color.BLACK, PieceType.BISHOP, List.of());

    int[][] bishopDirections = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
    EasyMock.expect(blackBishop.getSlidingDirections()).andReturn(bishopDirections).times(8);

    EasyMock.replay(whiteKing, whiteKnight, blackBishop);

    board.placePieceAt(new Position(1, 5), whiteKing);
    board.placePieceAt(new Position(3, 3), whiteKnight);
    board.placePieceAt(new Position(5, 1), blackBishop);

    List<Position> moves = board.getValidMoves(new Position(3, 3));

    assertTrue(moves.isEmpty());

    EasyMock.verify(blackBishop);
  }

  @Test
  public void GetValidMovesForPlayer_AfterInitColorWhite_NonEmptyList() {
    Board board = new Board();
    board.initializeBoard();

    List<Position> actual = board.getValidMovesForPlayer(Color.WHITE);

    assertFalse(actual.isEmpty());
  }

  @Test
  public void GetValidMovesForPlayer_AfterInitColorBlack_NonEmptyList() {
    Board board = new Board();
    board.initializeBoard();

    List<Position> actual = board.getValidMovesForPlayer(Color.BLACK);

    assertFalse(actual.isEmpty());
  }

  @Test
  public void GetValidMovesForPlayer_WhiteKingTrappedByBlackRooks_EmptyList() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of(
        new Position(2, 1), new Position(1, 2), new Position(2, 2)));
    Piece rook1 = stubPiece(Color.BLACK, PieceType.ROOK, List.of(
        new Position(2, 1), new Position(2, 2)));
    Piece rook2 = stubPiece(Color.BLACK, PieceType.ROOK, List.of(
        new Position(1, 2), new Position(2, 2)));

    EasyMock.replay(king, rook1, rook2);

    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(2, 8), rook1);
    board.placePieceAt(new Position(8, 2), rook2);

    List<Position> actual = board.getValidMovesForPlayer(Color.WHITE);

    assertTrue(actual.isEmpty());

    EasyMock.verify(king, rook1, rook2);
  }

  @Test
  public void GetValidMovesForPlayer_BlackKingTrappedByWhiteRooks_EmptyList() {
    Board board = new Board();
    Piece king = stubPiece(Color.BLACK, PieceType.KING, List.of(
        new Position(7, 8), new Position(8, 7), new Position(7, 7)));
    Piece rook1 = stubPiece(Color.WHITE, PieceType.ROOK, List.of(
        new Position(7, 8), new Position(7, 7)));
    Piece rook2 = stubPiece(Color.WHITE, PieceType.ROOK, List.of(
        new Position(8, 7), new Position(7, 7)));

    EasyMock.replay(king, rook1, rook2);

    board.placePieceAt(new Position(8, 8), king);
    board.placePieceAt(new Position(7, 1), rook1);
    board.placePieceAt(new Position(1, 7), rook2);

    List<Position> actual = board.getValidMovesForPlayer(Color.BLACK);

    assertTrue(actual.isEmpty());

    EasyMock.verify(king, rook1, rook2);
  }

  @Test
  public void CreatePiece_OnNull_ThrowsException() {
    Board board = new Board();

    Exception exception = assertThrows(
        IllegalStateException.class,
        () -> board.createPiece(null, null));

    String expected = "Unhandled piece type: " + null;
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  public void IsInCheck_OnEmptyBoard_ThrowsException() {
    Board board = new Board();

    Exception exception = assertThrows(
        IllegalStateException.class,
        () -> board.isInCheck(Color.WHITE));

    String expected = "cannot locateKing: king of specified color is not on the board.";
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  public void PromotePawn_WhitePawnAtRank8ToQueen_ReplacesWithQueen() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    board.promotePawn(new Position(8, 4), PieceType.QUEEN);

    Piece piece = board.getPieceAt(new Position(8, 4));
    assertEquals(PieceType.QUEEN, piece.getPieceType());
    assertEquals(Color.WHITE, piece.getColor());
    assertInstanceOf(Queen.class, piece);
  }

  @Test
  public void PromotePawn_WhitePawnAtRank8ToRook_ReplacesWithRook() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    board.promotePawn(new Position(8, 4), PieceType.ROOK);

    Piece piece = board.getPieceAt(new Position(8, 4));
    assertEquals(PieceType.ROOK, piece.getPieceType());
    assertEquals(Color.WHITE, piece.getColor());
    assertInstanceOf(Rook.class, piece);
  }

  @Test
  public void PromotePawn_WhitePawnAtRank8ToBishop_ReplacesWithBishop() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    board.promotePawn(new Position(8, 4), PieceType.BISHOP);

    Piece piece = board.getPieceAt(new Position(8, 4));
    assertEquals(PieceType.BISHOP, piece.getPieceType());
    assertEquals(Color.WHITE, piece.getColor());
    assertInstanceOf(Bishop.class, piece);
  }

  @Test
  public void PromotePawn_WhitePawnAtRank8ToKnight_ReplacesWithKnight() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    board.promotePawn(new Position(8, 4), PieceType.KNIGHT);

    Piece piece = board.getPieceAt(new Position(8, 4));
    assertEquals(PieceType.KNIGHT, piece.getPieceType());
    assertEquals(Color.WHITE, piece.getColor());
    assertInstanceOf(Knight.class, piece);
  }

  @Test
  public void PromotePawn_BlackPawnAtRank1ToQueen_ReplacesWithQueen() {
    Board board = new Board();
    board.placePieceAt(new Position(1, 4), new Pawn(Color.BLACK));

    board.promotePawn(new Position(1, 4), PieceType.QUEEN);

    Piece piece = board.getPieceAt(new Position(1, 4));
    assertEquals(PieceType.QUEEN, piece.getPieceType());
    assertEquals(Color.BLACK, piece.getColor());
    assertInstanceOf(Queen.class, piece);
  }

  @Test
  public void PromotePawn_WhitePawnAtMidBoard_ThrowsIllegalArgumentException() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Pawn(Color.WHITE));

    assertThrows(IllegalArgumentException.class,
        () -> board.promotePawn(new Position(4, 4), PieceType.QUEEN));
  }

  @Test
  public void PromotePawn_NonPawnAtRank8_ThrowsIllegalArgumentException() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Knight(Color.WHITE));

    assertThrows(IllegalArgumentException.class,
        () -> board.promotePawn(new Position(8, 4), PieceType.QUEEN));
  }

  @Test
  public void PromotePawn_PromoteToPawn_ThrowsIllegalArgumentException() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    assertThrows(IllegalArgumentException.class,
        () -> board.promotePawn(new Position(8, 4), PieceType.PAWN));
  }

  @Test
  public void PromotePawn_PromoteToKing_ThrowsIllegalArgumentException() {
    Board board = new Board();
    board.placePieceAt(new Position(8, 4), new Pawn(Color.WHITE));

    assertThrows(IllegalArgumentException.class,
        () -> board.promotePawn(new Position(8, 4), PieceType.KING));
  }

  @Test
  public void GetValidMoves_UnmovedPawnWithPieceOneAhead_ReturnEmpty() {
    Board board = new Board();
    Pawn pawn = new Pawn(Color.WHITE);
    board.placePieceAt(new Position(2, 1), pawn);
    board.placePieceAt(new Position(3, 1), new Pawn(Color.BLACK));

    List<Position> moves = board.getValidMoves(new Position(2, 1));

    assertTrue(moves.isEmpty());
  }

  @Test
  public void GetValidMoves_UnmovedBlackPawnWithPieceOneAhead_ReturnsEmpty() {
    Board board = new Board();
    Pawn pawn = new Pawn(Color.BLACK);
    board.placePieceAt(new Position(7, 1), pawn);
    board.placePieceAt(new Position(6, 1), new Pawn(Color.WHITE));

    List<Position> moves = board.getValidMoves(new Position(7, 1));

    assertTrue(moves.isEmpty());
  }

  @Test
  public void IsInCheck_NoKingOnBoard_ThrowsIllegalState() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Pawn(Color.WHITE));

    assertThrows(IllegalStateException.class, () -> board.isInCheck(Color.WHITE));
  }

  @Test
  public void PromotePawn_EmptyPosition_ThrowsIllegalArgumentException() {
    Board board = new Board();

    assertThrows(IllegalArgumentException.class,
        () -> board.promotePawn(new Position(8, 4), PieceType.QUEEN));
  }

  @Test
  public void hasInsufficientMaterial_NullColor_ThrowsExcception() {
    Board board = new Board();

    assertThrows(IllegalArgumentException.class,
        () -> board.hasInsufficientMaterial(null));

  }

  @Test
  public void hasInsufficientMaterial_White_OnlyKing() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    EasyMock.replay(king);
    board.placePieceAt(new Position(1, 1), king);

    assertTrue(board.hasInsufficientMaterial(Color.WHITE));
  }

  @Test
  public void hasInsufficientMaterial_Black_KingKnight() {
    Board board = new Board();
    Piece king = stubPiece(Color.BLACK, PieceType.KING, List.of());
    Piece knight = stubPiece(Color.BLACK, PieceType.KNIGHT, List.of());
    EasyMock.replay(king, knight );
    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(4, 4), knight);

    assertTrue(board.hasInsufficientMaterial(Color.BLACK));
  }

  @Test
  public void hasInsufficientMaterial_White_KingBishop() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece bishop = stubPiece(Color.WHITE, PieceType.BISHOP, List.of());
    EasyMock.replay(king, bishop);
    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(4, 4), bishop);


    assertTrue(board.hasInsufficientMaterial(Color.WHITE));
  }

  @Test
  public void hasInsufficientMaterial_Black_King2Knights() {
    Board board = new Board();
    Piece king = stubPiece(Color.BLACK, PieceType.KING, List.of());
    Piece knight1 = stubPiece(Color.BLACK, PieceType.KNIGHT, List.of());
    Piece knight2 = stubPiece(Color.BLACK, PieceType.KNIGHT, List.of());
    EasyMock.replay(king, knight1, knight2);
    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(4, 4), knight1);
    board.placePieceAt(new Position(8, 8), knight2);

    assertTrue(board.hasInsufficientMaterial(Color.BLACK));
  }

  @Test
  public void hasSufficientMaterial_White_KingRook() {
    Board board = new Board();
    Piece king = stubPiece(Color.WHITE, PieceType.KING, List.of());
    Piece rook = stubPiece(Color.WHITE, PieceType.ROOK, List.of());
    EasyMock.replay(king, rook);
    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(4, 4), rook);

    assertFalse(board.hasInsufficientMaterial(Color.WHITE));
  }

  @Test
  public void hasSufficientMaterial_Black_King2Bishops() {
    Board board = new Board();
    Piece king = stubPiece(Color.BLACK, PieceType.KING, List.of());
    Piece bishop1 = stubPiece(Color.BLACK, PieceType.BISHOP, List.of());
    Piece bishop2 = stubPiece(Color.BLACK, PieceType.BISHOP, List.of());
    EasyMock.replay(king, bishop1, bishop2);
    board.placePieceAt(new Position(1, 1), king);
    board.placePieceAt(new Position(4, 4), bishop1);
    board.placePieceAt(new Position(8, 8), bishop2); 

    assertFalse(board.hasInsufficientMaterial(Color.BLACK));
  }
}
