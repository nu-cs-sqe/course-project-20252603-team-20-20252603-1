package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    void constructor_rowAndColAtLowerBound_createsSuccessfully() {
        Position position = new Position(1, 1);
        assertNotNull(position);
    }

    @Test
    void constructor_rowAndColAtUpperBound_createsSuccessfully() {
        Position position = new Position(8, 8);
        assertNotNull(position);
    }

    @Test
    void constructor_rowBelowLowerBound_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Position(0, 1));
    }

    @Test
    void constructor_rowAboveUpperBound_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Position(9, 1));
    }

    @Test
    void constructor_colBelowLowerBound_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Position(1, 0));
    }

    @Test
    void constructor_colAboveUpperBound_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Position(1, 9));
    }

    @Test
    void getRow_positionCreatedWithRowOne_returnsOne() {
        Position position = new Position(1, 1);
        assertEquals(1, position.getRow());
    }

    @Test
    void getRow_positionCreatedWithRowEight_returnsEight() {
        Position position = new Position(8, 2);
        assertEquals(8, position.getRow());
    }
}
