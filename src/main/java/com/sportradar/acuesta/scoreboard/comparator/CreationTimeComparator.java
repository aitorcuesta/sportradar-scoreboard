package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

public class CreationTimeComparator extends ChainedComparator {

    @Override
    protected int compareMatches(Game m1, Game m2) {
	return (int) (m1.getCreationTime() - m2.getCreationTime());
    }

}
