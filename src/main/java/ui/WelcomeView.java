package ui;

import java.awt.GridBagConstraints;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WelcomeView extends BaseView {

    private static final int BUTTON_ROW = 3;
    private static final int TEXT_FIELD_COLUMNS = 20;

    private final ResourceBundle bundle;

    public WelcomeView(Locale locale) {
        super(ResourceBundle.getBundle("labels/labels", locale).getString("welcomeTitle"));
        this.bundle = ResourceBundle.getBundle("labels/labels", locale);
        buildScreen(locale);
    }

    private void buildScreen(Locale locale) {
        GridBagConstraints gbc = createGbc();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JPanel panel = createBasePanel();
        panel.add(createTitleLabel(bundle.getString("welcomeMessage")), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createBodyLabel(bundle.getString("player1NamePrompt")), gbc);
        JTextField player1Field = createStyledTextField(TEXT_FIELD_COLUMNS);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(player1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(createBodyLabel(bundle.getString("player2NamePrompt")), gbc);
        JTextField player2Field = createStyledTextField(TEXT_FIELD_COLUMNS);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(player2Field, gbc);

        JButton startButton = createStyledButton(bundle.getString("startGame"));
        startButton.addActionListener(e -> {
            String p1 = player1Field.getText().trim();
            String p2 = player2Field.getText().trim();
            // FIXME: perform input validation on player names
            transitionTo(new TimeControlView(p1, p2, locale));
        });
        gbc.gridx = 0;
        gbc.gridy = BUTTON_ROW;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(startButton, gbc);

        add(panel);
    }
}