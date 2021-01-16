package com.sportradar.acuesta.scoreboard.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sportradar.acuesta.scoreboard.exception.GameException;

/**
 * Test class for games
 * 
 * @author acuesta
 *
 */
public class GameTest {

    private static final String HOME_TEAM = "HOME_TEAM";
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final int HOME_SCORE = 5;
    private static final int AWAY_SCORE = 5;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void gamesWithoutHomeTeamGenerateError() throws GameException {
	thrown.expect(GameException.class);
	thrown.expectMessage(GameException.HOME_TEAM_NAME_MANDATORY);

	Game game = new Game(null, AWAY_TEAM);
    }

    @Test
    public void gamesWithoutAwayTeamGenerateError() throws GameException {
	thrown.expect(GameException.class);
	thrown.expectMessage(GameException.AWAY_TEAM_NAME_MANDATORY);

	Game game = new Game(HOME_TEAM, null);
    }

    @Test
    public void gameInitialScoreIs0_0() throws GameException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	assertEquals(0, game.getHomeScore());
	assertEquals(0, game.getAwayScore());
    }

    @Test
    public void gameTotalScoreIsHomeScorePlusAwayScore() throws GameException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	game.setHomeScore(HOME_SCORE);
	game.setAwayScore(AWAY_SCORE);
	assertEquals(HOME_SCORE + AWAY_SCORE, game.getTotalScore());
    }

    @Test
    public void gamesHaveCreationTime() throws GameException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	assertTrue(game.getCreationTime() > 0);	
    }

}
