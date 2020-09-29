package com.luciaastray.dartsapp.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luciaastray.dartsapp.model.score.Score;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="players")
public class Player implements Serializable {

    private static final long serialVersionUID = 8857226274311476209L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy="player")
    private List<Score> scores;

    public Player() {
    }

    public Player(UUID id, String name, List<Score> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getTotalScore(){
        return this.scores.stream().map(score-> score.getScore()).reduce(0L,(acc, val) -> acc + val);
    }
}
