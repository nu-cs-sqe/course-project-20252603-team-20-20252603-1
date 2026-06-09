package domain;

import domain.piece.Color;

public class ChessClock {
    private long whiteTimeRemaining;
    private long blackTimeRemaining;
    
    public ChessClock(long initialTime, ClockListener listener){
        this.whiteTimeRemaining = initialTime;
        this.blackTimeRemaining = initialTime;
    }

    public long getTimeRemaining(Color color){
        return (color == Color.WHITE ) ? this.whiteTimeRemaining : this.blackTimeRemaining;
    }
}
