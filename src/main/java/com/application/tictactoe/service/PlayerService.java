package com.application.tictactoe.service;

import com.application.tictactoe.model.Player;

public interface PlayerService {
    Player findOrCreatePlayerByLineUserId(String lineUserId);

    void updatePlayer(Player player);

    void createPlayer(String lineUserId, String displayName);

    Player getBotPlayer();
}
