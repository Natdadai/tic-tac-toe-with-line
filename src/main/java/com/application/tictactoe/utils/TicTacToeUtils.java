package com.application.tictactoe.utils;

import com.application.tictactoe.enums.GameResult;
import com.application.tictactoe.enums.GameSymbol;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TicTacToeUtils {
    public String getBoardString(String[][] board) {
        List<String> rows = new ArrayList<>();
        for (String[] row : board) {
            String rowString = String.join(" | ", row);
            rows.add(rowString.replace("null", " "));
        }
        return String.join("\n" + "----".repeat(board[0].length) + "\n", rows);
    }

    public GameResult checkGameResult(String[][] board, int boardSize) {
        if (checkGameEnd(board)) {
            return GameResult.DRAW;
        }

        GameSymbol winner = checkRows(board, boardSize);
        if (winner != null) {
            return GameSymbol.getGameResult(winner);
        }

        winner = checkCols(board, boardSize);
        if (winner != null) {
            return GameSymbol.getGameResult(winner);
        }

        winner = checkDiagonals(board, boardSize);
        if (winner != null) {
            return GameSymbol.getGameResult(winner);
        }

        return null;
    }

    private boolean checkGameEnd(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private GameSymbol checkRows(String[][] board, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][0] != null) {
                boolean win = true;
                for (int j = 1; j < boardSize; j++) {
                    if (board[i][j] == null || !board[i][j].equals(board[i][0])) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return GameSymbol.valueOf(board[i][0]);
                }
            }
        }
        return null;
    }

    private GameSymbol checkCols(String[][] board, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            if (board[0][i] != null) {
                boolean win = true;
                for (int j = 1; j < boardSize; j++) {
                    if (board[j][i] == null || !board[j][i].equals(board[0][i])) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return GameSymbol.valueOf(board[0][i]);
                }
            }
        }
        return null;
    }

    private GameSymbol checkDiagonals(String[][] board, int boardSize) {
        if (board[0][0] != null) {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][i] == null || !board[i][i].equals(board[0][0])) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return GameSymbol.valueOf(board[0][0]);
            }
        }
        if (board[0][boardSize - 1] != null) {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][boardSize - i - 1] == null || !board[i][boardSize - i - 1].equals(board[0][boardSize - 1])) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return GameSymbol.valueOf(board[0][boardSize - 1]);
            }
        }
        return null;
    }
}
