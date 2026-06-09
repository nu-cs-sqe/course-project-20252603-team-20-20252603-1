package ui;

import domain.ClockListener;
import domain.piece.Color;
import java.awt.BorderLayout;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JFrame;

public class MainView extends JFrame implements BoardChangeListener, ClockListener {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 1200;

    private BoardView boardView;
    private GameStatsView gameStatsView;
    private BoardController boardController;
    private String player1Name;
    private String player2Name;
    private Locale locale;
    private ResourceBundle bundle;
    private long timeControl;

    public MainView(String player1Name, String player2Name, Locale locale, long timeControl) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);
        this.timeControl = timeControl;

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
        gameStatsView = new GameStatsView(player1Name, player2Name, locale, timeControl);
        add(gameStatsView, BorderLayout.PAGE_START);
    }

    private void addBoardView() {
        boardController = new BoardController(this, this);
        boardController.startGame(timeControl);
        boardView = new BoardView(boardController);
        add(boardView, BorderLayout.CENTER);
    }

    @Override
    public void onBoardChanged() {
        Color turn = boardController.getCurrentTurn();
        String white = bundle.getString("white");
        String black = bundle.getString("black");
        String currentPlayer = bundle.getString("currentPlayer");

        String name = (turn == Color.WHITE) ? white : black;
        String currentPlayerLabelString = MessageFormat.format("{0}: {1}", currentPlayer, name);
        gameStatsView.currentPlayerLabel.setText(currentPlayerLabelString);
        boardView.repaint();
    }

    @Override
    public void onTimerTick(Color color, long timeRemainingMillis) {
        System.out.println("tick: " + color + " " + timeRemainingMillis);

        gameStatsView.updateTimer(color, timeRemainingMillis);
    }

    @Override
    public void onTimeout(Color color) {
        boardController.handleTimeout(color);
    }
}