package com.BalisAssessment.GameHandling.controller;

import com.BalisAssessment.GameHandling.exception.NotFoundException;
import com.BalisAssessment.GameHandling.model.Match;
import com.BalisAssessment.GameHandling.model.Odd;
import com.BalisAssessment.GameHandling.repository.MatchRepository;
import com.BalisAssessment.GameHandling.repository.OddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OddController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private OddRepository oddRepository;

    //Get all odds by match id
    @GetMapping("/matches/{matchId}/odds")
    public ResponseEntity<List<Odd>> getAllOddsByMatchId(@PathVariable(value = "matchId") Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new NotFoundException("Not found Match with id: " + matchId);
        }

        List<Odd> odds = oddRepository.findByMatchId(matchId);
        return new ResponseEntity<>(odds, HttpStatus.OK);
    }

    //Get odd by id
    @GetMapping("/odds/{id}")
    public ResponseEntity<Odd> getOddById(@PathVariable(value = "id") Long id) {
        Odd odd = oddRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Odd with id: " + id));

        return new ResponseEntity<>(odd, HttpStatus.OK);
    }

    //Creation of new odd
    @PostMapping("/matches/{matchId}/odds")
    public ResponseEntity<Odd> createOdd(@PathVariable(value = "matchId") Long matchId,
                                                 @RequestBody Odd oddRequest) {
        Odd odd = matchRepository.findById(matchId).map(match -> {
            oddRequest.setMatch(match);
            return oddRepository.save(new Odd(oddRequest.getMatch(),
                    oddRequest.getSpecifier(),
                    oddRequest.getOdd()));
        }).orElseThrow(() -> new NotFoundException("Not found Match with id: " + matchId));

        return new ResponseEntity<>(odd, HttpStatus.CREATED);
    }

    //Update or replace of odd
    @PutMapping("/odds/{id}")
    public ResponseEntity<Odd> updateOdd(@PathVariable("id") long id, @RequestBody Odd oddRequest) {
        Odd odd = oddRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Match with id: " +  id));

        odd.setOdd(oddRequest.getOdd());
        odd.setSpecifier(oddRequest.getSpecifier());

        return new ResponseEntity<>(oddRepository.save(odd), HttpStatus.OK);
    }

    //Delete odd
    @DeleteMapping("/odds/{id}")
    public ResponseEntity<HttpStatus> deleteOdd(@PathVariable("id") long id) {
        oddRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete all odds by match id
    @DeleteMapping("/matches/{matchId}/odds")
    public ResponseEntity<List<Odd>> deleteAllOddsOfMatch(@PathVariable(value = "matchId") Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new NotFoundException("Not found Match with id: " + matchId);
        }

        oddRepository.deleteByMatchId(matchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
