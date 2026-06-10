Feature: Detect Check
    In order to win the game,
    as a player,
    I want to know when I am in check.

    Scenario Outline: <player> is in check by one piece
        Given the game is in progress
        And the <player> King is at row <kingRow> col <kingCol>
        And a <opposingPlayer> <attackingPiece> is at row <attackerRow> col <attackerCol>
        When I check if the player is in check
        Then the player is in check

        Examples:
            | player  | kingRow | kingCol | opposingPlayer | attackingPiece | attackerRow | attackerCol |
            | "WHITE" | 1       | 4       | "BLACK"        | "ROOK"         | 8           | 4           |
            | "BLACK" | 8       | 4       | "WHITE"        | "QUEEN"        | 1           | 4           |
            | "WHITE" | 4       | 1       | "BLACK"        | "BISHOP"       | 8           | 5           |
            | "BLACK" | 4       | 8       | "WHITE"        | "KNIGHT"       | 2           | 7           |
            | "WHITE" | 7       | 1       | "BLACK"        | "ROOK"         | 4           | 1           |
    # | "BLACK" | 5       | 7       | "WHITE"        | "PAWN"         | 4           | 8           |

    Scenario Outline: <player> is in check by more than one piece
        Given the game is in progress
        And the <player> King is at row <kingRow> col <kingCol>
        And a <opposingPlayer> <attackPiece1> is at row <attackRow1> col <attackCol1>
        And a <opposingPlayer> <attackPiece2> is at row <attackRow2> col <attackCol2>
        When I check if the player is in check
        Then the player is in check

        Examples:
            | player  | kingRow | kingCol | opposingPlayer | attackPiece1 | attackRow1 | attackCol1 | attackPiece2 | attackRow2 | attackCol2 |
            | "WHITE" | 1       | 4       | "BLACK"        | "ROOK"       | 8          | 4          | "BISHOP"     | 2          | 5          |
            | "BLACK" | 8       | 4       | "WHITE"        | "QUEEN"      | 1          | 4          | "KNIGHT"     | 7          | 2          |
            | "WHITE" | 4       | 1       | "BLACK"        | "ROOK"       | 4          | 8          | "QUEEN"      | 5          | 2          |


