package ui;

import java.util.Locale;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Locale locale = Locale.getDefault();
        Locale locale = new Locale("es", "ES");
        SwingUtilities.invokeLater(() -> {
            new WelcomeView(locale).setVisible(true);
        });
    }

}