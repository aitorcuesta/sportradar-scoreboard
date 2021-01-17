package com.sportradar.acuesta.scoreboard.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;

/**
 * Basic in-memory implementation for storing games
 * 
 * @author acuesta
 *
 */
public class ScoreboardInMemoryRepository implements ScoreboardRepository {

    private static final String DASH = "-";
    private Map<String, Game> backend;

    public ScoreboardInMemoryRepository() {
	backend = new HashMap<>();
    }

    @Override
    public void addGame(Game game) throws GameException {
	if (null == game) {
	    throw new GameException(GameException.GAME_CANNOT_BE_NULL);
	}
	backend.put(getId(game.getHomeTeam(), game.getAwayTeam()), game);

    }

    @Override
    public void deleteGame(String homeTeam, String awayTeam) throws ScoreboardRepositoryException {
	findGame(homeTeam, awayTeam)
		.orElseThrow(() -> new ScoreboardRepositoryException(ScoreboardRepositoryException.GAME_NOT_STORED));
	backend.remove(getId(homeTeam, awayTeam));
    }

    @Override
    public void updateGame(Game game) throws GameException, ScoreboardRepositoryException {
	String gameId = getId(game.getHomeTeam(), game.getAwayTeam());
	findGame(game.getHomeTeam(), game.getAwayTeam())
		.orElseThrow(() -> new ScoreboardRepositoryException(ScoreboardRepositoryException.GAME_NOT_STORED));
	backend.put(gameId, game);

    }

    @Override
    public Optional<Game> findGame(String homeTeam, String awayTeam) {
	return Optional.ofNullable(backend.get(getId(homeTeam, awayTeam)));
    }

    @Override
    public List<Game> findAllGames() {
	return backend.values().stream().map(Game::new).collect(Collectors.toList());
    }

    private String getId(String homeTeam, String awayTeam) {
	return String.join(DASH, homeTeam, awayTeam);
    }

}
