package com.application.tictactoe.model;

import com.application.tictactoe.enums.GameState;
import com.application.tictactoe.enums.GameSymbol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private Player player;

    @Enumerated(EnumType.STRING)
    private GameSymbol winner;

    @OneToMany(mappedBy = "game")
    private Set<GameAction> gameActions;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GameState state = GameState.IN_PROGRESS;

    @NonNull
    private Integer boardSize;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime endedAt;
}