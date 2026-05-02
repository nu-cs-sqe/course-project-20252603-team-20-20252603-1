package domain;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Board {

    private Piece[][] _squares = new Piece[8][8];

    private Optional<Piece> _pieceAt(Position position) {
        return Optional.ofNullable(_squares[position.getRow()][position.getCol()]);
    }

    public void initializeBoard() {
        // white pieces
        for (int row = 0; row < 2; ++row) {
            for(int col = 0; col < 8; ++col) {
                _squares[row][col] = new Piece(PieceType.ROOK, Color.WHITE);
            }
        }

        // black pieces
        for (int row = 6; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                _squares[row][col] = new Piece(PieceType.ROOK, Color.WHITE);
            }
        }
    }

    public boolean isEmpty(Position position) {
        return _pieceAt(position).isEmpty();
    }

    public Piece getPieceAt(Position position) {
        return _pieceAt(position).orElseThrow(
            () -> new NoSuchElementException("Cannot get piece at empty position")
        );
    }
}