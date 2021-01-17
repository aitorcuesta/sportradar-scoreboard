package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

/**
 * Compares two games by their total score. Higher scores goes first on lists
 * 
 * @author acuesta
 *
 */
public class TotalScoreComparator extends ChainedComparator {

    public enum SortingType {
	HIGHER_FIRST, LOWER_FIRST
    };

    private SortingType sortingType;

    public TotalScoreComparator(SortingType sortingType) {
	this.sortingType = sortingType;
    }

    @Override
    public int compareGames(Game g1, Game g2) {
	int comparingResult = g1.getTotalScore() - g2.getTotalScore();
	return sortingType == SortingType.LOWER_FIRST ? comparingResult : comparingResult * -1;
    }

}
