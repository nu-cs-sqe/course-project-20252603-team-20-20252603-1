package ui;

import domain.piece.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;

public class MainView extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 1200;

    private BoardView boardView;
    private GameStatsView gameStatsView;
    private String player1Name;
    private String player2Name;
    private Locale locale;
    private ResourceBundle bundle;

    public MainView(String player1Name, String player2Name, Locale locale) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);

        configureMainView();
        addGameStatsView();
        addBoardView();

        setVisible(true);
        validate();
    }

    private void configureMainView() {

        setTitle(bundle.getString("guiTitle"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void addGameStatsView() {
        gameStatsView = new GameStatsView(player1Name, player2Name, locale);
        add(gameStatsView, BorderLayout.PAGE_START);
    }

    private void addBoardView() {
        boardView = new BoardView();
        add(boardView, BorderLayout.CENTER);
        boardView.setOnTurnChanged(turn -> {
            String white = bundle.getString("white");
            String black = bundle.getString("black");
            String currentPlayer = bundle.getString("currentPlayer");

            String name = (turn == Color.WHITE) ? white : black;

            String currentPlayerLabelString = MessageFormat.format("{0}: {1}",
                currentPlayer, name
            );

            gameStatsView.currentPlayerLabel.setText(currentPlayerLabelString);
        });
    }
}