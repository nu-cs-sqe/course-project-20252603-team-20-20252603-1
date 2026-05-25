package domain;

import java.util.Objects;

public class Position {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 8;

    private final int row;
    private final int col;

    private static boolean coordinateInBounds(int coord) {
        return (coord >= LOWER_BOUND) && (coord <= UPPER_BOUND);
    }

    public Position(int row, int col) {
        if (!coordinateInBounds(row)) {
            throw new IllegalArgumentException("Row must be between 1 and 8");
        }
        if (!coordinateInBounds(col)) {
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

    public static boolean validPosition(int row, int col) {
        return coordinateInBounds(row) && coordinateInBounds(col);
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
