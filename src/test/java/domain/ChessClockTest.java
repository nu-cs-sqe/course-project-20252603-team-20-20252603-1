package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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

    @Test
    public void Constructor_10Min_ClockCreated() {
        long TEN_MIN_TIME_CONTROL = 600000;

        ClockListener listener = EasyMock.createMock(ClockListener.class);
        
        ChessClock clock = new ChessClock(600000, listener);

        assertEquals(clock.getTimeRemaining(Color.WHITE), TEN_MIN_TIME_CONTROL);
        assertEquals(clock.getTimeRemaining(Color.BLACK), TEN_MIN_TIME_CONTROL);
    }

    @Test
    public void Constructor_1Hr_ClockCreated() {
        long ONE_HR_TIME_CONTROL = 3600000;

        ClockListener listener = EasyMock.createMock(ClockListener.class);
        
        ChessClock clock = new ChessClock(3600000, listener);

        assertEquals(clock.getTimeRemaining(Color.WHITE), ONE_HR_TIME_CONTROL);
        assertEquals(clock.getTimeRemaining(Color.BLACK), ONE_HR_TIME_CONTROL);
    }

    @Test
    public void Constructor_NullListener_Exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ChessClock(300000, null);
        });
        assertEquals("No valid listener passed", exception.getMessage());
    }

    @Test 
    public void Start_NewClock() {
        ClockListener listener = EasyMock.createMock(ClockListener.class);
        ChessClock clock = new ChessClock(3600000, listener);

        clock.start();
        assertTrue(clock.isRunning());

    }

    @Test 
    public void Start_RunningClock() {
        ClockListener listener = EasyMock.createMock(ClockListener.class);
        ChessClock clock = new ChessClock(3600000, listener);

        clock.start();
        clock.start();
        assertTrue(clock.isRunning());

    }

    @Test 
    public void Stop_RunningClock() {
        ClockListener listener = EasyMock.createMock(ClockListener.class);
        ChessClock clock = new ChessClock(3600000, listener);

        clock.start();
        clock.stop();
        assertFalse(clock.isRunning());

    }

    @Test 
    public void Stop_ClockNotStarted() {
        ClockListener listener = EasyMock.createMock(ClockListener.class);
        ChessClock clock = new ChessClock(3600000, listener);

        clock.stop();
        assertFalse(clock.isRunning());

    }

    @Test 
    public void SwitchClock_WhiteHighTime() {
        ClockListener listener = EasyMock.createMock(ClockListener.class);
        ChessClock clock = new ChessClock(300000, listener);
        clock.start();
        clock.setTime(299999, Color.WHITE);
        clock.switchClock();
        assertEquals(clock.getActiveColor(), Color.BLACK);
        assertEquals(clock.getTimeRemaining(Color.WHITE), 299999);
        assertEquals(clock.getTimeRemaining(Color.BLACK), 300000);

    }
}
