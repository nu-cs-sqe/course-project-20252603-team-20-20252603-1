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
            | "BLACK" | 4       | 8       | "WHITE"        | "QUEEN"      | 4          | 1          | "BISHOP"     | 3          | 7          |
    # | "WHITE" | 4       | 4       | "BLACK"        | "PAWN"       | 5          | 3          | "PAWN"       | 5          | 5          |


    Scenario Outline: <player> is not in check
        Given the game is in progress
        And the <player> King is at row <kingRow> col <kingCol>
        And a <opposingPlayer> <attackingPiece> is at row <attackerRow> col <attackerCol>
        When I check if the player is in check
        Then the player is not in check

        Examples:
            | player  | kingRow | kingCol | opposingPlayer | attackingPiece | attackerRow | attackerCol |
            | "WHITE" | 1       | 4       | "BLACK"        | "ROOK"         | 4           | 8           |
            | "BLACK" | 4       | 1       | "WHITE"        | "QUEEN"        | 8           | 4           |
            | "WHITE" | 8       | 4       | "BLACK"        | "BISHOP"       | 4           | 1           |
            | "BLACK" | 4       | 8       | "WHITE"        | "KNIGHT"       | 1           | 4           |
            | "WHITE" | 8       | 4       | "BLACK"        | "PAWN"         | 4           | 1           |

    Scenario: game has not started
        Given an empty chessboard
        When I check if either player is in check
        Then I am told I cannot cannot do this


    Scenario Outline: <player> is blocking check
        Given the game is in progress
        And the <player> King is at row <kingRow> col <kingCol>
        And a <player> <blocker> is at row <blockRow> col <blockCol>
        And a <opposingPlayer> <attacker> is at row <attackRow> col <attackCol>
        When I check if the player is in check
        Then the player is not in check

        Examples:
            | player  | kingRow | kingCol | opposingPlayer | blocker  | blockRow | blockCol | attacker | attackRow | attackCol |
            | "WHITE" | 1       | 4       | "BLACK"        | "ROOK"   | 6        | 4        | "ROOK"   | 8         | 4         |
            | "BLACK" | 4       | 1       | "WHITE"        | "BISHOP" | 4        | 4        | "QUEEN"  | 4         | 8         |
            | "WHITE" | 8       | 4       | "BLACK"        | "QUEEN"  | 7        | 3        | "BISHOP" | 5         | 1         |
            | "BLACK" | 4       | 8       | "WHITE"        | "KNIGHT" | 4        | 4        | "ROOK"   | 4         | 1         |
            | "WHITE" | 8       | 4       | "BLACK"        | "PAWN"   | 6        | 4        | "ROOK"   | 1         | 4         |
            | "BLACK" | 1       | 8       | "WHITE"        | "PAWN"   | 1        | 4        | "ROOK"   | 1         | 1         |
            | "WHITE" | 1       | 1       | "BLACK"        | "KNIGHT" | 4        | 1        | "QUEEN"  | 8         | 1         |
            | "BLACK" | 8       | 8       | "WHITE"        | "PAWN"   | 8        | 4        | "ROOK"   | 8         | 1         |
            | "WHITE" | 1       | 8       | "BLACK"        | "KNIGHT" | 4        | 8        | "QUEEN"  | 8         | 8         |
