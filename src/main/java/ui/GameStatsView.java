package ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStatsView extends JPanel {

    private static final int FONT_SIZE_HEADER = 30;
    private static final int FONT_SIZE_LABEL = 20;
    private static final Color NU_PURPLE = new Color(104, 76, 150);

    public JLabel currentPlayerLabel;

    public GameStatsView(String player1Name, String player2Name) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(NU_PURPLE);

        JLabel playerInfoLabel = new JLabel("Player Information");
        playerInfoLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_HEADER));
        playerInfoLabel.setForeground(Color.WHITE);

        JLabel player1Label = new JLabel(
                "\t\t\tPlayer 1: " + player1Name + " (Team: " + "White" + ")");
        player1Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player1Label.setForeground(Color.WHITE);

        JLabel player2Label = new JLabel(
                "\t\t\tPlayer 2: " + player2Name + " (Team: " + "Black" + ")");
        player2Label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_LABEL));
        player2Label.setForeground(Color.WHITE);

        //FIXME: hardcoded for now, but should be updated dynamically
        currentPlayerLabel = new JLabel("Current Player: " + player1Name);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_HEADER));
        currentPlayerLabel.setForeground(Color.WHITE);

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
    }
}