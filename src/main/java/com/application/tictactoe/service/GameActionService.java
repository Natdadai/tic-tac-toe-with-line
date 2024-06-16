package com.application.tictactoe.service;

import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;

public interface GameActionService {
    void makeMove(Player player, Game game, GameSymbol gameSymbol, int row, int col);

    void botMove(Game game);

    boolean checkPositionOccupied(Game game, int row, int col);

    boolean checkPositionOutOfBounds(Game game, int row, int col);
}
