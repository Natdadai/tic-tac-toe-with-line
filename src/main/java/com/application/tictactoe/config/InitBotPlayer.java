package com.application.tictactoe.config;

import com.application.tictactoe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitBotPlayer {
    private final PlayerService playerService;

    @Bean
    public CommandLineRunner createBotPlayer() {
        return args -> playerService.createPlayer("bot", "Bot");
    }
}

