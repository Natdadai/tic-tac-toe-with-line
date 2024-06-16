package com.application.tictactoe.model;

import com.application.tictactoe.enums.GameResult;
import com.application.tictactoe.enums.GameState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    private Player player;

    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<GameAction> gameActions = new HashSet<>();

    @NonNull
    @Enumerated(EnumType.STRING)
    private GameState state = GameState.IN_PROGRESS;

    @NonNull
    private Integer boardSize;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime endedAt;

    public GameAction getLastGameAction() {

        return gameActions.stream().max(Comparator.comparing(GameAction::getTurn)).orElse(null);
    }

    public int getLastTurn() {
        return gameActions.size();
    }

    public String[][] getBoard() {
        String[][] board = new String[boardSize][boardSize];
        for (GameAction gameAction : gameActions) {
            board[gameAction.getRowIndex()][gameAction.getColIndex()] = gameAction.getGameSymbol().toString();
        }
        return board;
    }
}