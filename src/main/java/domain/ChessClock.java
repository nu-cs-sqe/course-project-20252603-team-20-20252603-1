package domain;

import domain.piece.Color;

public class ChessClock {
    private long whiteTimeRemaining;
    private long blackTimeRemaining;
    private boolean running;
    private Color activeColor;

    public ChessClock(long initialTime, ClockListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("No valid listener passed");
        }
        this.whiteTimeRemaining = initialTime;
        this.blackTimeRemaining = initialTime;
    }
    
    @Override
    protected final void finalize() throws Throwable {
    }

    public long getTimeRemaining(Color color) {
        return (color == Color.WHITE) ? this.whiteTimeRemaining : this.blackTimeRemaining;
    }

    public void start() {
        this.running = true;
        this.activeColor = Color.WHITE;
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
        } else { this.activeColor = Color.WHITE; }
    }

    // For testing purposes
    void setTime(long time,Color color) {
        if (color == Color.WHITE) {
            this.whiteTimeRemaining = time;
        } else { this.blackTimeRemaining = time; }
    }

    // For testing purposes
    Color getActiveColor() {
        return this.activeColor;
    }
}
