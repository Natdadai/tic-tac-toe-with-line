package com.application.tictactoe.utils;

import com.application.tictactoe.config.GameConfig;
import com.application.tictactoe.enums.GameResult;
import com.application.tictactoe.enums.GameSymbol;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MinimaxUtils {
    private static final int MAX_DEPTH = GameConfig.getMaxDepth();

    public int[] findBestMove(String[][] board, int depth, boolean isMaximizing) {
        if (TicTacToeUtils.checkGameResult(board, board.length) != null || depth == MAX_DEPTH) {
            return new int[]{evaluate(board, depth), -1, -1};
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[]{bestScore, -1, -1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    board[i][j] = isMaximizing ? GameSymbol.getComputerSymbol().name() : GameSymbol.getPlayerSymbol().name();
                    int[] currentScore = findBestMove(board, depth + 1, !isMaximizing);
                    board[i][j] = null;
                    if (isMaximizing && currentScore[0] > bestScore) {
                        bestScore = currentScore[0];
                        bestMove = new int[]{bestScore, i, j};
                    } else if (!isMaximizing && currentScore[0] < bestScore) {
                        bestScore = currentScore[0];
                        bestMove = new int[]{bestScore, i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    private int evaluate(String[][] board, int depth) {
        GameResult gameResult = TicTacToeUtils.checkGameResult(board, board.length);
        if (gameResult == GameResult.COMPUTER) {
            return 10 - depth;
        } else if (gameResult == GameResult.PLAYER) {
            return depth - 10;
        } else {
            return 0;
        }
    }
}
