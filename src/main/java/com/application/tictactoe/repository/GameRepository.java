package com.application.tictactoe.repository;

import com.application.tictactoe.enums.GameState;
import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findTopByPlayerOrderByCreatedAtDesc (Player player);
    Game findTopByPlayerAndStateNotOrderByCreatedAtDesc(Player player, GameState state);
}