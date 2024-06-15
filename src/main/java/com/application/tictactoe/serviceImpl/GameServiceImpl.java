package com.application.tictactoe.serviceImpl;

import com.application.tictactoe.enums.GameState;
import com.application.tictactoe.enums.GameSymbol;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;
import com.application.tictactoe.repository.GameRepository;
import com.application.tictactoe.service.GameService;
import com.application.tictactoe.service.PlayerService;
import com.application.tictactoe.utils.TicTacToeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        gameRepository.save(game);

        // Add game to player
        player.getGames().add(game);
        playerService.updatePlayer(player);
    }

    @Override
    public String[][] getBoard(Game game) {
        int boardSize = game.getBoardSize();
        String[][] board = new String[boardSize][boardSize];
        game.getGameActions()
                .forEach(action -> board[action.getRow()][action.getCol()] = action.getGameSymbol().name());
        return board;
    }

    @Override
    public boolean checkGameEnd(Game game) {
        String[][] board = getBoard(game);
        int boardSize = game.getBoardSize();

        // Check if the game has a winner
        GameSymbol winner = TicTacToeUtils.checkWinner(board, boardSize);
        if (winner != null) {
            game.setState(GameState.ENDED);
            game.setWinner(winner);
            updateGame(game);
            return true;
        }

        // Check if the game is a draw
        if (TicTacToeUtils.checkGameDraw(game)) {
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
    public Game getLatestGameByPlayer(Player player) {
        return gameRepository.findTopByPlayerOrderByCreatedAtDesc(player);
    }

    @Override
    public void updateGame(Game game) {
        gameRepository.save(game);
    }
}
