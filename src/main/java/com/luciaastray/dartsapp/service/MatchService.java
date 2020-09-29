package com.luciaastray.dartsapp.service;

import com.luciaastray.dartsapp.model.match.Match;
import com.luciaastray.dartsapp.respository.MatchRepository;
import com.luciaastray.dartsapp.service.exceptions.ExceptionConstants;
import com.luciaastray.dartsapp.service.exceptions.MatchNotFound;
import com.luciaastray.dartsapp.service.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Transactional(readOnly = true)
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Match findById(String id){
        Assert.notNull(id, ExceptionConstants.REQUIRED_ID);
        Optional<Match> matchOptional = matchRepository.findById(UUIDUtils.parseUUID(id));
        return matchOptional.orElseThrow(() -> new MatchNotFound(id));
    }

    @Transactional
    public void create(Match match){
        UUID matchId = UUID.randomUUID();
        match.setId(matchId);
        match.getScores().forEach(score -> score.setMatchId(matchId));
        matchRepository.save(match);}
}
