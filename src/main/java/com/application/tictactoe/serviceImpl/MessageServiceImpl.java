package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.service.GameService;
import com.application.tictactoe.service.MessageService;
import com.application.tictactoe.service.PlayerService;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.webhook.model.MessageContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private PlayerService playerService;
    private GameService gameService;

    @Override
    public List<Message> handleMessage(String lineUserId, MessageContent message) {
        Player player = playerService.findOrCreatePlayerByLineUserId(lineUserId);
        Game game = gameService.getLatestGameByPlayerAndNotEnded(player);
        if (game == null) {
            return List.of();
        }
        return List.of();
    }
}
