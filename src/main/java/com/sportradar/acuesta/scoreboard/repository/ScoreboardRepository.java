package com.sportradar.acuesta.scoreboard.repository;

import java.util.List;
import java.util.Optional;

import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.exception.GameException;
import com.sportradar.acuesta.scoreboard.exception.ScoreboardRepositoryException;

/**
 * Interface that defines a repository for storing games in our scoreboard
 * @author acuesta
 *
 */
public interface ScoreboardRepository {

    /**
     * Adds a game to our repository
     * 
     * @param game The game to be added
     * @throws GameException If the game is null
     */
    void addGame(Game game) throws GameException;

    /**
     * Removes a game from our repository. The game is represented by home and away
     * team names
     * 
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @throws ScoreboardRepositoryException If the game to be deleted is not in our
     *                                       system
     */
    void deleteGame(String homeTeam, String awayTeam) throws ScoreboardRepositoryException;

    /**
     * Updates a game in our repository
     * 
     * @param game The game to be updated
     * @throws GameException                 If the game is null
     * @throws ScoreboardRepositoryException If the game to be updated is not in our
     *                                       system
     */
    void updateGame(Game game) throws GameException, ScoreboardRepositoryException;

    /**
     * Returns an Optional game
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @return Optional game
     */
    Optional<Game> findGame(String homeTeam, String awayTeam);

    /**
     * Returns all the games stored in our system
     * @return All the games stored
     */
    List<Game> findAllGames();

}
