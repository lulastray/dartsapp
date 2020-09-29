package com.luciaastray.dartsapp.controller;

import com.luciaastray.dartsapp.model.match.Match;
import com.luciaastray.dartsapp.mother.match.MatchMother;
import com.luciaastray.dartsapp.service.MatchService;
import com.luciaastray.dartsapp.service.exceptions.MatchNotFound;
import com.luciaastray.dartsapp.service.exceptions.NotValidUUID;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = MatchController.class)
public class MatchControllerTests extends AbstractControllerTest {

    @MockBean
    private MatchService service;

    @Captor
    private ArgumentCaptor<Match> matchCaptor;

    @Test
    void should_get_all() throws Exception {

        // GIVEN
        List<Match> matches = MatchMother.randomList(10);

        // WHEN && THEN
        when(service.findAll()).thenReturn(matches);
        assertResponse("/match/all", HttpMethod.GET, "", HttpStatus.OK, bodyAsJson(matches));
    }

    @Test
    void should_get_one() throws Exception {

        // GIVEN
        Match match = MatchMother.random();

        // WHEN && THEN
        when(service.findById(match.getId().toString())).thenReturn(match);
        assertResponse("/match/".concat(match.getId().toString()), HttpMethod.GET, "", HttpStatus.OK, bodyAsJson(match));
    }

    @Test
    void should_fail_get_one_if_not_found() throws Exception {

        // GIVEN
        String id = UUID.randomUUID().toString();

        // WHEN && THEN
        when(service.findById(id.toString())).thenThrow(new MatchNotFound(id));
        assertResponse("/match/".concat(id), HttpMethod.GET, "", HttpStatus.NOT_FOUND, "");
    }

    @Test
    void should_fail_get_one_if_uuid_not_valid() throws Exception {

        // GIVEN
        String id = "wrong";

        // WHEN && THEN
        when(service.findById(id)).thenThrow(new NotValidUUID(id));
        assertResponse("/match/".concat(id), HttpMethod.GET, "", HttpStatus.BAD_REQUEST, "");
    }

    @Test
    void should_create() throws Exception {

        // GIVEN
        Match match = MatchMother.random();

        // WHEN && THEN
        assertResponse("/match/new", HttpMethod.POST, bodyAsJson(match), HttpStatus.OK, "");
        verify(service, times(1)).create(matchCaptor.capture());
        assertMatch(match, matchCaptor.getValue());
    }

    private void assertMatch(Match expected, Match actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMatchDate(), actual.getMatchDate());
        assertEquals(expected.getTotalRounds(), actual.getTotalRounds());
    }
}
