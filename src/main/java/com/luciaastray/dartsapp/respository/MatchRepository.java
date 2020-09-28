package com.luciaastray.dartsapp.respository;

import com.luciaastray.dartsapp.model.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
}
