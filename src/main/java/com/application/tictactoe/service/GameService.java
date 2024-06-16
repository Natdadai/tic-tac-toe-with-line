package com.application.tictactoe.service;

import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;

public interface GameService {
    void initGame(Player player, int boardSize);

    boolean checkGameEnd(Game game);

    Game getLatestGameByPlayerAndNotEnded(Player player);

    void updateGame(Game game);

    boolean isPlayerTurn(Game game);
}
