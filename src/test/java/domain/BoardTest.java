package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

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

        boolean actual = board.isEmpty(position);

        assertTrue(actual);
    }

    /*
    * getPieceAt() unit tests
    * */

    @Test
    public void GetPieceAt_NewBoardRow0Col0_ThrowsException() {
        Board board = new Board();
        Position position = EasyMock.createMock(Position.class);

        Exception exception = assertThrows(
            NoSuchElementException.class,
            () -> board.getPieceAt(position)
        );

        assertTrue(board.isEmpty(position));

        String expected = "Cannot get piece at empty position";
        String actual = exception.getMessage();
        assertEquals(expected, actual);

    }

}
