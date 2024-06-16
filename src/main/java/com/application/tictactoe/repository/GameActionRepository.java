package com.application.tictactoe.repository;

import com.application.tictactoe.model.Game;
import com.application.tictactoe.model.GameAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameActionRepository extends CrudRepository<GameAction, Integer> {
    GameAction findTopByGameOrderByTurnDesc(Game game);
}
