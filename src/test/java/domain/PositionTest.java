package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PositionTest {
    @Test
    void constructor_rowAndColAtLowerBound_createsSuccessfully() {
        Position position = new Position(1, 1);
        assertNotNull(position);
    }

}
