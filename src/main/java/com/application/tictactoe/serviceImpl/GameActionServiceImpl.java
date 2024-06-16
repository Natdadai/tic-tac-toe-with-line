package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.GameAction;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.service.GameActionService;
import com.application.tictactoe.service.PlayerService;
import com.application.tictactoe.utils.MinimaxUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GameActionServiceImpl implements GameActionService {
    private PlayerService playerService;

    @Override
    @Transactional
    public void makeMove(Player player, Game game, GameSymbol gameSymbol, int row, int col) {
        int turn = game.getLastTurn() + 1;

        GameAction newGameAction = new GameAction(player, game, turn, row, col, gameSymbol);

        // Add gameAction to game and player
        game.getGameActions().add(newGameAction);
        player.getGameActions().add(newGameAction);

        playerService.updatePlayer(player);
    }

    @Override
    @Transactional
    public void botMove(Game game) {
        int[] move = MinimaxUtils.findBestMove(game.getBoard(), 0, true);
        makeMove(playerService.getBotPlayer(), game, GameSymbol.getComputerSymbol(), move[1], move[2]);
    }
}
