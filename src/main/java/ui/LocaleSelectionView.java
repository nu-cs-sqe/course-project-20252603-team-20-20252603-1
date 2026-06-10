package ui;

import java.awt.GridBagConstraints;
import java.util.Locale;
import javax.swing.JPanel;

public class LocaleSelectionView extends BaseView {

    public LocaleSelectionView() {
        super("Select Language / Seleccionar Idioma");
        buildScreen();
    }

    private void buildScreen() {
        GridBagConstraints gbc = createGbc();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        
        JPanel panel = createBasePanel();
        panel.add(createTitleLabel("Select Language / Seleccionar Idioma"), gbc);

        gbc.gridy = 1;
        var enButton = createStyledButton("English (US)");
        enButton.addActionListener(e -> transitionTo(new WelcomeView(Locale.US)));
        panel.add(enButton, gbc);

        gbc.gridy = 2;
        var esButton = createStyledButton("Espanol (ES)");
        esButton.addActionListener(e -> transitionTo(new WelcomeView(new Locale("es", "ES"))));
        panel.add(esButton, gbc);

        add(panel);
    }
}