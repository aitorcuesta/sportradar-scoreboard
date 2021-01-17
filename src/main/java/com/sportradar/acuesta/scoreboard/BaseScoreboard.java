package com.sportradar.acuesta.scoreboard;

import java.util.List;
import java.util.stream.Collectors;

import com.sportradar.acuesta.scoreboard.comparator.ChainedComparator;
import com.sportradar.acuesta.scoreboard.comparator.CreationTimeComparator;
import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;
import com.sportradar.acuesta.scoreboard.repository.ScoreboardInMemoryRepository;
import com.sportradar.acuesta.scoreboard.repository.ScoreboardRepository;

/**
 * Base class for scoreboards.
 * 
 * @author acuesta
 *
 */
public class BaseScoreboard {

    /**
     * The repository for this scoreboard
     */
    private ScoreboardRepository repository;

    /**
     * Default constructor. Creates a scoreboard within in-memory repository
     */
    public BaseScoreboard() {
	this.repository = new ScoreboardInMemoryRepository();
    }

    /**
     * Creates the scoreboard and assigns his repository
     * 
     * @param repository
     */
    public BaseScoreboard(ScoreboardRepository repository) {
	this.repository = repository;
    }

    /**
     * Starts a new game in our scoreboard
     * 
     * @param homeTeam The home team name
     * @param awayTeam The away team name
     * @throws GameException If any exception occurs creating or storing the game
     */
    public void startGame(String homeTeam, String awayTeam) throws GameException {
	repository.addGame(new Game(homeTeam, awayTeam));

    }

    /**
     * Removes a game from our scoreboard
     * 
     * @param homeTeam The home team name
     * @param awayTeam The away team name
     * @throws ScoreboardRepositoryException If any exception occurs while removing
     *                                       the game
     */
    public void finishGame(String homeTeam, String awayTeam) throws ScoreboardRepositoryException {
	repository.deleteGame(homeTeam, awayTeam);

    }

    /**
     * Updates a game in our scoreboard
     * 
     * @param homeTeam  The home team name
     * @param awayTeam  The away team name
     * @param homeScore The new score for the home team
     * @param awayScore The new score for the away team
     * @throws ScoreboardRepositoryException If any exception occurs while finding
     *                                       or updating the game in our scoreboard
     * @throws GameException                 If any exception occurs while updating
     *                                       the game
     */
    public void updateGameScore(String homeTeam, String awayTeam, int homeScore, int awayScore)
	    throws ScoreboardRepositoryException, GameException {
	Game game = repository.findGame(homeTeam, awayTeam)
		.orElseThrow(() -> new ScoreboardRepositoryException(ScoreboardRepositoryException.GAME_NOT_STORED));
	game.setHomeScore(homeScore);
	game.setAwayScore(awayScore);
	repository.updateGame(game);
    }

    /**
     * Returns all the games stored in our scoreboard
     * 
     * @param comparator The comparator for sorting the result.
     *                   {@link CreationTimeComparator} is used by default
     * @return The summary of games
     */
    public List<Game> getGamesSummary(ChainedComparator comparator) {
	return repository.findAllGames().stream()
		.sorted(null != comparator ? comparator
			: new CreationTimeComparator(CreationTimeComparator.SortingType.NEWER_FIRST))
		.collect(Collectors.toList());

    }

}
