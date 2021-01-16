package com.sportradar.acuesta.scoreboard.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;

public class ScoreboardInMemoryRepository implements ScoreboardRepository {

    private Map<String, Game> backend;

    public ScoreboardInMemoryRepository() {
	backend = new HashMap<>();
    }

    @Override
    public void add(Game game) throws GameException {
	if (null == game) {
	    throw new GameException(GameException.GAME_CANNOT_BE_NULL);
	}
	backend.put(getId(game.getHomeTeam(), game.getAwayTeam()), game);

    }

    @Override
    public void delete(String homeTeam, String awayTeam) throws ScoreboardRepositoryException {
	find(homeTeam, awayTeam)
		.orElseThrow(() -> new ScoreboardRepositoryException(ScoreboardRepositoryException.GAME_NOT_STORED));
	backend.remove(getId(homeTeam, awayTeam));
    }

    @Override
    public void update(Game game) throws GameException, ScoreboardRepositoryException {
	String gameId = getId(game.getHomeTeam(), game.getAwayTeam());
	find(game.getHomeTeam(), game.getAwayTeam())
		.orElseThrow(() -> new ScoreboardRepositoryException(ScoreboardRepositoryException.GAME_NOT_STORED));
	backend.put(gameId, game);

    }

    @Override
    public Optional<Game> find(String homeTeam, String awayTeam) {
	return Optional.ofNullable(backend.get(getId(homeTeam, awayTeam)));
    }

    @Override
    public List<Game> findAll() {	
	return backend.values().stream().map(Game::new).collect(Collectors.toList());
    }

    private String getId(String homeTeam, String awayTeam) {
	return String.join("-", homeTeam, awayTeam);
    }

}
