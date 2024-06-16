package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.enums.GameResult;
import com.application.tictactoe.enums.GameState;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.GameAction;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.repository.GameRepository;
import com.application.tictactoe.service.GameService;
import com.application.tictactoe.service.PlayerService;
import com.application.tictactoe.utils.TicTacToeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private PlayerService playerService;
    private GameRepository gameRepository;

    @Override
    public void initGame(Player player, int boardSize) {
        Game game = new Game(
                player,
                boardSize
        );

        // Add game to player
        player.getGames().add(game);
        playerService.updatePlayer(player);
    }

    @Override
    @Transactional
    public boolean checkGameEnd(Game game) {
        String[][] board = game.getBoard();
        int boardSize = game.getBoardSize();

        // Check if the game has a gameResul
        GameResult gameResult = TicTacToeUtils.checkGameResult(board, boardSize);
        if (gameResult != null) {
            game.setGameResult(gameResult);
            game.setState(GameState.ENDED);
            updateGame(game);
            return true;
        }

        return false;
    }

    @Override
    public Game getLatestGameByPlayerAndNotEnded(Player player) {
        return gameRepository.findTopByPlayerAndStateNotOrderByCreatedAtDesc(player, GameState.ENDED);
    }

    @Override
    @Transactional
    public void updateGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public boolean isPlayerTurn(Game game) {
        GameAction lastAction = game.getGameActions()
                .stream()
                .max(Comparator.comparingInt(GameAction::getTurn))
                .orElse(null);
        return lastAction == null || lastAction.getTurn() % 2 == 0;
    }
}
