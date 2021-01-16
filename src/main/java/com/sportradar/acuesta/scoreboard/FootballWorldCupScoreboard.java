package com.sportradar.acuesta.scoreboard;

import java.util.List;

import com.sportradar.acuesta.scoreboard.comparator.ChainedComparator;
import com.sportradar.acuesta.scoreboard.comparator.MostRecentlyCreatedComparator;
import com.sportradar.acuesta.scoreboard.comparator.TotalScoreComparator;
import com.sportradar.acuesta.scoreboard.entity.Game;
import com.sportradar.acuesta.scoreboard.repository.ScoreboardRepository;

/**
 * Implementation of our Football World Cup Scoreboard
 * 
 * @author acuesta
 *
 */
public class FootballWorldCupScoreboard extends BaseScoreboard {
    
    private ChainedComparator summaryByTotalScore;

    /**
     * Default constructor
     */
    public FootballWorldCupScoreboard() {
	super();
	initalizeComparators();
    }

    /**
     * Creates our scoreboard and sets the repository
     * 
     * @param repository The repository for storing the games
     */
    public FootballWorldCupScoreboard(ScoreboardRepository repository) {
	super(repository);
	initalizeComparators();
    }

    /**
     * Returns all the games stored in our repository by total score. Those games
     * with the same score will be returned ordered by the most recently added to
     * our system
     * 
     * @return
     */
    public List<Game> getSummaryByTotalScore() {	
	return this.getSummary(summaryByTotalScore);
    }
    
        
    private void initalizeComparators() {
	summaryByTotalScore = new TotalScoreComparator();
	summaryByTotalScore.setNext(new MostRecentlyCreatedComparator());
    }

}
