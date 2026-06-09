package ui;

import java.awt.Color;
import java.awt.Font;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStatsView extends JPanel {

    private static final int FONT_SIZE_HEADER = 30;
    private static final int FONT_SIZE_LABEL = 20;
    private static final Color NU_PURPLE = new Color(104, 76, 150);
    private Locale locale;

    public JLabel currentPlayerLabel;
    public JLabel whiteTimerLabel;
    public JLabel blackTimerLabel;
    private final String whiteLocale;
    private final String blackLocale;


    public GameStatsView(String player1Name, String player2Name, Locale locale, long initialTime) {
        this.locale = locale;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(NU_PURPLE);

        ResourceBundle labels = ResourceBundle.getBundle("labels/labels", locale);
        String playerInformation = labels.getString("playerInformation");
        JLabel playerInfoLabel = new JLabel(playerInformation);
        playerInfoLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_HEADER));
        playerInfoLabel.setForeground(Color.WHITE);

        String player = labels.getString("player");
        String team = labels.getString("team");
        this.whiteLocale = labels.getString("white");
        this.blackLocale = labels.getString("black");

        String player1Header = MessageFormat.format("\t\t\t{0} 1: {1} ({2}: {3})",
                player, player1Name, team, this.whiteLocale);

        JLabel player1Label = new JLabel(player1Header);
        player1Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player1Label.setForeground(Color.WHITE);

        String player2Header = MessageFormat.format("\t\t\t{0} 2: {1} ({2}: {3})",
                player, player2Name, team, this.blackLocale);

        JLabel player2Label = new JLabel(player2Header);

        player2Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player2Label.setForeground(Color.WHITE);

        // FIXME: hardcoded for now, but should be updated dynamically
        String currentPlayer = labels.getString("currentPlayer");
        String currentPlayerLabelStr = MessageFormat.format("{0}: {1}", currentPlayer, player1Name);
        currentPlayerLabel = new JLabel(currentPlayerLabelStr);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_HEADER));
        currentPlayerLabel.setForeground(Color.WHITE);

        long seconds = initialTime / 1000;
        String formatted = String.format("%02d:%02d", seconds / 60, seconds % 60);
        
        String whiteTimerText = MessageFormat.format("{0}: {1}", this.whiteLocale, formatted);
        whiteTimerLabel = new JLabel(whiteTimerText);
        whiteTimerLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        whiteTimerLabel.setForeground(java.awt.Color.WHITE);

        String blackTimerText = MessageFormat.format("{0}: {1}", this.blackLocale, formatted);
        blackTimerLabel = new JLabel(blackTimerText);
        blackTimerLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        blackTimerLabel.setForeground(java.awt.Color.WHITE);

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
        add(whiteTimerLabel);
        add(blackTimerLabel);
    }

    public void updateTimer(domain.piece.Color color, long timeRemainingMillis) {
        long seconds = timeRemainingMillis / 1000;
        String formatted = String.format("%02d:%02d", seconds / 60, seconds % 60);
        if (color == domain.piece.Color.WHITE) {
            String newWhiteTimerText = MessageFormat.format("{0}: {1}", this.whiteLocale, formatted);
            whiteTimerLabel.setText(newWhiteTimerText);
        } else {
            String newBlackTimerText = MessageFormat.format("{0}: {1}", this.blackLocale, formatted);
            blackTimerLabel.setText(newBlackTimerText);
        }
    }
}