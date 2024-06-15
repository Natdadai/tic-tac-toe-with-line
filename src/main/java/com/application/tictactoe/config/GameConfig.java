package com.application.tictactoe.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    @Getter
    private static int maxBoardSize;

    @Value("${game.max.board.size:3}")
    public void setMaxBoardSize(int maxBoardSize) {
        GameConfig.maxBoardSize = maxBoardSize;
    }

}