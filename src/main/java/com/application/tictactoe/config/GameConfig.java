package com.application.tictactoe.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static java.lang.Math.max;

@Configuration
public class GameConfig {
    @Getter
    private static int maxBoardSize;
    @Getter
    private static int maxDepth;

    @Value("${game.max.board.size:3}")
    public void setMaxBoardSize(int maxBoardSize) {
        GameConfig.maxBoardSize = maxBoardSize;
    }

    @Value("${game.max.depth:3}")
    public void setMaxDepth(int maxDepth) {
        GameConfig.maxDepth = max(3, maxDepth);
    }

}