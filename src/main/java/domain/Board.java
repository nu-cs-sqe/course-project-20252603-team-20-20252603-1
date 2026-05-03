package domain;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Board {

  private final Piece[][] squares = new Piece[8][8];

  private Optional<Piece> pieceAt(Position position) {
    return Optional.ofNullable(squares[position.getRow()][position.getCol()]);
  }

  public void initializeBoard() {
    // white pieces
    for (int col = 0; col < 8; ++col) {
      squares[0][col] = new Piece(PieceType.ROOK, Color.WHITE);
      squares[1][col] = new Piece(PieceType.PAWN, Color.WHITE);
    }

    // black pieces
    for (int col = 0; col < 8; ++col) {
      squares[7][col] = new Piece(PieceType.ROOK, Color.BLACK);
      squares[6][col] = new Piece(PieceType.PAWN, Color.BLACK);
    }
  }

  public boolean isEmpty(Position position) {
    return pieceAt(position).isEmpty();
  }

  public Piece getPieceAt(Position position) {
    return pieceAt(position).orElseThrow(
        () -> new NoSuchElementException("Cannot get piece at empty position")
    );
  }
}