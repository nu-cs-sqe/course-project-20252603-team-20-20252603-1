package domain;

public class Position {
    public Position(int row, int col) {
        if (row < 1 || row > 8) {
            throw new IllegalArgumentException("Row must be between 1 and 8");
        }
        if (col < 1 || col > 8) {
            throw new IllegalArgumentException("Col must be between 1 and 8");
        }
    }
}
