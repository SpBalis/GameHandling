package com.BalisAssessment.GameHandling.repository;

import com.BalisAssessment.GameHandling.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Long> {

    List<Match> findByDescription(String description);
}
