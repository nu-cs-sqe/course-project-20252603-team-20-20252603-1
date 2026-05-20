package domain;

import java.util.Objects;

public class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        if (row < 1 || row > 8) {
            throw new IllegalArgumentException("Row must be between 1 and 8");
        }
        if (col < 1 || col > 8) {
            throw new IllegalArgumentException("Col must be between 1 and 8");
        }
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return this.row == otherPosition.row && this.col == otherPosition.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
