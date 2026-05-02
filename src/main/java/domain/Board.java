package domain;

import domain.piece.Piece;

import java.util.NoSuchElementException;

public class Board {

    private boolean[][] _squares = new boolean[8][8];

    Board() {
        for (int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                _squares[row][col] = true;
            }
        }
    }

    public void initializeBoard() {
        // white pieces
        for (int row = 0; row < 2; ++row) {
            for(int col = 0; col < 8; ++col) {
                _squares[row][col] = false;
            }
        }

        // black pieces
        for (int row = 6; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                _squares[row][col] = false;
            }
        }
    }

    public boolean isEmpty(Position position) {
        return _squares[position.getRow()][position.getCol()];
    }

    public Piece getPieceAt(Position position) {
        throw new NoSuchElementException("Cannot get piece at empty position");
    }
}