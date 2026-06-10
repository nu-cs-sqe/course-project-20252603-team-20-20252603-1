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
    private GameConfig config; 

    public MainView(GameConfig config) {
        this.config = config;
        this.player1Name = config.getPlayer1Name();
        this.player2Name = config.getPlayer2Name();
        this.locale = config.getLocale();
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);
        this.timeControl = config.getTimeControl();

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
        gameStatsView = new GameStatsView(this.config);
        add(gameStatsView, BorderLayout.PAGE_START);
    }

    private void addBoardView() {
        boardController = new BoardController(this, this, locale);
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
        gameStatsView.updateTimer(color, timeRemainingMillis);
    }

    @Override
    public void onTimeout(Color color) {
        boardController.handleTimeout(color);
    }
}