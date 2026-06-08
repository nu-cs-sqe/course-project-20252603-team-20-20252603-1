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

    public GameStatsView(String player1Name, String player2Name, Locale locale) {
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
        String white = labels.getString("white");
        String black = labels.getString("black");

        String player1Header = MessageFormat.format("\t\t\t{0} 1: {1} ({2}: {3})",
                player, player1Name, team, white);

        JLabel player1Label = new JLabel(player1Header);
        player1Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player1Label.setForeground(Color.WHITE);

        String player2Header = MessageFormat.format("\t\t\t{0} 2: {1} ({2}: {3})",
                player, player2Name, team, black);

        JLabel player2Label = new JLabel(player2Header);

        player2Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player2Label.setForeground(Color.WHITE);

        // FIXME: hardcoded for now, but should be updated dynamically
        String currentPlayer = labels.getString("currentPlayer");
        String currentPlayerLabelStr = MessageFormat.format("{0}: {1}", currentPlayer, player1Name);
        currentPlayerLabel = new JLabel(currentPlayerLabelStr);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_HEADER));
        currentPlayerLabel.setForeground(Color.WHITE);

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
    }
}