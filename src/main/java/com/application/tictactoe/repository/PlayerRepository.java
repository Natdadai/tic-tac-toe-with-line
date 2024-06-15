package com.application.tictactoe.repository;

import com.application.tictactoe.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    Player findByLineUserId(String lineUserId);
}