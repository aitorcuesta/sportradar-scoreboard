package com.sportradar.acuesta.scoreboard.comparator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;

/**
 * Class for testing summary sorting requirement
 * 
 * @author acuesta
 *
 */
public class SummaryComparatorTest {

    private static final String HOME_TEAM_1 = "HOME_TEAM_1";
    private static final String HOME_TEAM_2 = "HOME_TEAM_2";
    private static final String HOME_TEAM_3 = "HOME_TEAM_3";

    private static final String AWAY_TEAM_1 = "AWAY_TEAM_1";
    private static final String AWAY_TEAM_2 = "AWAY_TEAM_2";
    private static final String AWAY_TEAM_3 = "AWAY_TEAM_3";

    private static final int HOME_SCORE_1 = 1;
    private static final int HOME_SCORE_2 = 2;
    private static final int HOME_SCORE_3 = 0;

    private static final int AWAY_SCORE_1 = 0;
    private static final int AWAY_SCORE_2 = 2;
    private static final int AWAY_SCORE_3 = 4;

    private ChainedComparator comparator;

    @Before
    public void setUp() {
	comparator = new HigherTotalScoreComparator();
	comparator.setNext(new MostRecentlyCreatedComparator());
    }

    @Test
    public void gamesAreSortedByTotalScoreHighToLow() throws GameException {
	Game game1 = new Game(HOME_TEAM_1, AWAY_TEAM_1);
	game1.setHomeScore(HOME_SCORE_1);
	game1.setAwayScore(AWAY_SCORE_1);

	Game game2 = new Game(HOME_TEAM_2, AWAY_TEAM_2);
	game2.setHomeScore(HOME_SCORE_2);
	game2.setAwayScore(AWAY_SCORE_2);

	assertTrue(comparator.compare(game1, game2) > 0);

    }

    public void whenTwoGamesHaveSameTotalScoreAreSortedByMostRecentlyAdded() throws GameException {
	Game game2 = new Game(HOME_TEAM_2, AWAY_TEAM_2);
	game2.setHomeScore(HOME_SCORE_2);
	game2.setAwayScore(AWAY_SCORE_2);
	
	Game game3 = new Game(HOME_TEAM_3, AWAY_TEAM_3);
	game3.setHomeScore(HOME_SCORE_3);
	game3.setAwayScore(AWAY_SCORE_3);
	
	assertTrue(comparator.compare(game2, game3) < 0);
		
    }

}
