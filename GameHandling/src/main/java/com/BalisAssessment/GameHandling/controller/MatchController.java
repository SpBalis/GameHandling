package com.BalisAssessment.GameHandling.controller;

import com.BalisAssessment.GameHandling.exception.NotFoundException;
import com.BalisAssessment.GameHandling.model.Match;
import com.BalisAssessment.GameHandling.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    //Getting all matches
    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches(@RequestParam(required = false) String description){

        List<Match> matches = new ArrayList<>();

        if(description == null){
            matchRepository.findAll().forEach(matches::add);
        } else{
            matchRepository.findByDescription(description).forEach(matches::add);
        }

        if(matches.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    //Search by match id
    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") long id){
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Match with id: " + id));

        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    //Creates new match
    @PostMapping("/matches")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match newMatch = matchRepository
                .save(new Match(match.getDescription(),
                        match.getMatch_date(),
                        match.getMatch_time(),
                        match.getTeam_a(),
                        match.getTeam_b(),
                        match.getSport()));

        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    //Update or replace match
    @PutMapping("/matches/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") long id, @RequestBody Match match) {
        Match newMatch = matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Match with id: " + id));

        newMatch.setDescription(match.getDescription());
        newMatch.setMatch_date(match.getMatch_date());
        newMatch.setMatch_time(match.getMatch_time());
        newMatch.setTeam_a(match.getTeam_a());
        newMatch.setTeam_b(match.getTeam_b());
        newMatch.setSport(match.getSport());

        return new ResponseEntity<>(matchRepository.save(newMatch), HttpStatus.OK);
    }

    //Delete a match
    @DeleteMapping("/matches/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") long id) {
        matchRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/matches")
    public ResponseEntity<HttpStatus> deleteAllMatches() {
        matchRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
