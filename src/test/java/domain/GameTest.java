package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
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
}
