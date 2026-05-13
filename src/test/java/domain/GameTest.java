package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import domain.piece.*;

public class GameTest {

    @Test
    public void Game_NewGame_Null() {
        
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                new Game(null);
            });

	    assertEquals("No valid board passed", exception.getMessage());


    }

    @Test
    public void StartGame_GetPieceAt_PieceExists() {
        Board board = EasyMock.createMock(Board.class);
        Position position = new Position(1,1);
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
        Position position = new Position(4,4);

        Game game = new Game(board);

        board.initializeBoard();
        EasyMock.expect(board.getPieceAt(position)).andThrow(new NoSuchElementException("Cannot get piece at empty position"));
        EasyMock.replay(board);

        game.startGame();

        NoSuchElementException exception = 
            assertThrows(NoSuchElementException.class, 
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

        IllegalStateException exception = 
            assertThrows(IllegalStateException.class, 
                () -> game.getCurrentTurn());

        assertEquals("Game has not started yet, no player has a turn", exception.getMessage());

    }

    @Test
    public void BeforeStartGame_NoPieces() {
        Board board = EasyMock.createMock(Board.class);
        Position position = new Position(1,1);
        Game game = new Game(board);

        IllegalStateException exception = 
            assertThrows(IllegalStateException.class, 
                () -> game.getPieceAt(position));

        assertEquals("Game has not started yet, no pieces are on the board", exception.getMessage());

    }
}
