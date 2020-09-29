package com.luciaastray.dartsapp.mother.match;

import com.luciaastray.dartsapp.model.match.Match;
import com.luciaastray.dartsapp.mother.MotherCreator;
import com.luciaastray.dartsapp.mother.score.ScoreMother;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchMother {

    public static final Match random() {
        UUID matchId = UUID.randomUUID();
        return new Match(
                matchId,
                ScoreMother.randomListForMatch(matchId, 10),
                MotherCreator.randomDate(),
                MotherCreator.random().number().numberBetween(1L, 50L)
        );
    }

    public static final List<Match> randomList(int num) {
        List<Match> matches = new ArrayList<>();

        for(int i=0; i < num; i++) {
            matches.add(random());
        }

        return matches;
    }
}
