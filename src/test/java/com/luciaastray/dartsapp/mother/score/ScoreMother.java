package com.luciaastray.dartsapp.mother.score;

import com.luciaastray.dartsapp.model.player.Player;
import com.luciaastray.dartsapp.model.score.Score;
import com.luciaastray.dartsapp.mother.MotherCreator;
import com.luciaastray.dartsapp.mother.player.PlayerMother;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreMother {

    public static final Score random() {
        return new Score(
                null,
                UUID.randomUUID(),
                PlayerMother.randomWithScores(),
                MotherCreator.random().number().numberBetween(1L, 50L)
        );
    }

    public static final Score randomForMatch(UUID matchId) {
        return new Score(
                null,
                matchId,
                PlayerMother.randomWithScores(),
                MotherCreator.random().number().numberBetween(1L, 50L)
        );
    }

    public static final Score randomForPlayer(Player player) {
        return new Score(
                null,
                UUID.randomUUID(),
                player,
                MotherCreator.random().number().numberBetween(1L, 50L)
        );
    }

    public static final List<Score> randomList(int num) {
        List<Score> scores = new ArrayList<>();

        for(int i=0; i < num; i++) {
            scores.add(random());
        }

        return scores;
    }

    public static final List<Score> randomListForMatch(UUID matchId, int num) {
        List<Score> scores = new ArrayList<>();

        for(int i=0; i < num; i++) {
            scores.add(randomForMatch(matchId));
        }

        return scores;
    }

    public static final List<Score> randomListForPlayer(Player player, int num) {
        List<Score> scores = new ArrayList<>();

        for(int i=0; i < num; i++) {
            scores.add(randomForPlayer(player));
        }

        return scores;
    }

}
