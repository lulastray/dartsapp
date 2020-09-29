package com.luciaastray.dartsapp.model.score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luciaastray.dartsapp.model.player.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="scores")
public class Score implements Serializable {

    private static final long serialVersionUID = -6989074239925222925L;

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "match_id")
    private UUID matchId;

    @JsonIgnoreProperties("scores")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="player_id")
    private Player player;

    private Long score;

    public Score() {
    }

    public Score(UUID id, UUID matchId, Player player, Long score) {
        this.id = id;
        this.matchId = matchId;
        this.player = player;
        this.score = score;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
