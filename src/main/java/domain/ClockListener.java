package domain;

import domain.piece.Color;

public interface ClockListener {
    void onTimerTick(Color color, long timeRemaining);

    void onTimeout(Color color);
}
