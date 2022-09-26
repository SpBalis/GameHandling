package com.BalisAssessment.GameHandling.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    private java.sql.Date match_date;

    private java.sql.Time match_time;
    private String team_a;

    private String team_b;
    private  int sport;

    public Match(){

    }

    public Match(String description, java.sql.Date match_date, java.sql.Time match_time, String team_a, String team_b, int sport) {
        this.description = description;
        this.match_date = match_date;
        this.match_time = match_time;
        this.team_a = team_a;
        this.team_b = team_b;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getMatch_date() {
        return match_date;
    }

    public void setMatch_date(java.sql.Date match_date) {
        this.match_date = match_date;
    }

    public java.sql.Time getMatch_time() {
        return match_time;
    }

    public void setMatch_time(java.sql.Time match_time) {
        this.match_time = match_time;
    }

    public String getTeam_a() {
        return team_a;
    }

    public void setTeam_a(String team_a) {
        this.team_a = team_a;
    }

    public String getTeam_b() {
        return team_b;
    }

    public void setTeam_b(String team_b) {
        this.team_b = team_b;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }
}
