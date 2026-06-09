package ui;

import java.awt.GridBagConstraints;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TimeControlView extends BaseView {

    private static final long FIVE_MINUTES = 300000L;
    private static final long TEN_MINUTES = 600000L;
    private static final long ONE_HOUR = 3600000L;

    private final ResourceBundle bundle;
    private final String player1Name;
    private final String player2Name;
    private final Locale locale;

    public TimeControlView(String player1Name, String player2Name, Locale locale) {
        super(ResourceBundle.getBundle("labels/labels", locale).getString("timeControlTitle"));
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);
        buildScreen();
    }

    private void buildScreen() {
        GridBagConstraints gbc = createGbc();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JPanel panel = createBasePanel();
        panel.add(createTitleLabel(bundle.getString("promptForTimeControl")), gbc);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 1;
        JButton fiveMin = createStyledButton("5:00");
        fiveMin.addActionListener(e -> startGame(FIVE_MINUTES));
        panel.add(fiveMin, gbc);

        gbc.gridy = 2;
        JButton tenMin = createStyledButton("10:00");
        tenMin.addActionListener(e -> startGame(TEN_MINUTES));
        panel.add(tenMin, gbc);

        gbc.gridy = 3;
        JButton oneHour = createStyledButton("60:00");
        oneHour.addActionListener(e -> startGame(ONE_HOUR));
        panel.add(oneHour, gbc);

        add(panel);
    }

    private void startGame(long timeControl) {
        transitionTo(new MainView(player1Name, player2Name, locale, timeControl));
    }
}