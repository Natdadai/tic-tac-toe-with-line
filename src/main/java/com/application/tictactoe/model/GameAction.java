package com.application.tictactoe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_actions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private Player player;

    @NonNull
    @ManyToOne
    private Game game;

    @NonNull
    private String move;

    @NonNull
    private Integer turn;

    @NonNull
    private LocalDateTime createdAt;
}