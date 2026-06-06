package ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainView extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 1200;

    private BoardView boardView;
    private GameStatsView gameStatsView;
    private String player1Name;
    private String player2Name;

    public MainView(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        configureMainView();
        addGameStatsView();
        addBoardView();

        setVisible(true);
        validate();
    }

    private void configureMainView() {
        setTitle("Our Chess Game GUI!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void addGameStatsView() {
        gameStatsView = new GameStatsView(player1Name, player2Name);
        add(gameStatsView, BorderLayout.PAGE_START);
    }

    private void addBoardView() {
        boardView = new BoardView();
        add(boardView, BorderLayout.CENTER);
    }
}