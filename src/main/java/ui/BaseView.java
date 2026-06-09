package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public abstract class BaseView extends JFrame {

    protected static final int WINDOW_SIZE      = 600;
    protected static final int INSET_SIZE       = 10;
    protected static final int FONT_SIZE_TITLE  = 28;
    protected static final int FONT_SIZE_LABEL  = 16;
    protected static final int FONT_SIZE_BUTTON = 20;
    protected static final int BORDER_THICKNESS = 2;
    protected static final Color NU_PURPLE      = new Color(104, 76, 150);

    public BaseView(String title) {
        setTitle(title);
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    protected JPanel createBasePanel() {
        JPanel panel = new JPanel(new java.awt.GridBagLayout());
        panel.setBackground(NU_PURPLE);
        return panel;
    }

    protected java.awt.GridBagConstraints createGbc() {
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    protected JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));
        label.setForeground(Color.WHITE);
        return label;
    }

    protected JLabel createBodyLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        label.setForeground(Color.WHITE);
        return label;
    }

    protected JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_BUTTON));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDER_THICKNESS, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    protected JTextField createStyledTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_LABEL));
        return field;
    }

    protected void transitionTo(JFrame next) {
        next.setVisible(true);
        setVisible(false);
        dispose();
    }
}