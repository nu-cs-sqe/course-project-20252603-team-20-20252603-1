package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.NoSuchElementException;
import domain.Position;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import domain.piece.*;

public class GameTest {

    @Test
    public void Game_NewGame_Null() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game(null);
        });

        assertEquals("No valid board passed", exception.getMessage());
    }

    @Test
    public void Game_NewGameNoParam_NotNull() {

        Game game = new Game();

        assertDoesNotThrow(() -> game.startGame());
    }

    @Test
    public void StartGame_GetPieceAt_PieceExists() {
        Board board = EasyMock.createMock(Board.class);
        Position position = new Position(1, 1);
        PieceType pieceType = PieceType.ROOK;
        Color color = Color.WHITE;
        Piece expected = new Piece(pieceType, color);

        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expect(board.getPieceAt(position)).andReturn(expected);
        EasyMock.replay(board);

        game.startGame();

        Piece actual = game.getPieceAt(position);
        assertEquals(Color.WHITE, actual.getColor());
        assertEquals(pieceType, actual.getPieceType());

        EasyMock.verify(board);
    }

    @Test
    public void StartGame_GetPieceAt_EmptySquare() {
        Board board = EasyMock.createMock(Board.class);
        Position position = new Position(4, 4);

        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expect(board.getPieceAt(position))
                .andThrow(new NoSuchElementException("Cannot get piece at empty position"));
        EasyMock.replay(board);

        game.startGame();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> game.getPieceAt(position));

        assertEquals("Cannot get piece at empty position", exception.getMessage());

        EasyMock.verify(board);
    }

    @Test
    public void StartGame_GetCurrentTurn() {
        Board board = EasyMock.createMock(Board.class);
        Color color = Color.WHITE;
        Game game = new Game(board);
        board.initializeBoard();

        EasyMock.replay(board);

        game.startGame();
        Color turn = game.getCurrentTurn();
        assertEquals(color, turn);

        EasyMock.verify(board);
    }

    @Test
    public void BeforeStartGame_TurnInvalid() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> game.getCurrentTurn());

        assertEquals("Game has not started yet, no player has a turn", exception.getMessage());
    }

    @Test
    public void BeforeStartGame_NoPieces() {
        Board board = EasyMock.createMock(Board.class);
        Position position = new Position(1, 1);
        Game game = new Game(board);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> game.getPieceAt(position));

        assertEquals("Game has not started yet, no pieces are on the board", exception.getMessage());
    }

    @Test
    public void StartGame_AlreadyStarted_ThrowsIllegalState() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.replay(board);

        game.startGame();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> game.startGame());

        assertEquals("Game has already started, cannot restart", exception.getMessage());

        EasyMock.verify(board);
    }

    @Test
    public void ExecuteMove_GameNotStarted_ThrowsIllegalState() {
        Board board = EasyMock.createMock(Board.class);
        Position from = EasyMock.createMock(Position.class);
        Position to = EasyMock.createMock(Position.class);
        Game game = new Game(board);

        EasyMock.replay(board, from, to);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> game.executeMove(from, to));

        String expected = "Cannot execute move if the game has not started.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board);

    }

    @Test
    public void ExecuteMove_SelectedBlackOnWhiteTurn_ThrowsIllegalArgument() {
        Board board = EasyMock.createMock(Board.class);
        Position from = EasyMock.createMock(Position.class);
        Position to = EasyMock.createMock(Position.class);
        Piece piece = EasyMock.createMock(Piece.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();
        EasyMock.expect(board.getPieceAt(from)).andReturn(piece);
        EasyMock.expect(piece.getColor()).andStubReturn(Color.BLACK);
        EasyMock.replay(board, piece, from, to);

        game.startGame();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> game.executeMove(from, to));

        String expected = "Cannot execute move if the current turn is not the piece's color.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board, piece);

    }

    @Test
    public void ExecuteMove_WhitesTurn_BlacksTurn() {
        Board board = EasyMock.createMock(Board.class);
        Position from = EasyMock.createMock(Position.class);
        Position to = EasyMock.createMock(Position.class);
        Piece piece = EasyMock.createMock(Piece.class);
        Game game = new Game(board);

        EasyMock.expect(from.getRow()).andStubReturn(2);
        EasyMock.expect(from.getRow()).andStubReturn(1);
        EasyMock.expect(to.getRow()).andStubReturn(3);
        EasyMock.expect(to.getRow()).andStubReturn(1);

        board.initializeBoard();
        EasyMock.expectLastCall();

        board.movePiece(from, to);
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(from)).andReturn(piece);
        EasyMock.expect(piece.getColor()).andStubReturn(Color.WHITE);
        EasyMock.expect(board.getPieceAt(to)).andReturn(piece);
        EasyMock.expect(piece.getPieceType()).andReturn(PieceType.KNIGHT);

        EasyMock.replay(board, piece, from, to);

        game.startGame();
        game.executeMove(from, to);

        Color actual = game.getCurrentTurn();

        assertEquals(Color.BLACK, actual);

        EasyMock.verify(board, piece);

    }

    @Test
    public void ExecuteMove_BlackTurn_WhiteTurn() {
        Board board = EasyMock.createMock(Board.class);
        Position whiteFrom = EasyMock.createMock(Position.class);
        Position whiteTo = EasyMock.createMock(Position.class);
        Position blackFrom = EasyMock.createMock(Position.class);
        Position blackTo = EasyMock.createMock(Position.class);
        Piece whitePiece = EasyMock.createMock(Piece.class);
        Piece blackPiece = EasyMock.createMock(Piece.class);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(whiteFrom)).andReturn(whitePiece);
        EasyMock.expect(whitePiece.getColor()).andReturn(Color.WHITE);

        board.movePiece(whiteFrom, whiteTo);
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(blackFrom)).andReturn(blackPiece);
        EasyMock.expect(blackPiece.getColor()).andReturn(Color.BLACK);

        board.movePiece(blackFrom, blackTo);
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(whiteTo)).andReturn(whitePiece);
        EasyMock.expect(whitePiece.getPieceType()).andReturn(PieceType.KNIGHT);
        EasyMock.expect(board.getPieceAt(blackTo)).andReturn(blackPiece);
        EasyMock.expect(blackPiece.getPieceType()).andReturn(PieceType.KNIGHT);

        EasyMock.replay(board, whitePiece, blackPiece);

        Game game = new Game(board);
        game.startGame();
        game.executeMove(whiteFrom, whiteTo);
        game.executeMove(blackFrom, blackTo);

        assertEquals(Color.WHITE, game.getCurrentTurn());
        EasyMock.verify(board, whitePiece, blackPiece);
    }

    @Test
    public void ValidMoves_GameNotStarted_ThrowsIllegalState() {
        Board board = EasyMock.createMock(Board.class);
        Position position = EasyMock.createMock(Position.class);
        Game game = new Game(board);

        EasyMock.replay(board, position);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> game.getValidMoves(position));

        String expected = "Cannot get valid moves if the game has not started.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board);

    }

    @Test
    public void ValidMoves_WrongColor_ThrowsIllegalArgument() {
        Board board = EasyMock.createMock(Board.class);
        Position position = EasyMock.createMock(Position.class);
        Piece piece = EasyMock.createMock(Piece.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(position.getRow()).andStubReturn(7);
        EasyMock.expect(position.getRow()).andStubReturn(1);

        EasyMock.expect(board.getPieceAt(position)).andReturn(piece);
        EasyMock.expect(piece.getColor()).andReturn(Color.BLACK);

        EasyMock.replay(board, position, piece);

        game.startGame();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> game.getValidMoves(position));

        String expected = "Cannot get moves for piece that the current turn's color";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board, piece);

    }

    @Test
    public void ValidMoves_WhitePawn_TwoMoves() {
        Board board = EasyMock.createMock(Board.class);
        Position position = EasyMock.createMock(Position.class);
        Piece piece = EasyMock.createMock(Piece.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(position)).andReturn(piece);
        EasyMock.expect(piece.getColor()).andReturn(Color.WHITE);

        EasyMock.expect(board.getValidMoves(position)).andReturn(List.of(
                new Position(3, 1),
                new Position(4, 1)));

        EasyMock.replay(board, position, piece);

        game.startGame();
        List<Position> actual = game.getValidMoves(position);
        List<Position> expected = List.of(new Position(3, 1), new Position(4, 1));

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));

        EasyMock.verify(board, piece);
    }

    @Test
    public void BoardSnapshot_GameNotStarted_ThrowsIllegalState() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        EasyMock.replay(board);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> game.getBoardSnapshot());

        String expected = "Cannot get board snapshot if the game has not started.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board);

    }

    @Test
    public void BoardSnapshot_StartGame_CallsBoardSnapshot() {
        Board board = EasyMock.createMock(Board.class);
        Piece[][] snapshot = new Piece[8][8];
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.getSnapshot()).andReturn(snapshot);

        EasyMock.replay(board);

        game.startGame();
        Piece[][] actual = game.getBoardSnapshot();

        // check same pointer
        assertTrue(snapshot == actual);

        EasyMock.verify(board);
    }

    @Test
    public void ExecuteMove_WhiteKnightCapturesBlackPawn_TurnSwitches() {
        Board board = new Board();
        Game game = new Game(board);
        game.startGame();
        board.placePieceAt(new Position(4, 4), new Knight(Color.WHITE));
        board.placePieceAt(new Position(6, 5), new Pawn(Color.BLACK));

        game.executeMove(new Position(4, 4), new Position(6, 5));

        assertTrue(board.isEmpty(new Position(4, 4)));
        assertEquals(Color.WHITE, board.getPieceAt(new Position(6, 5)).getColor());
        assertEquals(PieceType.KNIGHT, board.getPieceAt(new Position(6, 5)).getPieceType());
        assertEquals(Color.BLACK, game.getCurrentTurn());
    }

    @Test
    public void PlayerInCheck_GameNotStarted_ThrowsIllegalState() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        EasyMock.replay(board);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> game.playerInCheck(Color.WHITE));

        String expected = "Cannot check if players are in check if the game has not started.";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

        EasyMock.verify(board);

    }

    @Test
    public void PlayerInCheck_FirstTurnWhite_ReturnsFalse() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.isInCheck(Color.WHITE)).andReturn(false);
        EasyMock.replay(board);

        game.startGame();

        assertFalse(game.playerInCheck(Color.WHITE));

        EasyMock.verify(board);

    }

    @Test
    public void PlayerInCheck_WhiteInCheck_ReturnsTrue() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.isInCheck(Color.WHITE)).andReturn(true);
        EasyMock.replay(board);

        game.startGame();

        assertTrue(game.playerInCheck(Color.WHITE));

        EasyMock.verify(board);

    }

    @Test
    public void PlayerInCheck_BlackInCheck_ReturnsTrue() {
        Board board = EasyMock.createMock(Board.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.isInCheck(Color.BLACK)).andReturn(true);
        EasyMock.replay(board);

        game.startGame();

        assertTrue(game.playerInCheck(Color.BLACK));

        EasyMock.verify(board);

    }

    @Test
    public void ExecuteMove_KnightMove_BlacksTurn() {
        Board board = EasyMock.createMock(Board.class);
        Piece piece = EasyMock.createMock(Knight.class);
        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expectLastCall();

        EasyMock.expect(board.getPieceAt(new Position(4, 4))).andReturn(piece);
        EasyMock.expect(piece.getColor()).andStubReturn(Color.WHITE);

        board.movePiece(new Position(4, 4), new Position(6, 5));
        EasyMock.expectLastCall();

        EasyMock.replay(board, piece);

        game.startGame();
        game.executeMove(new Position(4, 4), new Position(6, 5));

        assertEquals(Color.BLACK, game.getCurrentTurn());

        EasyMock.verify(board);

    }

    @Test
    public void IsPromotionPending_AfterStart_ReturnsFalse() {
        Game game = new Game();
        game.startGame();

        assertFalse(game.isPromotionPending());
    }

    @Test
    public void ExecuteMove_WhitePawnReachesRank8_PromotionPendingTrue() {
        Board board = new Board();
        Game game = new Game(board);
        game.startGame();

        // clear BLACK queen at (8,4) and place WHITE pawn at (7,4)
        board.placePieceAt(new Position(8, 4), null);
        Pawn pawn = new Pawn(Color.WHITE);
        pawn.markMoved();
        board.placePieceAt(new Position(7, 4), pawn);

        game.executeMove(new Position(7, 4), new Position(8, 4));

        assertTrue(game.isPromotionPending());
    }
}
