package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeView extends JFrame {

    private static final int WINDOW_SIZE = 600;
    private static final int INSET_SIZE = 10;
    private static final int FONT_SIZE_TITLE = 28;
    private static final int FONT_SIZE_LABEL = 16;
    private static final int FONT_SIZE_BUTTON = 20;
    private static final int TEXT_FIELD_COLUMNS = 20;
    private static final int BUTTON_ROW = 3;
    private static final int BORDER_THICKNESS = 2;
    private static final Color NU_PURPLE = new Color(104, 76, 150);

    private JTextField player1NameField;
    private JTextField player2NameField;
    private Locale locale;
    private ResourceBundle bundle;

    public WelcomeView(Locale locale) {
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);

        setTitle(bundle.getString("welcomeTitle"));
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createWelcomeScreen();
    }

    private void createWelcomeScreen() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(NU_PURPLE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Welcome message
        String welcomeMessage = bundle.getString("welcomeMessage");
        JLabel welcomeLabel = new JLabel(welcomeMessage, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        welcomePanel.add(welcomeLabel, gbc);

        // Player 1 Name Input
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        String player1NamePrompt = bundle.getString("player1NamePrompt");
        JLabel player1NameLabel = new JLabel(player1NamePrompt);
        player1NameLabel.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        player1NameLabel.setForeground(Color.WHITE);
        welcomePanel.add(player1NameLabel, gbc);
        player1NameField = new JTextField(TEXT_FIELD_COLUMNS);
        player1NameField.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        gbc.gridx = 1;
        gbc.gridy = 1;
        welcomePanel.add(player1NameField, gbc);

        // Player 2 Name Input
        gbc.gridx = 0;
        gbc.gridy = 2;
        String player2NamePrompt = bundle.getString("player2NamePrompt");
        JLabel player2NameLabel = new JLabel(player2NamePrompt);
        player2NameLabel.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        player2NameLabel.setForeground(Color.WHITE);
        welcomePanel.add(player2NameLabel, gbc);
        player2NameField = new JTextField(TEXT_FIELD_COLUMNS);
        player2NameField.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        gbc.gridx = 1;
        gbc.gridy = 2;
        welcomePanel.add(player2NameField, gbc);

        // Start Game Button
        String startGame = bundle.getString("startGame");
        JButton startGameButton = new JButton(startGame);
        startGameButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_BUTTON));
        startGameButton.setOpaque(true);
        startGameButton.setBackground(Color.WHITE);
        startGameButton.setForeground(Color.BLACK);
        startGameButton.setBorder(
                BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS, true));
        startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        startGameButton.addActionListener(e -> {
            // Get player names
            String player1Name = player1NameField.getText().trim();
            String player2Name = player2NameField.getText().trim();

            // FIXME: perform input validation on player names

            // Hide the welcome screen
            setVisible(false);
            dispose(); // Dispose of this frame to free up resources

            // Create and show the main game screen
            MainView mainScreen = new MainView(player1Name, player2Name, locale);
            mainScreen.setVisible(true);
        });
        gbc.gridx = 0;
        gbc.gridy = BUTTON_ROW;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        welcomePanel.add(startGameButton, gbc);

        add(welcomePanel);
    }
}