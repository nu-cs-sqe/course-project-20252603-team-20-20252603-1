package domain;

import domain.piece.Color;
import domain.piece.King;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.piece.Rook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class PawnPromotion {
    ChessSteps steps;
    Position fromPosition;
    Position toPosition;
    Position movePosition;
    PieceType selected;
    Color opponent;

    public PawnPromotion(ChessSteps steps) {
        this.steps = steps;
    }

    @Given("a {string} Pawn is at row {int} col {int}")
    public void a_pawn_is_at_row_col(String player, Integer row, Integer col) {
        this.fromPosition = new Position(row, col);
        this.steps.player = Color.valueOf(player);
        this.steps.board.placePieceAt(fromPosition, new Pawn(this.steps.player));
    }

    @Given("an {string} piece is at row {int} col {int}")
    public void an_piece_is_at_row_col(String opponent, Integer row, Integer col) {
        Color oppColor = Color.valueOf(opponent);
        Position opponentPosition = new Position(row, col);
        this.steps.board.placePieceAt(opponentPosition, new Rook(oppColor));
    }

    @When("{string} moves the pawn to row {int} col {int}")
    public void moves_the_pawn_to_row_col(String player, Integer row, Integer col) {
        // Silence locateKing exception
        Position kingPosition = new Position(4, 4);
        this.steps.board.placePieceAt(kingPosition, new King(this.steps.player));

        this.toPosition = new Position(row, col);
        this.steps.board.movePiece(this.fromPosition, this.toPosition);
    }

    @When("{string} captures the piece at row {int} col {int}")
    public void captures_the_piece_at_row_col(String player, Integer row, Integer col) {
        // Silence locateKing exception
        Position kingPosition = new Position(4, 4);
        this.steps.board.placePieceAt(kingPosition, new King(this.steps.player));

        this.toPosition = new Position(row, col);
        this.steps.board.movePiece(this.fromPosition, this.toPosition);
    }

    @When("{string} selects {string} as the promotion piece")
    public void selects_as_the_promotion_piece(String player, String promotionPiece) {
        this.selected = PieceType.valueOf(promotionPiece);
        this.steps.board.promotePawn(this.toPosition, selected);
    }

    @When("{string} moves the {string} from row {int} col {int} to row {int} col {int}")
    public void moves_the_from_row_col_to_row_col(String player, String promotionPiece, Integer toRow, Integer pawnCol,
            Integer moveRow, Integer moveCol) {
        this.movePosition = new Position(moveRow, moveCol);
        this.steps.board.movePiece(this.toPosition, this.movePosition);
    }

    @Then("the pawn is removed from row {int} col {int}")
    public void the_pawn_is_removed_from_row_col(Integer int1, Integer int2) {
        Piece pieceAtDest = this.steps.board.getPieceAt(this.toPosition);
        assertFalse(pieceAtDest.getPieceType() == PieceType.PAWN);
    }

    @Then("the {string} piece is removed from row {int} col {int}")
    public void the_piece_is_removed_from_row_col(String string, Integer int1, Integer int2) {
        Piece pieceAtDest = this.steps.board.getPieceAt(this.toPosition);
        assertFalse((pieceAtDest.getPieceType() == PieceType.ROOK)
                && (pieceAtDest.getColor() == opponent));
    }

    @Then("a {string} {string} is placed at row {int} col {int}")
    public void a_is_placed_at_row_col(String string, String string2, Integer int1, Integer int2) {
        Piece pieceAtDest = this.steps.board.getPieceAt(this.toPosition);
        assertTrue(pieceAtDest.getPieceType() == this.selected);
    }

    @Then("the {string} {string} is at row {int} col {int}")
    public void the_is_at_row_col(String player, String promotionPiece, Integer moveRow, Integer moveCol) {
        Piece pieceMoved = this.steps.board.getPieceAt(this.movePosition);
        assertTrue((pieceMoved.getPieceType() == this.selected) 
            && (pieceMoved.getColor() == this.steps.player));
    }

}
