package domain;

import domain.piece.Color;

public class ChessClock {
    private long whiteTimeRemaining;
    private long blackTimeRemaining;
    private boolean running;
    
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
    }
    
    public boolean isRunning() {
        return this.running;
    }
}
