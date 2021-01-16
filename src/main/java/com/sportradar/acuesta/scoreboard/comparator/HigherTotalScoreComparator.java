package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

/**
 * Compares two games by their total score. Higher scores goes first on lists
 * 
 * @author acuesta
 *
 */
public class HigherTotalScoreComparator extends ChainedComparator {

    @Override
    public int compareGames(Game g1, Game g2) {
	return g2.getTotalScore() - g1.getTotalScore();
    }

}
