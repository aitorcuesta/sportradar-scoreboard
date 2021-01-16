package com.sportradar.acuesta.scoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;

/**
 * Test class for Football World Cup Scoreboard
 * 
 * @author acuesta
 *
 */
public class FootballWorldCupScoreboardTest {

    private static final String HOME_TEAM_1 = "HOME_TEAM_1";
    private static final String HOME_TEAM_2 = "HOME_TEAM_2";

    private static final String AWAY_TEAM_1 = "AWAY_TEAM_1";
    private static final String AWAY_TEAM_2 = "AWAY_TEAM_2";

    private static final int HOME_SCORE_1 = 1;
    private static final int AWAY_SCORE_1 = 0;

    private static final String MEXICO = "MEXICO";
    private static final int MEXICO_SCORE = 0;

    private static final String CANADA = "CANADA";
    private static final int CANADA_SCORE = 5;

    private static final String SPAIN = "SPAIN";
    private static final int SPAIN_SCORE = 10;

    private static final String BRAZIL = "BRAZIL";
    private static final int BRAZIL_SCORE = 2;

    private static final String GERMANY = "GERMANY";
    private static final int GERMANY_SCORE = 2;

    private static final String FRANCE = "FRANCE";
    private static final int FRANCE_SCORE = 2;

    private static final String URUGUAY = "URUGUAY";
    private static final int URUGUAY_SCORE = 6;

    private static final String ITALY = "ITALY";
    private static final int ITALY_SCORE = 6;

    private static final String ARGENTINA = "ARGENTINA";
    private static final int ARGENTINA_SCORE = 3;

    private static final String AUSTRALIA = "AUSTRALIA";
    private static final int AUSTRALIA_SCORE = 1;

    private FootballWorldCupScoreboard scoreboard;

    @Before
    public void setUp() {
	scoreboard = new FootballWorldCupScoreboard();
    }

    @Test
    public void gameCanBeStartedWithBothTeamNames() throws GameException {
	assertEquals(0, scoreboard.getSummary(null).size());
	scoreboard.startGame(HOME_TEAM_1, AWAY_TEAM_1);
	assertEquals(1, scoreboard.getSummary(null).size());
    }

    @Test
    public void finishedGamesAreRemovedFromScoreboard() throws GameException, ScoreboardRepositoryException {
	assertEquals(0, scoreboard.getSummary(null).size());
	scoreboard.startGame(HOME_TEAM_1, AWAY_TEAM_1);
	assertEquals(1, scoreboard.getSummary(null).size());
	scoreboard.finishGame(HOME_TEAM_1, AWAY_TEAM_1);
	assertEquals(0, scoreboard.getSummary(null).size());
    }

    @Test
    public void gamesCanBeUpdated() throws GameException, ScoreboardRepositoryException {
	assertEquals(0, scoreboard.getSummary(null).size());
	scoreboard.startGame(HOME_TEAM_1, AWAY_TEAM_1);
	assertEquals(1, scoreboard.getSummary(null).size());
	scoreboard.updateScore(HOME_TEAM_1, AWAY_TEAM_1, HOME_SCORE_1, AWAY_SCORE_1);
	Game game = scoreboard.getSummary(null).get(0);
	assertEquals(HOME_SCORE_1, game.getHomeScore());
	assertEquals(AWAY_SCORE_1, game.getAwayScore());
    }

    @Test
    public void gamesSummaryIsSortedByTotalScoreAndMostRecentlyAdded() throws GameException, ScoreboardRepositoryException {
	assertEquals(0, scoreboard.getSummary(null).size());
	scoreboard.startGame(MEXICO, CANADA);
	scoreboard.startGame(SPAIN, BRAZIL);
	scoreboard.startGame(GERMANY, FRANCE);
	scoreboard.startGame(URUGUAY, ITALY);
	scoreboard.startGame(ARGENTINA, AUSTRALIA);
	assertEquals(5, scoreboard.getSummary(null).size());

	scoreboard.updateScore(MEXICO, CANADA, MEXICO_SCORE, CANADA_SCORE);
	scoreboard.updateScore(SPAIN, BRAZIL, SPAIN_SCORE, BRAZIL_SCORE);
	scoreboard.updateScore(GERMANY, FRANCE, GERMANY_SCORE, FRANCE_SCORE);
	scoreboard.updateScore(URUGUAY, ITALY, URUGUAY_SCORE, ITALY_SCORE);
	scoreboard.updateScore(ARGENTINA, AUSTRALIA, ARGENTINA_SCORE, AUSTRALIA_SCORE);
	
	List<Game> summary = scoreboard.getSummaryByTotalScore();
	assertEquals(5, scoreboard.getSummary(null).size());
	
	/*
	 * Order should be:
	 * 0 - URUGUAY - ITALY
	 * 1 - SPAIN - BRAZIL
	 * 2 - MEXICO - CANADA
	 * 3 - ARGENTINA - AUSTRALIA
	 * 4 - GERMANY - FRANCE
	 */
	
	// URUGUAY - ITALY
	Game game = summary.get(0);
	assertEquals(URUGUAY, game.getHomeTeam());
	assertEquals(ITALY, game.getAwayTeam());
	assertEquals(URUGUAY_SCORE, game.getHomeScore());
	assertEquals(ITALY_SCORE, game.getAwayScore());
	
	// SPAIN - BRAZIL
	game = summary.get(1);
	assertEquals(SPAIN, game.getHomeTeam());
	assertEquals(BRAZIL, game.getAwayTeam());
	assertEquals(SPAIN_SCORE, game.getHomeScore());
	assertEquals(BRAZIL_SCORE, game.getAwayScore());
	
	// MEXICO - CANADA
	game = summary.get(2);
	assertEquals(MEXICO, game.getHomeTeam());
	assertEquals(CANADA, game.getAwayTeam());
	assertEquals(MEXICO_SCORE, game.getHomeScore());
	assertEquals(CANADA_SCORE, game.getAwayScore());
	
	// ARGENTINA - AUSTRALIA
	game = summary.get(3);
	assertEquals(ARGENTINA, game.getHomeTeam());
	assertEquals(AUSTRALIA, game.getAwayTeam());
	assertEquals(ARGENTINA_SCORE, game.getHomeScore());
	assertEquals(AUSTRALIA_SCORE, game.getAwayScore());
	
	// GERMANY - FRANCE
	game = summary.get(4);
	assertEquals(GERMANY, game.getHomeTeam());
	assertEquals(FRANCE, game.getAwayTeam());
	assertEquals(GERMANY_SCORE, game.getHomeScore());
	assertEquals(FRANCE_SCORE, game.getAwayScore());
	
    }

}
