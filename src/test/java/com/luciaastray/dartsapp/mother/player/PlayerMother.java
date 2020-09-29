package com.luciaastray.dartsapp.mother.player;

import com.luciaastray.dartsapp.model.player.Player;
import com.luciaastray.dartsapp.mother.MotherCreator;
import com.luciaastray.dartsapp.mother.score.ScoreMother;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerMother {

    public static final Player random() {
        return new Player(
                null,
                MotherCreator.random().name().fullName(),
                new ArrayList<>()
        );
    }

    public static final Player randomWithScores() {
        Player player = random();
        player.setScores(ScoreMother.randomListForPlayer(player, 10));
        return player;
    }
}
