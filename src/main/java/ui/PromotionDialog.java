package ui;

import domain.piece.Color;
import domain.piece.PieceType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class PromotionDialog extends JDialog {
    private static final int BUTTON_SIZE = 80;
    private static final int BUTTON_PADDING = 10;
    private static final PieceType[] PROMOTION_OPTIONS = {
            PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT
    };

    private PieceType selectedPieceType;

    @Override
    protected final void finalize() throws Throwable {
    }

    public PromotionDialog(Frame parent, Color pawnColor, Locale locale) {
        super(parent, ResourceBundle.getBundle("labels/labels", locale)
                .getString("promotionTitle"), true);
        setLayout(new FlowLayout());
        String colorPrefix = (pawnColor == Color.WHITE) ? "white" : "black";
        for (PieceType type : PROMOTION_OPTIONS) {
            add(createPieceButton(colorPrefix, type));
        }
        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private JButton createPieceButton(String colorPrefix, PieceType type) {
        JButton button = new JButton(type.name());
        String imagePath = "images/" + colorPrefix + "_" + type.name().toLowerCase() + ".png";
        InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath);
        if (is != null) {
            try {
                Image img = ImageIO.read(is).getScaledInstance(
                        BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(img));
                button.setText("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        button.setPreferredSize(
                new Dimension(BUTTON_SIZE + BUTTON_PADDING, BUTTON_SIZE + BUTTON_PADDING));
        button.addActionListener(e -> {
            this.selectedPieceType = type;
            dispose();
        });
        return button;
    }

    public PieceType showAndGetResult() {
        setVisible(true);
        return selectedPieceType;
    }
}