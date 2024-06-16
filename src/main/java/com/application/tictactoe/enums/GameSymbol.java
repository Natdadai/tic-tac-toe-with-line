package com.application.tictactoe.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameSymbol {
    X("X"), O("O");

    private final String symbol;

    public static GameSymbol getPlayerSymbol() {
        return GameSymbol.X;
    }

    public static GameSymbol getComputerSymbol() {
        return GameSymbol.O;
    }

    public static GameResult getGameResult(GameSymbol gameSymbol) {
        return gameSymbol == getPlayerSymbol() ? GameResult.PLAYER : GameResult.COMPUTER;
    }
}
