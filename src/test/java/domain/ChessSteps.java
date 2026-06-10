package domain;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.piece.Queen;
import domain.piece.Rook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessSteps {
    Board board;
    Color player;
    boolean inCheck;

    private Piece createPiece(PieceType type, Color color) {
        switch (type) {
            case PAWN:
                return new Pawn(color);
            case KNIGHT:
                return new Knight(color);
            case KING:
                return new King(color);
            case BISHOP:
                return new Bishop(color);
            case ROOK:
                return new Rook(color);
            case QUEEN:
                return new Queen(color);
            default:
                throw new IllegalStateException("Unhandled piece type: " + type);
        }
    }

    @Given("the game is in progress")
    public void the_game_is_in_progress() {
        board = new Board();
    }

    @Given("the {string} King is at row {int} col {int}")
    public void the_king_is_at_row_col(String player, Integer kingRow, Integer kingCol) {
        Position position = new Position(kingRow, kingCol);
        this.player = Color.valueOf(player);
        board.placePieceAt(position, createPiece(PieceType.KING, this.player));
    }

    @Given("a {string} {string} is at row {int} col {int}")
    public void a_is_at_row_col(String color, String pieceType, Integer row, Integer col) {
        Position position = new Position(row, col);
        Piece piece = createPiece(PieceType.valueOf(pieceType), Color.valueOf(color));
        board.placePieceAt(position, piece);
    }

    @When("I check if the player is in check")
    public void i_check_if_the_player_is_in_check() {
        inCheck = board.isInCheck(this.player);
    }

    @Then("the player is in check")
    public void the_player_is_in_check() {
        assertTrue(inCheck);
    }

    @Then("the player is not in check")
    public void the_player_is_not_in_check() {
        assertFalse(inCheck);
    }

}
