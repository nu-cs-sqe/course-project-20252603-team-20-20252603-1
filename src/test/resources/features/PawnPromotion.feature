Feature: Pawn Promotion
    In order to win the game,
    as a player,
    I want to know when I am in check.

    Scenario Outline: <player> pawn promotes by moving to back rank
        Given the game is in progress
        And a <player> Pawn is at row <fromRow> col <pawnCol>
        When <player> moves the pawn to row <toRow> col <pawnCol>
        And <player> selects <promotionPiece> as the promotion piece
        Then the pawn is removed from row <toRow> col <pawnCol>
        And a <player> <promotionPiece> is placed at row <toRow> col <pawnCol>

        Examples:
            | player  | fromRow | toRow | pawnCol | promotionPiece |
            | "WHITE" | 7       | 8     | 1       | "QUEEN"        |
            | "BLACK" | 2       | 1     | 1       | "ROOK"         |
            | "WHITE" | 7       | 8     | 4       | "BISHOP"       |
            | "BLACK" | 2       | 1     | 4       | "KNIGHT"       |
            | "WHITE" | 7       | 8     | 8       | "ROOK"         |
            | "BLACK" | 2       | 1     | 8       | "QUEEN"        |

    Scenario Outline: <player> pawn promotes by capturing to back rank
        Given the game is in progress
        And a <player> Pawn is at row <fromRow> col <fromCol>
        And an <opponent> piece is at row <toRow> col <toCol>
        When <player> captures the piece at row <toRow> col <toCol>
        And <player> selects <promotionPiece> as the promotion piece
        Then the pawn is removed from row <fromRow> col <fromCol>
        And the <opponent> piece is removed from row <toRow> col <toCol>
        And a <player> <promotionPiece> is placed at row <toRow> col <toCol>

        Examples:
            | player  | opponent | fromRow | fromCol | toRow | toCol | promotionPiece |
            | "WHITE" | "BLACK"  | 7       | 1       | 8     | 2     | "QUEEN"        |
            | "BLACK" | "WHITE"  | 2       | 4       | 1     | 3     | "ROOK"         |
            | "WHITE" | "BLACK"  | 7       | 4       | 8     | 3     | "BISHOP"       |
            | "BLACK" | "WHITE"  | 2       | 1       | 1     | 2     | "KNIGHT"       |
            | "WHITE" | "BLACK"  | 7       | 8       | 8     | 7     | "ROOK"         |
            | "BLACK" | "WHITE"  | 2       | 8       | 1     | 7     | "QUEEN"        |

    Scenario Outline: <player> promoted <promotionPiece> moves correctly after promotion
        Given the game is in progress
        And a <player> Pawn is at row <fromRow> col <pawnCol>
        When <player> moves the pawn to row <toRow> col <pawnCol>
        And <player> selects <promotionPiece> as the promotion piece
        And <player> moves the <promotionPiece> from row <toRow> col <pawnCol> to row <moveRow> col <moveCol>
        Then the <player> <promotionPiece> is at row <moveRow> col <moveCol>

        Examples:
            | player  | fromRow | toRow | pawnCol | promotionPiece | moveRow | moveCol |
            | "WHITE" | 7       | 8     | 5       | "QUEEN"        | 8       | 1       |
            | "BLACK" | 2       | 1     | 6       | "ROOK"         | 8       | 6       |