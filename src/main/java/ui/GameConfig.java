package ui;

import java.util.Locale;

public class GameConfig {
    private String player1Name;
    private String player2Name;
    private Locale locale;
    private long timeControl;

    private GameConfig() {}

    public String getPlayer1Name() { 
        return player1Name; 
    }

    public String getPlayer2Name() { 
        return player2Name; 
    }
    
    public Locale getLocale() { 
        return locale; 
    }

    public long getTimeControl() { 
        return timeControl; 
    }

    public static class Builder {
        private final GameConfig config = new GameConfig();

        public Builder player1Name(String name) {
            config.player1Name = name;
            return this;
        }

        public Builder player2Name(String name) {
            config.player2Name = name;
            return this;
        }

        public Builder locale(Locale locale) {
            config.locale = locale;
            return this;
        }

        public Builder timeControl(long timeControl) {
            config.timeControl = timeControl;
            return this;
        }

        public GameConfig build() {
            return config;
        }
    }
}