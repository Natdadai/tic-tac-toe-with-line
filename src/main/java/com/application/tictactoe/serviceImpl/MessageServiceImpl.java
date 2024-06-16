package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.config.GameConfig;
import com.application.tictactoe.enums.GameResult;
import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.service.GameActionService;
import com.application.tictactoe.service.GameService;
import com.application.tictactoe.service.MessageService;
import com.application.tictactoe.service.PlayerService;
import com.application.tictactoe.utils.MessageUtils;
import com.application.tictactoe.utils.StringUtils;
import com.application.tictactoe.utils.TicTacToeUtils;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.webhook.model.MessageContent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private PlayerService playerService;
    private GameService gameService;
    private GameActionService gameActionService;
    private MessageUtils messageUtils;

    @Override
    @Transactional
    public List<Message> handleMessage(String lineUserId, MessageContent message) {
        Player player = playerService.findOrCreatePlayerByLineUserId(lineUserId);
        Game game = gameService.getLatestGameByPlayerAndNotEnded(player);
        Collection<TextMessage> textMessages = new ArrayList<>();
        if (game != null) {
            handleGame(game, player, message, textMessages);
        } else {
            handleNonGame(player, message, textMessages);
        }
        return textMessages.isEmpty() ? null : new ArrayList<>(textMessages);
    }

    private void handleGame(Game game, Player player, MessageContent message, Collection<TextMessage> textMessages) {
        String text = ((TextMessageContent) message).text();
        if (!gameService.isPlayerTurn(game)) {
            textMessages.add(
                    messageUtils.getMessage("game.turn.invalid")
            );
            return;
        }

        // check if the move is valid
        if (StringUtils.isMoveFormatValid(text)) {
            String[] move = text.split(",");
            int row = Integer.parseInt(move[0]);
            int col = Integer.parseInt(move[1]);

            // Check if the position is out of bounds
            if (gameActionService.checkPositionOutOfBounds(game, row, col)) {
                textMessages.add(
                        messageUtils.getMessage("game.position.out.of.bound", game.getBoardSize() - 1)
                );
                return;
            }

            // Check if the position is occupied
            if (gameActionService.checkPositionOccupied(game, row, col)) {
                textMessages.add(
                        messageUtils.getMessage("game.position.occupied")
                );
                return;
            }

            // Player move
            gameActionService.makeMove(player, game, GameSymbol.getPlayerSymbol(), row, col);
            if (!gameService.checkGameEnd(game)) {
                // Bot move
                gameActionService.botMove(game);
            }
            if (!gameService.checkGameEnd(game)) {
                textMessages.add(new TextMessage(TicTacToeUtils.getBoardString(game.getBoard())));
                textMessages.add(new TextMessage("Your turn!"));
            } else {
                textMessages.add(new TextMessage(TicTacToeUtils.getBoardString(game.getBoard())));
                if (game.getGameResult() == GameResult.DRAW) {
                    textMessages.add(
                            messageUtils.getMessage("game.draw")
                    );
                } else {
                    textMessages.add(
                            messageUtils.getMessage("game.win", game.getGameResult())
                    );
                }
            }
        } else {
            textMessages.add(
                    messageUtils.getMessage("game.move.format.invalid", GameConfig.getMaxBoardSize())
            );
        }
    }

    private void handleNonGame(Player player, MessageContent message, Collection<TextMessage> textMessages) {
        String text = ((TextMessageContent) message).text();
        if (StringUtils.isNumeric(text)) {
            int boardSize = NumberUtils.parseNumber(text, Integer.class);
            if (boardSize < 3 || boardSize > GameConfig.getMaxBoardSize()) {
                textMessages.add(
                        messageUtils.getMessage("game.board.size.invalid", GameConfig.getMaxBoardSize())
                );
                return;
            }
            gameService.initGame(player, boardSize);
            textMessages.add(
                    messageUtils.getMessage("game.start", GameSymbol.getPlayerSymbol().name())
            );
        } else {
            textMessages.add(
                    messageUtils.getMessage("game.board.size.invalid", GameConfig.getMaxBoardSize())
            );
        }
    }
}
