package com.luciaastray.dartsapp.model.match;

import com.luciaastray.dartsapp.model.score.Score;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="matches")
public class Match implements Serializable {

    private static final long serialVersionUID = 175563824200384680L;

    @Id
    private UUID id;

    @OneToMany(mappedBy="matchId", cascade= CascadeType.ALL )
    private List<Score> scores;

    private LocalDateTime matchDate;

    private Long totalRounds;

    public Match() {
    }

    public Match(UUID id, List<Score> scores, LocalDateTime matchDate, Long totalRounds) {
        this.id = id;
        this.scores = scores;
        this.matchDate = matchDate;
        this.totalRounds = totalRounds;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Long getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(Long totalRounds) {
        this.totalRounds = totalRounds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
