package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @Test
    public void IsEmpty_NewBoardRow0Col0_IsTrue() {
        Board board = new Board();
        Position position = EasyMock.createMock(Position.class);

        boolean actual = board.isEmpty(position);

        assertTrue(actual);
    }

}
