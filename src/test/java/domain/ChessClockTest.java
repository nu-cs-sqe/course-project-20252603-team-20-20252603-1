package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.easymock.EasyMock;
import domain.piece.Color;
 

public class ChessClockTest {

    @Test
    public void Constructor_5Min_ClockCreated() {
        long FIVE_MIN_TIME_CONTROL = 300000;

        ClockListener listener = EasyMock.createMock(ClockListener.class);
        
        ChessClock clock = new ChessClock(300000, listener);

        assertEquals(clock.getTimeRemaining(Color.WHITE), FIVE_MIN_TIME_CONTROL);
        assertEquals(clock.getTimeRemaining(Color.BLACK), FIVE_MIN_TIME_CONTROL);
    }
}
