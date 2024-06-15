package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.enums.GameState;
import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.GameAction;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.repository.GameActionRepository;
import com.application.tictactoe.service.GameActionService;
import com.application.tictactoe.service.GameService;
import com.application.tictactoe.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GameActionServiceImpl implements GameActionService {
    private GameActionRepository gameActionRepository;
    private GameService gameService;
    private PlayerService playerService;

    @Override
    @Transactional
    public void makeMove(Player player, GameSymbol gameSymbol, int row, int col) {
        Game game = gameService.getLatestGameByPlayer(player);
        GameAction gameAction = gameActionRepository.findTopByGameOrderByTurnDesc(game);
        int turn = gameAction == null ? 1 : gameAction.getTurn() + 1;

        GameAction newGameAction = new GameAction(
                player,
                game,
                turn,
                row,
                col,
                gameSymbol
        );

        // Save game action
        gameActionRepository.save(newGameAction);

        // Add gameAction to game and player
        game.getGameActions().add(newGameAction);
        player.getGameActions().add(newGameAction);

        // Update game and player
        gameService.updateGame(game);
        playerService.updatePlayer(player);
    }

    private boolean checkMoveValid(Game game, int row, int col) {
        int boardSize = game.getBoardSize();
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize;
    }
}
