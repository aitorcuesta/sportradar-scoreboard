package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

/**
 * Compares two games based on their creation time. Most recently created goes
 * first on list
 * 
 * @author acuesta
 *
 */
public class MostRecentlyCreatedComparator extends ChainedComparator {

    @Override
    protected int compareGames(Game m1, Game m2) {
	return (int) (m2.getCreationTime() - m1.getCreationTime());
    }

}
