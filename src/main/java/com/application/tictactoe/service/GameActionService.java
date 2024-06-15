package com.application.tictactoe.service;

import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Player;

public interface GameActionService {
    void makeMove(Player player, GameSymbol gameSymbol, int row, int col);
}
