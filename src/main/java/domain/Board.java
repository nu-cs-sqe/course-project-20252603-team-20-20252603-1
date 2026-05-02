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
        for(int col = 0; col < 8; ++col) {
            _squares[0][col] = new Piece(PieceType.ROOK, Color.WHITE);
            _squares[1][col] = new Piece(PieceType.PAWN, Color.WHITE);
        }

        // black pieces
        for(int col = 0; col < 8; ++col) {
            _squares[7][col] = new Piece(PieceType.ROOK, Color.BLACK);
            _squares[6][col] = new Piece(PieceType.PAWN, Color.BLACK);
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