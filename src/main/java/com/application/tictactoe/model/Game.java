package com.application.tictactoe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private Player player;

    @NonNull
    @ManyToOne
    private Player winner;

    @NonNull
    @OneToMany(mappedBy = "game")
    private Set<GameAction> gameActions;

    @NonNull
    private String board;

    @NonNull
    private Integer size;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private LocalDateTime endedAt;
}