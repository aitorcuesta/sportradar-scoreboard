package com.sportradar.acuesta.scoreboard.comparator;

import com.sportradar.acuesta.scoreboard.entity.Game;

public class TotalScoreComparator extends ChainedComparator {

    @Override
    public int compareMatches(Game g1, Game g2) {	
	return g2.getTotalScore() - g1.getTotalScore();
    }

}
