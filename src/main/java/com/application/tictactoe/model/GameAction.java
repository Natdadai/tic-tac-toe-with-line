package com.application.tictactoe.model;

import com.application.tictactoe.enums.GameSymbol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_actions")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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
    private Integer turn;

    @NonNull
    private Integer row;

    @NonNull
    private Integer col;

    @NonNull
    @Enumerated(EnumType.STRING)
    private GameSymbol gameSymbol;

    @CreatedDate
    private LocalDateTime createdAt;
}