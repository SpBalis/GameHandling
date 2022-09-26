package com.BalisAssessment.GameHandling.repository;

import com.BalisAssessment.GameHandling.model.Odd;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OddRepository extends JpaRepository<Odd, Long> {

    List<Odd> findByMatchId(Long matchId);

    @Transactional
    void deleteByMatchId(long matchId);
}
