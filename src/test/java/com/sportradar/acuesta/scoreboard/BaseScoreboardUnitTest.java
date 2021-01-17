package com.sportradar.acuesta.scoreboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;
import com.sportradar.acuesta.scoreboard.repository.ScoreboardRepository;

@RunWith(MockitoJUnitRunner.class)
public class BaseScoreboardUnitTest {

    private static final String HOME_TEAM = "HOME_TEAM";
    private static final String AWAY_TEAM = "AWAY_TEAM";
    private static final int HOME_TEAM_SCORE = 5;
    private static final int AWAY_TEAM_SCORE = 3;

    @Mock
    private ScoreboardRepository repository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private BaseScoreboard scoreboard;

    @Captor
    private ArgumentCaptor<Game> gameCaptor;
    @Captor
    private ArgumentCaptor<String> homeTeamNameCaptor;
    @Captor
    private ArgumentCaptor<String> awayTeamNameCaptor;

    @Before
    public void setUp() {
	scoreboard = new BaseScoreboard(repository);
    }

    @Test
    public void whenInvokingStartGameAGameIsCreated() throws GameException {
	scoreboard.startGame(HOME_TEAM, AWAY_TEAM);

	verify(repository, times(1)).addGame(gameCaptor.capture());
	Game game = gameCaptor.getValue();
	checkEqualityTeamNames(game, HOME_TEAM, AWAY_TEAM);
	checkEqualityTeamScores(game, 0, 0);
	assertTrue(game.getCreationTime() > 0);
    }

    @Test
    public void whenInvokingStartGameAndRepositoryThrowsExceptionExceptionIsPropagated() throws GameException {
	thrown.expect(GameException.class);
	doThrow(GameException.class).when(repository).addGame(any(Game.class));
	scoreboard.startGame(HOME_TEAM, AWAY_TEAM);
	verify(repository, times(1)).addGame(any(Game.class));
    }

    @Test
    public void whenInvokingFinishGameAGameIsDeleted() throws ScoreboardRepositoryException {
	scoreboard.finishGame(HOME_TEAM, AWAY_TEAM);
	verify(repository, times(1)).deleteGame(homeTeamNameCaptor.capture(), awayTeamNameCaptor.capture());
	assertEquals(HOME_TEAM, homeTeamNameCaptor.getValue());
	assertEquals(AWAY_TEAM, awayTeamNameCaptor.getValue());
    }

    @Test
    public void whenInvokingFinishGameAndRepositoryThrowsExceptionExceptionIsPropagated()
	    throws ScoreboardRepositoryException {
	thrown.expect(ScoreboardRepositoryException.class);
	doThrow(ScoreboardRepositoryException.class).when(repository).deleteGame(any(String.class), any(String.class));
	scoreboard.finishGame(HOME_TEAM, AWAY_TEAM);
	verify(repository, times(1)).deleteGame(any(String.class), any(String.class));
    }

    @Test
    public void whenInvokingUpdateGameScoreTheGameIsUpdated() throws GameException, ScoreboardRepositoryException {
	when(repository.findGame(HOME_TEAM, AWAY_TEAM)).thenReturn(Optional.of(new Game(HOME_TEAM, AWAY_TEAM)));
	scoreboard.updateGameScore(HOME_TEAM, AWAY_TEAM, HOME_TEAM_SCORE, AWAY_TEAM_SCORE);

	verify(repository, times(1)).findGame(homeTeamNameCaptor.capture(), awayTeamNameCaptor.capture());
	assertEquals(HOME_TEAM, homeTeamNameCaptor.getValue());
	assertEquals(AWAY_TEAM, awayTeamNameCaptor.getValue());

	verify(repository, times(1)).updateGame(gameCaptor.capture());
	Game game = gameCaptor.getValue();
	checkEqualityTeamNames(game, HOME_TEAM, AWAY_TEAM);
	checkEqualityTeamScores(game, HOME_TEAM_SCORE, AWAY_TEAM_SCORE);

    }

    @Test
    public void whenInvokingUpdateGameScoreAndIsNotInRepositoryExcetptionIsThrown()
	    throws ScoreboardRepositoryException, GameException {
	thrown.expect(ScoreboardRepositoryException.class);
	when(repository.findGame(HOME_TEAM, AWAY_TEAM)).thenReturn(Optional.ofNullable(null));
	scoreboard.updateGameScore(HOME_TEAM, AWAY_TEAM, HOME_TEAM_SCORE, AWAY_TEAM_SCORE);
	verify(repository, times(1)).findGame(HOME_TEAM, AWAY_TEAM);

	verify(repository, never()).updateGame(any(Game.class));
    }

    @Test
    public void whenInvokingUpdateGameScoreAndRepositoryThrowsExceptionExceptionIsPropagated()
	    throws GameException, ScoreboardRepositoryException {
	thrown.expect(ScoreboardRepositoryException.class);
	when(repository.findGame(HOME_TEAM, AWAY_TEAM)).thenReturn(Optional.of(new Game(HOME_TEAM, AWAY_TEAM)));
	doThrow(ScoreboardRepositoryException.class).when(repository).updateGame(any(Game.class));
	scoreboard.updateGameScore(HOME_TEAM, AWAY_TEAM, HOME_TEAM_SCORE, AWAY_TEAM_SCORE);

	verify(repository, times(1)).updateGame(gameCaptor.capture());

    }

    @Test
    public void whenInvokingGetGamesSummaryRepositoryIsCalled() {
	scoreboard.getGamesSummary(null);
	verify(repository, times(1)).findAllGames();
    }

    private void checkEqualityTeamNames(Game game, String expectedHomeName, String expectedAwayName) {
	assertNotNull(game);
	assertEquals(expectedHomeName, game.getHomeTeam());
	assertEquals(expectedAwayName, game.getAwayTeam());
    }

    private void checkEqualityTeamScores(Game game, int expectedHomeScore, int expectedAwayScore) {
	assertNotNull(game);
	assertEquals(expectedHomeScore, game.getHomeScore());
	assertEquals(expectedAwayScore, game.getAwayScore());
    }

}
