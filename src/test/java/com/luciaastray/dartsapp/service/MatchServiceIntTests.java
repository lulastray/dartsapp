package com.luciaastray.dartsapp.service;

import com.luciaastray.dartsapp.model.match.Match;
import com.luciaastray.dartsapp.model.player.Player;
import com.luciaastray.dartsapp.model.score.Score;
import com.luciaastray.dartsapp.mother.match.MatchMother;
import com.luciaastray.dartsapp.respository.MatchRepository;
import com.luciaastray.dartsapp.service.exceptions.ExceptionConstants;
import com.luciaastray.dartsapp.service.exceptions.MatchNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Rollback
@Transactional
@SpringBootTest
public class MatchServiceIntTests {

    @Autowired
    private MatchService service;

    @Autowired
    private MatchRepository repository;

    @Autowired
    private EntityManager em;

    private List<Match> matches;

    @BeforeEach
    void setup() {
        matches = MatchMother.randomList(10);
        matches.forEach(match -> {
            em.persist(match);
            em.flush();
        });
    }

    @Test
    void should_find_all() {

        // WHEN
        List<Match> actual = service.findAll();

        // THEN
        assertEquals(matches.size(), actual.size());
        for(int i = 0; i < matches.size(); i++) {
            assertMatch(matches.get(i), actual.get(i));
        }
    }

    @Test
    void should_find_by_id() {

        // GIVEN
        Match expected = matches.get(0);

        // WHEN
        Match actual = service.findById(expected.getId().toString());

        // THEN
        assertMatch(expected, actual);
    }

    @Test
    void should_fail_find_by_id_if_not_found() {

        // GIVEN
        UUID id = UUID.randomUUID();

        // WHEN && THEN
        Exception e = assertThrows(MatchNotFound.class, () -> {
            service.findById(id.toString());
        });
        assertEquals(String.format(ExceptionConstants.MATCH_NOT_FOUND, id), e.getMessage());
    }

    @Test
    void should_fail_find_by_id_is_null() {

        // WHEN && THEN
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            service.findById(null);
        });
        assertEquals(ExceptionConstants.REQUIRED_ID, e.getMessage());
    }

    @Test
    void should_create_a_match() {

        // GIVEN
        int oldSize = matches.size();
        Match newMatch = MatchMother.random();

        // WHEN
        service.create(newMatch);

        // THEN
        assertEquals(oldSize + 1, service.findAll().size());
        Match createdMatch = service.findById(newMatch.getId().toString());
        assertEquals(newMatch.getId(), createdMatch.getId());
        assertEquals(newMatch.getMatchDate(), createdMatch.getMatchDate());
        assertEquals(newMatch.getTotalRounds(), createdMatch.getTotalRounds());
        assertEquals(newMatch.getScores().size(), createdMatch.getScores().size());
    }

    private void assertMatch(Match expected, Match actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMatchDate(), actual.getMatchDate());
        assertEquals(expected.getTotalRounds(), actual.getTotalRounds());
        for(int i = 0; i < expected.getScores().size(); i++) {
            assertScore(expected.getScores().get(i), actual.getScores().get(i));
        }
    }

    private void assertScore(Score expected, Score actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMatchId(), actual.getMatchId());
        assertEquals(expected.getScore(), actual.getScore());
        assertPlayer(expected.getPlayer(), actual.getPlayer());
    }

    private void assertPlayer(Player expected, Player actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getTotalScore(), actual.getTotalScore());
    }
}
