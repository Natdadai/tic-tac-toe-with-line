package com.application.tictactoe.service;

import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;

public interface GameService {
    void initGame(Player player, int boardSize);

    Game getLatestGameByPlayerAndNotEnded(Player player);

    Game getLatestGameByPlayer(Player player);

    void updateGame(Game game);

    String[][] getBoard(Game game);

    boolean checkGameEnd(Game game);
}
