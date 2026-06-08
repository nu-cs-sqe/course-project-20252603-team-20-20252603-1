package ui;

import domain.Position;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardView extends JPanel implements BoardChangeListener {
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 100; // size of each square in pixels
    private final java.awt.Color lightSquareColor = new java.awt.Color(240, 217, 181);
    private final java.awt.Color darkSquareColor = new java.awt.Color(181, 136, 99);
    private final java.awt.Color selectedSquareColor = new java.awt.Color(164, 149, 195);
    private final java.awt.Color validMoveColor = new java.awt.Color(100, 200, 100, 160);

    private Map<PieceType, Image> whitePieceImages;
    private Map<PieceType, Image> blackPieceImages;

    private final BoardController boardController;
    private Consumer<Color> onTurnChanged = turn -> {
    };

    public BoardView() {
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        loadPieceImages();
        this.boardController = new BoardController(this);
        addMouseListener(new BoardMouseListener());
    }

    @Override
    protected final void finalize() throws Throwable {
    }

    public void setOnTurnChanged(Consumer<Color> callback) {
        this.onTurnChanged = callback;
    }

    @Override
    public void onBoardChanged() {
        onTurnChanged.accept(boardController.getCurrentTurn());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawSelectedSquare(g);
        drawValidMoves(g);
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                java.awt.Color squareColor = (row + col) % 2 == 0
                        ? lightSquareColor
                        : darkSquareColor;
                g.setColor(squareColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void loadPieceImages() {

        whitePieceImages = new HashMap<>();
        blackPieceImages = new HashMap<>();

        loadOnePieceImage(PieceType.PAWN, Color.WHITE, "images/white_pawn.png");
        loadOnePieceImage(PieceType.ROOK, Color.WHITE, "images/white_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, Color.WHITE, "images/white_knight.png");
        loadOnePieceImage(PieceType.BISHOP, Color.WHITE, "images/white_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, Color.WHITE, "images/white_queen.png");
        loadOnePieceImage(PieceType.KING, Color.WHITE, "images/white_king.png");

        loadOnePieceImage(PieceType.PAWN, Color.BLACK, "images/black_pawn.png");
        loadOnePieceImage(PieceType.ROOK, Color.BLACK, "images/black_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, Color.BLACK, "images/black_knight.png");
        loadOnePieceImage(PieceType.BISHOP, Color.BLACK, "images/black_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, Color.BLACK, "images/black_queen.png");
        loadOnePieceImage(PieceType.KING, Color.BLACK, "images/black_king.png");
    }

    private void loadOnePieceImage(PieceType type, Color color, String imagePath) {
        InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (color.equals(Color.BLACK)) {
            blackPieceImages.put(type, image);
        } else {
            this.whitePieceImages.put(type, image);
        }
    }

    private void drawPieces(Graphics g) {

        Piece[][] boardSnapshot = boardController.getBoardSnapshot();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = boardSnapshot[row][col];
                if (piece != null) {
                    Image pieceImage = piece.getColor() == Color.WHITE
                            ? whitePieceImages.get(piece.getPieceType())
                            : blackPieceImages.get(piece.getPieceType());
                    g.drawImage(pieceImage, col * TILE_SIZE, row * TILE_SIZE,
                            TILE_SIZE, TILE_SIZE, this);
                }
            }
        }
    }

    private void drawSelectedSquare(Graphics g) {
        Optional<Position> selection = boardController.getSelectedPosition();
        if (selection.isEmpty()) {
            return;
        }
        Position position = selection.get();
        g.setColor(selectedSquareColor);
        g.fillRect(
                (position.getCol() - 1) * TILE_SIZE,
                (position.getRow() - 1) * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE);
    }

    private void drawValidMoves(Graphics g) {
        g.setColor(validMoveColor);
        for (Position pos : boardController.getValidMoves()) {
            g.fillRect(
                    (pos.getCol() - 1) * TILE_SIZE,
                    (pos.getRow() - 1) * TILE_SIZE,
                    TILE_SIZE,
                    TILE_SIZE);
        }
    }

    private class BoardMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;
            if (Position.validPosition(row + 1, col + 1)) {
                boardController.handleSquareClick(new Position(row + 1, col + 1));
            }
        }
    }

}