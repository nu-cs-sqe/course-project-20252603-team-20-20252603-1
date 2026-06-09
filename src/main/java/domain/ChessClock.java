package domain;

import domain.piece.Color;
import javax.swing.Timer;

public class ChessClock {
    private long whiteTimeRemaining;
    private long blackTimeRemaining;
    private boolean running;
    private Color activeColor = Color.WHITE;
    private Timer timer;
    private ClockListener listener;
    private static final int MILLISECONDS_IN_SECOND = 1000;


    public ChessClock(long initialTime, ClockListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("No valid listener passed");
        }
        this.whiteTimeRemaining = initialTime;
        this.blackTimeRemaining = initialTime;
        this.listener = listener;
    }

    @Override
    protected final void finalize() throws Throwable {
    }

    public long getTimeRemaining(Color color) {
        if (color == Color.WHITE) {
            return this.whiteTimeRemaining;
        } else if (color == Color.BLACK) {
            return this.blackTimeRemaining;
        } else {
            throw new IllegalArgumentException("No color passed");
        }
    }

    public void start() {
        this.running = true;
        this.timer = new Timer(MILLISECONDS_IN_SECOND, e -> tick());
        this.timer.start();
    }

    public void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void switchClock() {
        if (this.activeColor == Color.WHITE) {
            this.activeColor = Color.BLACK;
        } else {
            this.activeColor = Color.WHITE;
        }
    }

    // package-private for testing
    void tick() {
        if (this.activeColor == Color.WHITE) {
            this.whiteTimeRemaining -= MILLISECONDS_IN_SECOND;
            if (this.whiteTimeRemaining <= 0) {
                stop();
                listener.onTimeout(Color.WHITE);
                return;
            }
        } else {
            this.blackTimeRemaining -= MILLISECONDS_IN_SECOND;
            if (this.blackTimeRemaining <= 0) {
                stop();
                listener.onTimeout(Color.BLACK);
                return;
            }
        } 
        listener.onTimerTick(this.activeColor, getTimeRemaining(this.activeColor));
    }

    // For testing purposes
    void setTime(long time, Color color) {
        if (color == Color.WHITE) {
            this.whiteTimeRemaining = time;
        } else {
            this.blackTimeRemaining = time;
        }
    }

    // For testing purposes
    Color getActiveColor() {
        return this.activeColor;
    }
}
