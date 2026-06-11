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
            | "WHITE" |    7    |   8   |    1    |     "QUEEN"    |
            | "BLACK" |    2    |   1   |    1    |     "ROOK"     | 
            | "WHITE" |    7    |   8   |    4    |    "BISHOP"    |

