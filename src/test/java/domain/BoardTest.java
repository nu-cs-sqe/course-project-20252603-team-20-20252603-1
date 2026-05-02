package domain;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    /*
    * isEmpty() unit tests
    * */

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 0",
        "6, 0",
        "7, 0",
        "0, 1",
        "0, 6",
        "0, 7",
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
    public void IsEmpty_AfterInitRow0Col0_IsFalse() {
        Board board = new Board();
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(0);
        EasyMock.expect(position.getCol()).andStubReturn(0);

        EasyMock.replay(position);

        board.initializeBoard();

        boolean actual = board.isEmpty(position);

        assertFalse(actual);
    }

    /*
    * getPieceAt() SOCIABLE unit tests
    * */

    @Test
    public void GetPieceAt_AfterInitRow0Col0_WhiteRook() {
        Board board = new Board();
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(0);
        EasyMock.expect(position.getCol()).andStubReturn(0);

        EasyMock.replay(position);

        board.initializeBoard();

        Piece actual = board.getPieceAt(position);

        assertEquals(Color.WHITE, actual.getColor());
        assertEquals(PieceType.ROOK, actual.getPieceType());
        assertFalse(board.isEmpty(position));

    }

    @Test
    public void GetPieceAt_NewBoardRow0Col0_ThrowsException() {
        Board board = new Board();
        Position position = EasyMock.createMock(Position.class);

        EasyMock.expect(position.getRow()).andStubReturn(0);
        EasyMock.expect(position.getCol()).andStubReturn(0);

        EasyMock.replay(position);

        Exception exception = assertThrows(
            NoSuchElementException.class,
            () -> board.getPieceAt(position)
        );

        assertTrue(board.isEmpty(position));

        String expected = "Cannot get piece at empty position";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

    }

    /*
    * initializeBoard() unit tests
    * */

    @ParameterizedTest
    @CsvSource({
        "2, 0",
        "2, 7",
        "5, 0",
        "5, 7",
        "3, 3",
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

}
