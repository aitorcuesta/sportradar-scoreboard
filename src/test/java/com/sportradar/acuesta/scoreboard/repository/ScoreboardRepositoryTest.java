package com.sportradar.acuesta.scoreboard.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;

public class ScoreboardRepositoryTest {

    private static final String HOME_TEAM = "HOME_TEAM";
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final String OTHER_HOME_TEAM = "OTHER_HOME_TEAM";
    private static final String OTHER_AWAY_TEAM = "OTHER_AWAY_TEAM";
    private static final int NEW_HOME_SCORE = 2;

    private ScoreboardRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
	repository = new ScoreboardInMemoryRepository();
    }

    @Test
    public void gamesCanBeAdded() throws GameException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	assertTrue(repository.findGame(HOME_TEAM, AWAY_TEAM).isPresent());
    }

    @Test
    public void nullGamesCannotBeAdded() throws GameException {
	thrown.expect(GameException.class);
	thrown.expectMessage(GameException.GAME_CANNOT_BE_NULL);
	repository.addGame(null);
    }

    @Test
    public void addedGamesCanBeRetrieved() throws GameException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	Game returnedGame = repository.findGame(HOME_TEAM, AWAY_TEAM).get();
	assertEquals(game.getHomeTeam(), returnedGame.getHomeTeam());
	assertEquals(game.getAwayTeam(), returnedGame.getAwayTeam());
	assertEquals(game.getHomeScore(), returnedGame.getHomeScore());
	assertEquals(game.getAwayScore(), returnedGame.getAwayScore());
	assertEquals(game.getCreationTime(), returnedGame.getCreationTime());
    }

    @Test
    public void storedGamesCanBeDeleted() throws GameException, ScoreboardRepositoryException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	repository.deleteGame(HOME_TEAM, AWAY_TEAM);
	assertFalse(repository.findGame(HOME_TEAM, AWAY_TEAM).isPresent());
    }

    @Test
    public void onlystoredGamesCanBeDeleted() throws GameException, ScoreboardRepositoryException {
	thrown.expect(ScoreboardRepositoryException.class);
	thrown.expectMessage(ScoreboardRepositoryException.GAME_NOT_STORED);
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	repository.deleteGame(OTHER_HOME_TEAM, OTHER_AWAY_TEAM);
    }

    @Test
    public void storedGamesCanBeUpdated() throws GameException, ScoreboardRepositoryException {
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	game.setHomeScore(NEW_HOME_SCORE);
	repository.updateGame(game);
	Game returnedGame = repository.findGame(HOME_TEAM, AWAY_TEAM).get();
	assertEquals(NEW_HOME_SCORE, returnedGame.getHomeScore());
    }

    @Test
    public void onlyStoredGamesCanBeUpdated() throws GameException, ScoreboardRepositoryException {
	thrown.expect(ScoreboardRepositoryException.class);
	thrown.expectMessage(ScoreboardRepositoryException.GAME_NOT_STORED);
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	Game otherGame = new Game(OTHER_HOME_TEAM, OTHER_AWAY_TEAM);
	repository.updateGame(otherGame);
    }

    @Test
    public void allGamesCanBeRetrieved() throws GameException {
	assertEquals(0, repository.findAllGames().size());
	Game game = new Game(HOME_TEAM, AWAY_TEAM);
	repository.addGame(game);
	assertEquals(1, repository.findAllGames().size());
	Game otherGame = new Game(OTHER_HOME_TEAM, OTHER_AWAY_TEAM);
	repository.addGame(otherGame);
	assertEquals(2, repository.findAllGames().size());
    }

}
