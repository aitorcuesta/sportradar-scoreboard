package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

/**
 * Compares two games based on their creation time. Most recently created goes
 * first on list
 * 
 * @author acuesta
 *
 */
public class CreationTimeComparator extends ChainedComparator {

    public enum SortingType {
	NEWER_FIRST, OLDER_FIRST
    };

    private SortingType sortingType;

    public CreationTimeComparator(SortingType sortingType) {
	this.sortingType = sortingType;
    }

    @Override
    protected int compareGames(Game g1, Game g2) {
	int comparingResult = (int) (g1.getCreationTime() - g2.getCreationTime());
	return sortingType == SortingType.OLDER_FIRST ? comparingResult : comparingResult * -1;
    }

}
