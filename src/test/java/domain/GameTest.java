package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void Game_NewGame_Null() {
        
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                new Game(null);
            });

	    assertEquals("No valid board passed", exception.getMessage());


    }

}
