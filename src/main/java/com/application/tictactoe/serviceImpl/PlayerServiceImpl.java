package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.model.Player;
import com.application.tictactoe.repository.PlayerRepository;
import com.application.tictactoe.service.PlayerService;
import com.linecorp.bot.messaging.client.MessagingApiClient;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final MessagingApiClient messagingApiClient;
    private PlayerRepository playerRepository;

    @Override
    @SneakyThrows
    public Player findOrCreatePlayerByLineUserId(String lineUserId) {
        Player player = playerRepository.findByLineUserId(lineUserId);
        if (player == null) {
            player = messagingApiClient.getProfile(lineUserId)
                .thenApply(profile -> new Player(lineUserId, profile.body().displayName()))
                .thenApplyAsync(playerRepository::save)
                .get();
        }
        return player;
    }

    @Override
    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}