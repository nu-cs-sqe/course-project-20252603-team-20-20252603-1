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

import java.util.List;
import java.util.NoSuchElementException;

public class BoardTest {

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

    List<Position> moves = board.getValidMoves(pos);

    assertEquals(14, moves.size());
  }

  @Test
  public void GetValidMoves_WhiteBishopFriendlyOnRay_StopsBeforeFriendly() {
    Board board = new Board();
    board.placePieceAt(new Position(4, 4), new Bishop(Color.WHITE));
    board.placePieceAt(new Position(6, 6), new Pawn(Color.WHITE));

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
  public void IsInCheck_InitializedBoardWhite_False() {
    Board board = new Board();
    board.initializeBoard();
    assertFalse(board.isInCheck(Color.WHITE));
  }
}
