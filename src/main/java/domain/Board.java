package domain;

import domain.piece.Piece;

import java.util.NoSuchElementException;

public class Board {

    public void initializeBoard() {

    }

    public boolean isEmpty(Position position) {
        return true;
    }

    public Piece getPieceAt(Position position) {
        throw new NoSuchElementException("Cannot get piece at empty position");
    }
}
