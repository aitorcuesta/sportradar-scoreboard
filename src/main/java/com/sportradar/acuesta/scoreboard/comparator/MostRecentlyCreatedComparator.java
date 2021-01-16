package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

public class MostRecentlyCreatedComparator extends ChainedComparator {

    @Override
    protected int compareMatches(Game m1, Game m2) {
	return (int) (m2.getCreationTime() - m1.getCreationTime());
    }

}
