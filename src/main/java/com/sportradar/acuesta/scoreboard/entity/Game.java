package com.sportradar.acuesta.scoreboard.entity;

import org.apache.commons.lang3.StringUtils;

import com.sportradar.acuesta.scoreboard.exception.GameException;

/**
 * Represents a game in our scoreboard
 * 
 * @author acuesta
 *
 */
public class Game implements Cloneable{

    /**
     * Home team name
     */
    private String homeTeam;

    /**
     * Away team name
     */
    private String awayTeam;

    /**
     * Score for the home team
     */
    private int homeScore;

    /**
     * Score for the away team
     */
    private int awayScore;

    /**
     * Creation time for this game
     */
    private long creationTime;

    /**
     * Creates a game and initalize its values. Every game starts with 0-0 score.
     * Every game has a creation time
     * 
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @throws GameException If home or away teams name are empty
     */
    public Game(String homeTeam, String awayTeam) throws GameException {
	if (StringUtils.isBlank(homeTeam)) {
	    throw new GameException(GameException.HOME_TEAM_NAME_MANDATORY);
	}
	if (StringUtils.isBlank(awayTeam)) {
	    throw new GameException(GameException.AWAY_TEAM_NAME_MANDATORY);
	}

	this.homeTeam = homeTeam;
	this.awayTeam = awayTeam;
	this.homeScore = 0;
	this.awayScore = 0;
	this.creationTime = System.nanoTime();
    }    

    /**
     * Returns the home team name
     * 
     * @return Home team name
     */
    public String getHomeTeam() {
	return homeTeam;
    }

    /**
     * Returns the home team name
     * 
     * @return Home team name
     */
    public String getAwayTeam() {
	return awayTeam;
    }

    /**
     * Returns the home team score
     * 
     * @return Home team score
     */
    public int getHomeScore() {
	return homeScore;
    }

    /**
     * Sets the home team score
     * 
     * @param homeScore Home team score
     */
    public void setHomeScore(int homeScore) {
	this.homeScore = homeScore;
    }

    /**
     * Returns the away team score
     * 
     * @return Away team score
     */
    public int getAwayScore() {
	return awayScore;
    }

    /**
     * Sets the away team score
     * 
     * @param awayScore away team score
     */
    public void setAwayScore(int awayScore) {
	this.awayScore = awayScore;
    }

    /**
     * Returns the creation time for this game. This time is only for elapsed time
     * measurement, is not related with all-clock time.
     * 
     * @return Creation time
     */
    public long getCreationTime() {
	return creationTime;
    }

    /**
     * Returns the total score for both teams
     * 
     * @return Total score
     */
    public int getTotalScore() {
	return this.homeScore + this.awayScore;
    }
    
}
