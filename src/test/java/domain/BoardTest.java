package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

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

}
