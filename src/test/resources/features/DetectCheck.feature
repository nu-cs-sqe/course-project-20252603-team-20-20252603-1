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

